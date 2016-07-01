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

import org.apache.tools.ant.taskdefs.Manifest.Attribute
import org.apache.tools.ant.taskdefs.Manifest.Section
import org.apache.tools.ant.taskdefs.ManifestTask

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.manifest(
	file: String? = null,
	encoding: String? = null,
	mode: Mode? = null,
	mergeclasspathattributes: Boolean? = null,
	flattenattributes: Boolean? = null,
	nested: (KManifestTask.() -> Unit)? = null)
{
	ManifestTask().execute("manifest") { task ->
		if (file != null)
			task.setFile(project.resolveFile(file))
		if (encoding != null)
			task.setEncoding(encoding)
		if (mode != null)
			task.setMode(ManifestTask.Mode().apply { this.value = mode.value })
		if (mergeclasspathattributes != null)
			task.setMergeClassPathAttributes(mergeclasspathattributes)
		if (flattenattributes != null)
			task.setFlattenAttributes(flattenattributes)
		if (nested != null)
			nested(KManifestTask(task))
	}
}

class KManifestTask(val component: ManifestTask) :
	ISectionNested,
	IAttributeNested
{
	override fun _addSection(value: Section) = component.addConfiguredSection(value)
	override fun _addAttribute(value: Attribute) = component.addConfiguredAttribute(value)
}

enum class Mode(val value: String) { UPDATE("update"), REPLACE("replace") }
