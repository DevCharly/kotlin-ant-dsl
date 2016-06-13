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
import org.apache.tools.ant.types.DirSet
import org.apache.tools.ant.types.FileSet
import org.apache.tools.ant.types.ResourceCollection

//---- ResourceCollection -----------------------------------------------------

interface IResourceCollectionNested {
	val task: Task
	fun _addResourceCollection(res: ResourceCollection)
}

//---- FileSet ----------------------------------------------------------------

interface IFileSetNested : IResourceCollectionNested {
	fun fileset(dir: String, nested: (KFileSet.() -> Unit)? = null) {
		val fileset = FileSet()
		task.project.setProjectReference(fileset);
		fileset.dir = task.resolveFile(dir)
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
	fun dirset(dir: String, nested: (KDirSet.() -> Unit)? = null) {
		val dirset = DirSet()
		task.project.setProjectReference(dirset);
		dirset.dir = task.resolveFile(dir)
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
