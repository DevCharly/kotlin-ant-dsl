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

import org.apache.tools.ant.taskdefs.Chmod
import org.apache.tools.ant.taskdefs.ExecuteOn
import org.apache.tools.ant.types.Environment.Variable
import org.apache.tools.ant.types.ResourceCollection
import org.apache.tools.ant.util.FileNameMapper

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.chmod(
	file: String? = null,
	dir: String? = null,
	perm: String? = null,
	includes: String? = null,
	excludes: String? = null,
	defaultexcludes: Boolean? = null,
	parallel: Boolean? = null,
	type: FileDirBoth? = null,
	maxparallel: Int? = null,
	verbose: Boolean? = null,
	os: String? = null,
	osfamily: String? = null,
	nested: (KChmod.() -> Unit)? = null)
{
	Chmod().execute("chmod") { task ->
		if (file != null)
			task.setFile(project.resolveFile(file))
		if (dir != null)
			task.setDir(project.resolveFile(dir))
		if (perm != null)
			task.setPerm(perm)
		if (includes != null)
			task.setIncludes(includes)
		if (excludes != null)
			task.setExcludes(excludes)
		if (defaultexcludes != null)
			task.setDefaultexcludes(defaultexcludes)
		if (parallel != null)
			task.setParallel(parallel)
		if (type != null)
			task.setType(ExecuteOn.FileDirBoth().apply { this.value = type.value })
		if (maxparallel != null)
			task.setMaxParallel(maxparallel)
		if (verbose != null)
			task.setVerbose(verbose)
		if (os != null)
			task.setOs(os)
		if (osfamily != null)
			task.setOsFamily(osfamily)
		if (nested != null)
			nested(KChmod(task))
	}
}

class KChmod(override val component: Chmod) :
	IFileNameMapperNested,
	IResourceCollectionNested
{
	fun env(key: String? = null, value: String? = null, path: String? = null, file: String? = null) {
		component.addEnv(Variable().apply {
			_init(component.project, key, value, path, file)
		})
	}
	fun arg(value: String? = null, line: String? = null, path: String? = null, pathref: String? = null, file: String? = null, prefix: String? = null, suffix: String? = null) {
		component.createArg().apply {
			component.project.setProjectReference(this)
			_init(value, line, path, pathref, file, prefix, suffix)
		}
	}
	fun srcfile(prefix: String? = null, suffix: String? = null) {
		component.createSrcfile().apply {
			_init(prefix, suffix)
		}
	}
	fun targetfile(prefix: String? = null, suffix: String? = null) {
		component.createTargetfile().apply {
			_init(prefix, suffix)
		}
	}
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
			component.project.setProjectReference(this)
			_init(includes, excludes, includesfile, excludesfile, nested)
		}
	}
	override fun _addFileNameMapper(value: FileNameMapper) = component.add(value)
	override fun _addResourceCollection(value: ResourceCollection) = component.add(value)
}
