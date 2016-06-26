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

import org.apache.tools.ant.types.FileSet

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IFileSetNested : INestedComponent {
	fun fileset(
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
		errorOnMissingDir: Boolean? = null)
	{
		_addFileSet(FileSet().apply {
			component.project.setProjectReference(this);
			_init(dir, file, includes, excludes,
				includesfile, excludesfile, defaultexcludes, caseSensitive,
				followSymlinks, maxLevelsOfSymlinks, errorOnMissingDir)
		})
	}

	fun _addFileSet(value: FileSet)
}

fun FileSet._init(
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
	errorOnMissingDir: Boolean?)
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
}
