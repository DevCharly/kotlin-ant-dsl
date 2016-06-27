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

import org.apache.tools.ant.types.DirSet
import org.apache.tools.ant.types.selectors.FileSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IDirSetNested : INestedComponent {
	fun dirset(
		dir: String? = null,
		file: String? = null,
		includes: String? = null,
		excludes: String? = null,
		includesfile: String? = null,
		excludesfile: String? = null,
		defaultexcludes: Boolean? = null,
		caseSensitive: Boolean? = null,
		followSymlinks: Boolean? = null,
		maxLevelsOfSymlinks: Int? = null,
		errorOnMissingDir: Boolean? = null,
		nested: (KDirSet.() -> Unit)? = null)
	{
		_addDirSet(DirSet().apply {
			component.project.setProjectReference(this);
			_init(dir, file, includes, excludes,
				includesfile, excludesfile, defaultexcludes, caseSensitive,
				followSymlinks, maxLevelsOfSymlinks, errorOnMissingDir, nested)
		})
	}

	fun _addDirSet(value: DirSet)
}

fun IResourceCollectionNested.dirset(
	dir: String? = null,
	file: String? = null,
	includes: String? = null,
	excludes: String? = null,
	includesfile: String? = null,
	excludesfile: String? = null,
	defaultexcludes: Boolean? = null,
	caseSensitive: Boolean? = null,
	followSymlinks: Boolean? = null,
	maxLevelsOfSymlinks: Int? = null,
	errorOnMissingDir: Boolean? = null,
	nested: (KDirSet.() -> Unit)? = null)
{
	_addResourceCollection(DirSet().apply {
		component.project.setProjectReference(this);
		_init(dir, file, includes, excludes,
			includesfile, excludesfile, defaultexcludes, caseSensitive,
			followSymlinks, maxLevelsOfSymlinks, errorOnMissingDir, nested)
	})
}

fun DirSet._init(
	dir: String?,
	file: String?,
	includes: String?,
	excludes: String?,
	includesfile: String?,
	excludesfile: String?,
	defaultexcludes: Boolean?,
	caseSensitive: Boolean?,
	followSymlinks: Boolean?,
	maxLevelsOfSymlinks: Int?,
	errorOnMissingDir: Boolean?,
	nested: (KDirSet.() -> Unit)?)
{
	if (dir != null)
		setDir(project.resolveFile(dir))
	if (file != null)
		setFile(project.resolveFile(file))
	if (includes != null)
		setIncludes(includes)
	if (excludes != null)
		setExcludes(excludes)
	if (includesfile != null)
		setIncludesfile(project.resolveFile(includesfile))
	if (excludesfile != null)
		setExcludesfile(project.resolveFile(excludesfile))
	if (defaultexcludes != null)
		setDefaultexcludes(defaultexcludes)
	if (caseSensitive != null)
		setCaseSensitive(caseSensitive)
	if (followSymlinks != null)
		setFollowSymlinks(followSymlinks)
	if (maxLevelsOfSymlinks != null)
		setMaxLevelsOfSymlinks(maxLevelsOfSymlinks)
	if (errorOnMissingDir != null)
		setErrorOnMissingDir(errorOnMissingDir)
	if (nested != null)
		nested(KDirSet(this))
}

class KDirSet(override val component: DirSet) : IFileSelectorNested {
	override fun _addFileSelector(value: FileSelector) = component.add(value)
}
