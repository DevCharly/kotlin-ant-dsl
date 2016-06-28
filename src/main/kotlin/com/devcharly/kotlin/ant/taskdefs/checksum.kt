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

import org.apache.tools.ant.taskdefs.Checksum
import org.apache.tools.ant.types.ResourceCollection
import org.apache.tools.ant.types.selectors.FileSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.checksum(
	file: String? = null,
	todir: String? = null,
	algorithm: String? = null,
	provider: String? = null,
	fileext: String? = null,
	property: String? = null,
	totalproperty: String? = null,
	verifyproperty: String? = null,
	forceoverwrite: Boolean? = null,
	readbuffersize: Int? = null,
	format: FormatElement? = null,
	pattern: String? = null,
	includes: String? = null,
	excludes: String? = null,
	defaultexcludes: Boolean? = null,
	includesfile: String? = null,
	excludesfile: String? = null,
	casesensitive: Boolean? = null,
	followsymlinks: Boolean? = null,
	nested: (KChecksum.() -> Unit)? = null)
{
	Checksum().execute("checksum") { task ->
		if (file != null)
			task.setFile(project.resolveFile(file))
		if (todir != null)
			task.setTodir(project.resolveFile(todir))
		if (algorithm != null)
			task.setAlgorithm(algorithm)
		if (provider != null)
			task.setProvider(provider)
		if (fileext != null)
			task.setFileext(fileext)
		if (property != null)
			task.setProperty(property)
		if (totalproperty != null)
			task.setTotalproperty(totalproperty)
		if (verifyproperty != null)
			task.setVerifyproperty(verifyproperty)
		if (forceoverwrite != null)
			task.setForceOverwrite(forceoverwrite)
		if (readbuffersize != null)
			task.setReadBufferSize(readbuffersize)
		if (format != null)
			task.setFormat(Checksum.FormatElement().apply { value = format.value })
		if (pattern != null)
			task.setPattern(pattern)
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
			nested(KChecksum(task))
	}
}

class KChecksum(override val component: Checksum) : IFileSelectorNested, IResourceCollectionNested {
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

enum class FormatElement(val value: String) { CHECKSUM("CHECKSUM"), MD5SUM("MD5SUM"), SVF("SVF") }
