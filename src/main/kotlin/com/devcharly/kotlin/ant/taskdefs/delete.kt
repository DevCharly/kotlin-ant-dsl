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

import org.apache.tools.ant.taskdefs.Delete
import org.apache.tools.ant.types.ResourceCollection
import org.apache.tools.ant.types.selectors.FileSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.delete(
	file: String? = null,
	dir: String? = null,
	verbose: Boolean? = null,
	quiet: Boolean? = null,
	failOnError: Boolean? = null,
	deleteOnExit: Boolean? = null,
	includeEmptyDirs: Boolean? = null,
	performGcOnFailedDelete: Boolean? = null,
	includes: String? = null,
	excludes: String? = null,
	defaultexcludes: Boolean? = null,
	includesfile: String? = null,
	excludesfile: String? = null,
	caseSensitive: Boolean? = null,
	followSymlinks: Boolean? = null,
	removeNotFollowedSymlinks: Boolean? = null,
	nested: (KDelete.() -> Unit)? = null)
{
	Delete().execute("delete") { task ->
		if (file != null)
			task.setFile(project.resolveFile(file))
		if (dir != null)
			task.setDir(project.resolveFile(dir))
		if (verbose != null)
			task.setVerbose(verbose)
		if (quiet != null)
			task.setQuiet(quiet)
		if (failOnError != null)
			task.setFailOnError(failOnError)
		if (deleteOnExit != null)
			task.setDeleteOnExit(deleteOnExit)
		if (includeEmptyDirs != null)
			task.setIncludeEmptyDirs(includeEmptyDirs)
		if (performGcOnFailedDelete != null)
			task.setPerformGcOnFailedDelete(performGcOnFailedDelete)
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
		if (caseSensitive != null)
			task.setCaseSensitive(caseSensitive)
		if (followSymlinks != null)
			task.setFollowSymlinks(followSymlinks)
		if (removeNotFollowedSymlinks != null)
			task.setRemoveNotFollowedSymlinks(removeNotFollowedSymlinks)
		if (nested != null)
			nested(KDelete(task))
	}
}

class KDelete(override val component: Delete) : IFileSelectorNested, IResourceCollectionNested {
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
