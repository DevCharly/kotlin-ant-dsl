/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.devcharly.kotlin.ant

import org.apache.tools.ant.taskdefs.Jar
import org.apache.tools.ant.taskdefs.Manifest
import org.apache.tools.ant.taskdefs.Zip
import org.apache.tools.ant.types.ResourceCollection
import org.apache.tools.ant.types.spi.Provider
import org.apache.tools.ant.types.spi.Service

//---- zip --------------------------------------------------------------------

fun AntBuilder.zip(destfile: String, basedir: String? = null,
				   includes: String? = null, includesfile: String? = null,
				   excludes: String? = null, excludesfile: String? = null,
				   defaultexcludes: Boolean = true,
				   nested: (KZip.() -> Unit)? = null)
{
	Zip().execute("zip") { task ->
		task.setDestFile(resolveFile(destfile))
		task.setBasedir(resolveFile(basedir))
		task._init(includes, includesfile, excludes, excludesfile, defaultexcludes)
		if (nested != null)
			nested(KZip(task))
	}
}

open class KZip(override val task: Zip)
	: IFileSetNested
{
	override fun _addResourceCollection(res: ResourceCollection) {
		task.add(res)
	}
}

//---- jar --------------------------------------------------------------------

fun AntBuilder.jar(destfile: String, basedir: String? = null,
				   includes: String? = null, includesfile: String? = null,
				   excludes: String? = null, excludesfile: String? = null,
				   defaultexcludes: Boolean = true,
				   nested: (KJar.() -> Unit)? = null)
{
	Jar().execute("jar") { task ->
		task.setDestFile(resolveFile(destfile))
		task.setBasedir(resolveFile(basedir))
		task._init(includes, includesfile, excludes, excludesfile, defaultexcludes)
		if (nested != null)
			nested(KJar(task))
	}
}

class KJar(override val task: Jar)
	: KZip(task)
{
	fun manifest(init: KManifest.() -> Unit) {
		val manifest = Manifest()
		init(KManifest(manifest))
		task.addConfiguredManifest(manifest)
	}

	fun service(type: String, vararg providers: String) {
		val service = Service()
		service.type = type
		providers.forEach { service.addConfiguredProvider(Provider().apply { className = it }) }
		task.addConfiguredService(service)
	}
}

class KManifest(val manifest: Manifest) {
	fun attribute(name: String, value: String) {
		manifest.addConfiguredAttribute(Manifest.Attribute(name, value))
	}
}
