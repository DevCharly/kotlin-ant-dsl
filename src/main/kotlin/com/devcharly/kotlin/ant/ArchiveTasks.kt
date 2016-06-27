/*
 * Copyright 2016 Karl Tauber
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
import org.apache.tools.ant.types.*
import org.apache.tools.ant.types.spi.Provider
import org.apache.tools.ant.types.spi.Service

//---- jar --------------------------------------------------------------------

fun Ant.jar(destfile: String, basedir: String? = null,
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

class KJar(override val component: Jar)
	: IResourceCollectionNested
{
	fun manifest(init: KManifest.() -> Unit) {
		val manifest = Manifest()
		init(KManifest(manifest))
		component.addConfiguredManifest(manifest)
	}

	fun service(type: String, vararg providers: String) {
		val service = Service()
		service.type = type
		providers.forEach { service.addConfiguredProvider(Provider().apply { className = it }) }
		component.addConfiguredService(service)
	}

	override fun _addResourceCollection(value: ResourceCollection) = component.add(value)
}

class KManifest(val manifest: Manifest) {
	fun attribute(name: String, value: String) {
		manifest.addConfiguredAttribute(Manifest.Attribute(name, value))
	}
}
