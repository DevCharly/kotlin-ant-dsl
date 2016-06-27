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

import org.apache.tools.ant.types.Resource
import org.apache.tools.ant.types.ResourceCollection
import org.apache.tools.ant.types.ZipFileSet
import org.apache.tools.ant.types.selectors.FileSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IZipFileSetNested : INestedComponent {
	fun zipfileset(
		dir: String? = null,
		src: String? = null,
		srcResource: String? = null,
		errorOnMissingArchive: Boolean? = null,
		prefix: String? = null,
		fullpath: String? = null,
		encoding: String? = null,
		fileMode: String? = null,
		dirMode: String? = null,
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
		nested: (KZipFileSet.() -> Unit)? = null)
	{
		_addZipFileSet(ZipFileSet().apply {
			component.project.setProjectReference(this);
			_init(dir, src, srcResource, errorOnMissingArchive,
				prefix, fullpath, encoding, fileMode,
				dirMode, file, includes, excludes,
				includesfile, excludesfile, defaultexcludes, caseSensitive,
				followSymlinks, maxLevelsOfSymlinks, errorOnMissingDir, nested)
		})
	}

	fun _addZipFileSet(value: ZipFileSet)
}

fun ZipFileSet._init(
	dir: String?,
	src: String?,
	srcResource: String?,
	errorOnMissingArchive: Boolean?,
	prefix: String?,
	fullpath: String?,
	encoding: String?,
	fileMode: String?,
	dirMode: String?,
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
	nested: (KZipFileSet.() -> Unit)?)
{
	if (dir != null)
		setDir(project.resolveFile(dir))
	if (src != null)
		setSrc(project.resolveFile(src))
	if (srcResource != null)
		setSrcResource(Resource(srcResource))
	if (errorOnMissingArchive != null)
		setErrorOnMissingArchive(errorOnMissingArchive)
	if (prefix != null)
		setPrefix(prefix)
	if (fullpath != null)
		setFullpath(fullpath)
	if (encoding != null)
		setEncoding(encoding)
	if (fileMode != null)
		setFileMode(fileMode)
	if (dirMode != null)
		setDirMode(dirMode)
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
		nested(KZipFileSet(this))
}

class KZipFileSet(override val component: ZipFileSet) : IFileSelectorNested, IResourceCollectionNested {
	override fun _addFileSelector(value: FileSelector) = component.add(value)
	override fun _addResourceCollection(value: ResourceCollection) = component.addConfigured(value)
}
