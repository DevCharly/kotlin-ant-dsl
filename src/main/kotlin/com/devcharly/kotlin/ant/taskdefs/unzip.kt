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

import org.apache.tools.ant.taskdefs.Expand
import org.apache.tools.ant.types.PatternSet
import org.apache.tools.ant.types.ResourceCollection
import org.apache.tools.ant.util.FileNameMapper

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.unzip(
	src: String? = null,
	dest: String? = null,
	overwrite: Boolean? = null,
	encoding: String? = null,
	failonemptyarchive: Boolean? = null,
	stripabsolutepathspec: Boolean? = null,
	scanforunicodeextrafields: Boolean? = null,
	nested: (KUnzip.() -> Unit)? = null)
{
	Expand().execute("unzip") { task ->
		if (src != null)
			task.setSrc(project.resolveFile(src))
		if (dest != null)
			task.setDest(project.resolveFile(dest))
		if (overwrite != null)
			task.setOverwrite(overwrite)
		if (encoding != null)
			task.setEncoding(encoding)
		if (failonemptyarchive != null)
			task.setFailOnEmptyArchive(failonemptyarchive)
		if (stripabsolutepathspec != null)
			task.setStripAbsolutePathSpec(stripabsolutepathspec)
		if (scanforunicodeextrafields != null)
			task.setScanForUnicodeExtraFields(scanforunicodeextrafields)
		if (nested != null)
			nested(KUnzip(task))
	}
}

class KUnzip(override val component: Expand) :
	IFileNameMapperNested,
	IResourceCollectionNested,
	IPatternSetNested
{
	override fun _addFileNameMapper(value: FileNameMapper) = component.add(value)
	override fun _addResourceCollection(value: ResourceCollection) = component.add(value)
	override fun _addPatternSet(value: PatternSet) = component.addPatternset(value)
}
