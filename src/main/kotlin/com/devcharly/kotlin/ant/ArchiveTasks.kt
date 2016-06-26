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
import org.apache.tools.ant.taskdefs.Tar
import org.apache.tools.ant.taskdefs.Zip
import org.apache.tools.ant.types.*
import org.apache.tools.ant.types.spi.Provider
import org.apache.tools.ant.types.spi.Service

//---- zip --------------------------------------------------------------------

fun Ant.zip(destfile: String, basedir: String? = null,
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

open class KZip(override val component: Zip)
	: IFileSetNested, IZipFileSetNested
{
	override fun _addFileSet(value: FileSet) = component.add(value)
	override fun _addZipFileSet(value: ZipFileSet) = component.add(value)
}

//---- tar --------------------------------------------------------------------

fun Ant.tar(destfile: String, basedir: String? = null,
            longfile: TarLongFileMode = TarLongFileMode.WARN,
            includes: String? = null, includesfile: String? = null,
            excludes: String? = null, excludesfile: String? = null,
            defaultexcludes: Boolean = true,
            compression: TarCompressionMethod = TarCompressionMethod.NONE,
            encoding: String? = null,
            nested: (KTar.() -> Unit)? = null)
{
	Tar().execute("tar") { task ->
		task.setDestFile(resolveFile(destfile))
		task.setBasedir(resolveFile(basedir))
		if (longfile != TarLongFileMode.WARN)
			task.setLongfile( Tar.TarLongFileMode().apply { value = longfile.name.toLowerCase() })
		task._init(includes, includesfile, excludes, excludesfile, defaultexcludes)
		if (compression != TarCompressionMethod.NONE)
			task.setCompression(Tar.TarCompressionMethod().apply { value = compression.name.toLowerCase() })
		if (encoding != null)
			task.setEncoding(encoding)
		if (nested != null)
			nested(KTar(task))
	}
}

open class KTar(override val component: Tar)
	: IFileSetNested, ITarFileSetNested
{
	override fun _addFileSet(value: FileSet) = component.add(value)
	override fun _addTarFileSet(value: TarFileSet) = component.add(value)
}

enum class TarLongFileMode { WARN, FAIL, TRUNCATE, GNU, POSIX, OMIT }
enum class TarCompressionMethod { NONE, GZIP, BZIP2 }

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
	: KZip(component)
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
}

class KManifest(val manifest: Manifest) {
	fun attribute(name: String, value: String) {
		manifest.addConfiguredAttribute(Manifest.Attribute(name, value))
	}
}
