/*
 * Copyright 2016 the original author or authors.
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

import org.apache.tools.ant.Task
import org.apache.tools.ant.types.*

//---- ResourceCollection -----------------------------------------------------

interface IResourceCollectionNested {
	val task: Task
	fun _addResourceCollection(res: ResourceCollection)
}

//---- AbstractFileSet --------------------------------------------------------

fun AbstractFileSet._init(includes: String?, includesfile: String?,
						  excludes: String?, excludesfile: String?,
						  casesensitive: Boolean, followsymlinks: Boolean,
						  erroronmissingdir: Boolean)
{
	if (includes != null)
		setIncludes(includes)
	if (includesfile != null)
		setIncludesfile(project.resolveFile(includesfile))

	if (excludes != null)
		setExcludes(excludes)
	if (excludesfile != null)
		setExcludesfile(project.resolveFile(excludesfile))

	if (!casesensitive)
		setCaseSensitive(casesensitive)
	if (!followsymlinks)
		setFollowSymlinks(followsymlinks)
	if( !erroronmissingdir)
		setErrorOnMissingDir(erroronmissingdir)
}

//---- FileSet ----------------------------------------------------------------

interface IFileSetNested : IResourceCollectionNested {
	fun fileset(dir: String? = null, file: String? = null,
				defaultexcludes: Boolean = true,
				includes: String? = null, includesfile: String? = null,
				excludes: String? = null, excludesfile: String? = null,
				casesensitive: Boolean = true, followsymlinks: Boolean = true,
				erroronmissingdir: Boolean = true,
				nested: (KFileSet.() -> Unit)? = null)
	{
		val fileset = FileSet()
		task.project.setProjectReference(fileset);
		if (dir != null)
			fileset.dir = task.resolveFile(dir)
		if (file != null)
			fileset.setFile(task.resolveFile(file))
		if (!defaultexcludes)
			fileset.defaultexcludes = defaultexcludes
		fileset._init(includes, includesfile, excludes, excludesfile, casesensitive, followsymlinks, erroronmissingdir)
		if (nested != null)
			nested(KFileSet(fileset))
		_addResourceCollection(fileset)
	}
}

class KFileSet(val fileset: FileSet) {
	fun include(name: String) {
		fileset.createInclude().name = name
	}

	fun exclude(name: String) {
		fileset.createExclude().name = name
	}
}

//---- DirSet -----------------------------------------------------------------

interface IDirSetNested : IResourceCollectionNested {
	fun dirset(dir: String,
			   includes: String? = null, includesfile: String? = null,
			   excludes: String? = null, excludesfile: String? = null,
			   casesensitive: Boolean = true, followsymlinks: Boolean = true,
			   erroronmissingdir: Boolean = true,
			   nested: (KDirSet.() -> Unit)? = null)
	{
		val dirset = DirSet()
		task.project.setProjectReference(dirset);
		if (dir != null)
			dirset.dir = task.resolveFile(dir)
		dirset._init(includes, includesfile, excludes, excludesfile, casesensitive, followsymlinks, erroronmissingdir)
		if (nested != null)
			nested(KDirSet(dirset))
		_addResourceCollection(dirset)
	}
}

class KDirSet(val dirset: DirSet) {
	fun include(name: String) {
		dirset.createInclude().name = name
	}

	fun exclude(name: String) {
		dirset.createExclude().name = name
	}
}

//---- ArchiveFileSet ---------------------------------------------------------

fun ArchiveFileSet._init(prefix: String?, fullpath: String?, src: String?,
						 filemode: String?, dirmode: String?,
						 encoding: String?, erroronmissingarchive: Boolean)
{
	if (prefix != null)
		setPrefix(prefix)
	if (fullpath != null)
		setFullpath(fullpath)
	if (src != null)
		setSrc(project.resolveFile(src))
	if (filemode != null)
		setFileMode(filemode)
	if (dirmode != null)
		setDirMode(dirmode)
	if (encoding != null)
		setEncoding(encoding)
	if (!erroronmissingarchive)
		setErrorOnMissingArchive(erroronmissingarchive)
}

//---- ZipFileSet -------------------------------------------------------------

interface IZipFileSetNested : IFileSetNested {
	fun zipfileset(
			// fileset
			dir: String? = null, file: String? = null,
			defaultexcludes: Boolean = true,
			includes: String? = null, includesfile: String? = null,
			excludes: String? = null, excludesfile: String? = null,
			casesensitive: Boolean = true, followsymlinks: Boolean = true,
			erroronmissingdir: Boolean = true,
			// zipfileset
			prefix: String? = null, fullpath: String? = null, src: String? = null,
			filemode: String? = null, dirmode: String? = null,
			encoding: String? = null, erroronmissingarchive: Boolean = true,
			nested: (KFileSet.() -> Unit)? = null)
	{
		val zipfileset = ZipFileSet()
		task.project.setProjectReference(zipfileset);
		if (dir != null)
			zipfileset.dir = task.resolveFile(dir)
		if (file != null)
			zipfileset.setFile(task.resolveFile(file))
		if (!defaultexcludes)
			zipfileset.defaultexcludes = defaultexcludes
		zipfileset._init(includes, includesfile, excludes, excludesfile, casesensitive, followsymlinks, erroronmissingdir)
		zipfileset._init(prefix, fullpath, src, filemode, dirmode, encoding, erroronmissingarchive)
		if (nested != null)
			nested(KFileSet(zipfileset))
		_addResourceCollection(zipfileset)
	}
}
