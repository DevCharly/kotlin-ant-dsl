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

import org.apache.tools.ant.taskdefs.Tar
import org.apache.tools.ant.types.ResourceCollection
import org.apache.tools.ant.types.selectors.FileSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.tar(
	destfile: String? = null,
	basedir: String? = null,
	longfile: TarLongFileMode? = null,
	compression: TarCompressionMethod? = null,
	encoding: String? = null,
	includes: String? = null,
	excludes: String? = null,
	defaultexcludes: Boolean? = null,
	includesfile: String? = null,
	excludesfile: String? = null,
	casesensitive: Boolean? = null,
	followsymlinks: Boolean? = null,
	nested: (KTar.() -> Unit)? = null)
{
	Tar().execute("tar") { task ->
		if (destfile != null)
			task.setDestFile(project.resolveFile(destfile))
		if (basedir != null)
			task.setBasedir(project.resolveFile(basedir))
		if (longfile != null)
			task.setLongfile(Tar.TarLongFileMode().apply { value = longfile.value })
		if (compression != null)
			task.setCompression(Tar.TarCompressionMethod().apply { value = compression.value })
		if (encoding != null)
			task.setEncoding(encoding)
		if (includes != null)
			task.setIncludes(includes)
		if (excludes != null)
			task.setExcludes(excludes)
		if (defaultexcludes != null)
			task.setDefaultexcludes(defaultexcludes)
		if (includesfile != null)
			task.setIncludesfile(project.resolveFile(includesfile))
		if (excludesfile != null)
			task.setExcludesfile(project.resolveFile(excludesfile))
		if (casesensitive != null)
			task.setCaseSensitive(casesensitive)
		if (followsymlinks != null)
			task.setFollowSymlinks(followsymlinks)
		if (nested != null)
			nested(KTar(task))
	}
}

class KTar(override val component: Tar) : IFileSelectorNested, IResourceCollectionNested {
	fun include(name: String? = null, If: String? = null, unless: String? = null) {
		component.createInclude().apply {
			_init(name, If, unless)
		}
	}
	fun exclude(name: String? = null, If: String? = null, unless: String? = null) {
		component.createExclude().apply {
			_init(name, If, unless)
		}
	}
	fun patternset(includes: String? = null, excludes: String? = null, includesfile: String? = null, excludesfile: String? = null, nested: (KPatternSet.() -> Unit)? = null) {
		component.createPatternSet().apply {
			_init(includes, excludes, includesfile, excludesfile, nested)
		}
	}
	override fun _addFileSelector(value: FileSelector) = component.add(value)
	override fun _addResourceCollection(value: ResourceCollection) = component.add(value)
}

enum class TarLongFileMode(val value: String) { WARN("warn"), FAIL("fail"), TRUNCATE("truncate"), GNU("gnu"), POSIX("posix"), OMIT("omit") }
enum class TarCompressionMethod(val value: String) { NONE("none"), GZIP("gzip"), BZIP2("bzip2") }
