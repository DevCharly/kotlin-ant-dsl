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

import org.apache.tools.ant.taskdefs.Copy
import org.apache.tools.ant.types.ResourceCollection
import org.apache.tools.ant.util.FileNameMapper

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.copy(
	file: String? = null,
	tofile: String? = null,
	todir: String? = null,
	preservelastmodified: Boolean? = null,
	filtering: Boolean? = null,
	overwrite: Boolean? = null,
	force: Boolean? = null,
	flatten: Boolean? = null,
	verbose: Boolean? = null,
	includeemptydirs: Boolean? = null,
	quiet: Boolean? = null,
	enablemultiplemappings: Boolean? = null,
	failonerror: Boolean? = null,
	encoding: String? = null,
	outputencoding: String? = null,
	granularity: Long? = null,
	nested: (KCopy.() -> Unit)? = null)
{
	Copy().execute("copy") { task ->
		if (file != null)
			task.setFile(project.resolveFile(file))
		if (tofile != null)
			task.setTofile(project.resolveFile(tofile))
		if (todir != null)
			task.setTodir(project.resolveFile(todir))
		if (preservelastmodified != null)
			task.setPreserveLastModified(preservelastmodified)
		if (filtering != null)
			task.setFiltering(filtering)
		if (overwrite != null)
			task.setOverwrite(overwrite)
		if (force != null)
			task.setForce(force)
		if (flatten != null)
			task.setFlatten(flatten)
		if (verbose != null)
			task.setVerbose(verbose)
		if (includeemptydirs != null)
			task.setIncludeEmptyDirs(includeemptydirs)
		if (quiet != null)
			task.setQuiet(quiet)
		if (enablemultiplemappings != null)
			task.setEnableMultipleMappings(enablemultiplemappings)
		if (failonerror != null)
			task.setFailOnError(failonerror)
		if (encoding != null)
			task.setEncoding(encoding)
		if (outputencoding != null)
			task.setOutputEncoding(outputencoding)
		if (granularity != null)
			task.setGranularity(granularity)
		if (nested != null)
			nested(KCopy(task))
	}
}

class KCopy(override val component: Copy) :
	IFileNameMapperNested,
	IResourceCollectionNested
{
	override fun _addFileNameMapper(value: FileNameMapper) = component.add(value)
	override fun _addResourceCollection(value: ResourceCollection) = component.add(value)
}
