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

import org.apache.tools.ant.taskdefs.FixCRLF
import org.apache.tools.ant.types.selectors.FileSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.fixcrlf(
	srcdir: String? = null,
	destdir: String? = null,
	javafiles: Boolean? = null,
	file: String? = null,
	eol: CrLf? = null,
	tab: AddAsisRemove? = null,
	tablength: Int? = null,
	eof: AddAsisRemove? = null,
	encoding: String? = null,
	outputencoding: String? = null,
	fixlast: Boolean? = null,
	preservelastmodified: Boolean? = null,
	includes: String? = null,
	excludes: String? = null,
	defaultexcludes: Boolean? = null,
	includesfile: String? = null,
	excludesfile: String? = null,
	casesensitive: Boolean? = null,
	followsymlinks: Boolean? = null,
	nested: (KFixCRLF.() -> Unit)? = null)
{
	FixCRLF().execute("fixcrlf") { task ->
		if (srcdir != null)
			task.setSrcdir(project.resolveFile(srcdir))
		if (destdir != null)
			task.setDestdir(project.resolveFile(destdir))
		if (javafiles != null)
			task.setJavafiles(javafiles)
		if (file != null)
			task.setFile(project.resolveFile(file))
		if (eol != null)
			task.setEol(FixCRLF.CrLf().apply { value = eol.value })
		if (tab != null)
			task.setTab(FixCRLF.AddAsisRemove().apply { value = tab.value })
		if (tablength != null)
			task.setTablength(tablength)
		if (eof != null)
			task.setEof(FixCRLF.AddAsisRemove().apply { value = eof.value })
		if (encoding != null)
			task.setEncoding(encoding)
		if (outputencoding != null)
			task.setOutputEncoding(outputencoding)
		if (fixlast != null)
			task.setFixlast(fixlast)
		if (preservelastmodified != null)
			task.setPreserveLastModified(preservelastmodified)
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
			nested(KFixCRLF(task))
	}
}

class KFixCRLF(override val component: FixCRLF) : IFileSelectorNested {
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
}

enum class CrLf(val value: String) { ASIS("asis"), CR("cr"), LF("lf"), CRLF("crlf"), MAC("mac"), UNIX("unix"), DOS("dos") }
enum class AddAsisRemove(val value: String) { ADD("add"), ASIS("asis"), REMOVE("remove") }
