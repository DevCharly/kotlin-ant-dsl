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
import org.apache.tools.ant.types.AbstractFileSet
import org.apache.tools.ant.types.DirSet
import org.apache.tools.ant.types.FileSet
import org.apache.tools.ant.types.ResourceCollection

//---- ResourceCollection -----------------------------------------------------

interface IResourceCollectionNested {
	val task: Task
	fun _addResourceCollection(res: ResourceCollection)
}

//---- AbstractFileSet --------------------------------------------------------

interface IAbstractFileSetNested : IResourceCollectionNested {
	fun _init(abstractFileSet: AbstractFileSet,
			  includes: String?, includesfile: String?,
			  excludes: String?, excludesfile: String?,
			  casesensitive: Boolean, followsymlinks: Boolean,
			  erroronmissingdir: Boolean)
	{
		if (includes != null)
			abstractFileSet.setIncludes(includes)
		if (includesfile != null)
			abstractFileSet.setIncludesfile(task.resolveFile(includesfile))

		if (excludes != null)
			abstractFileSet.setExcludes(excludes)
		if (excludesfile != null)
			abstractFileSet.setExcludesfile(task.resolveFile(excludesfile))

		if (!casesensitive)
			abstractFileSet.setCaseSensitive(casesensitive)
		if (!followsymlinks)
			abstractFileSet.setFollowSymlinks(followsymlinks)
		if( !erroronmissingdir)
			abstractFileSet.errorOnMissingDir = erroronmissingdir
	}
}

//---- FileSet ----------------------------------------------------------------

interface IFileSetNested : IAbstractFileSetNested {
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
		_init(fileset, includes, includesfile, excludes, excludesfile, casesensitive, followsymlinks, erroronmissingdir)
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

interface IDirSetNested : IAbstractFileSetNested {
	fun dirset(dir: String,
			   includes: String? = null, includesfile: String? = null,
			   excludes: String? = null, excludesfile: String? = null,
			   casesensitive: Boolean = true, followsymlinks: Boolean = true,
			   erroronmissingdir: Boolean = true,
			   nested: (KDirSet.() -> Unit)? = null)
	{
		val dirset = DirSet()
		task.project.setProjectReference(dirset);
		dirset.dir = task.resolveFile(dir)
		_init(dirset, includes, includesfile, excludes, excludesfile, casesensitive, followsymlinks, erroronmissingdir)
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
