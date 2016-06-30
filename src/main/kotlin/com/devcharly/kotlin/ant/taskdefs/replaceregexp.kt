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

import org.apache.tools.ant.taskdefs.optional.ReplaceRegExp
import org.apache.tools.ant.types.ResourceCollection

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.replaceregexp(
	file: String? = null,
	match: String? = null,
	replace: String? = null,
	flags: String? = null,
	byline: Boolean? = null,
	encoding: String? = null,
	preservelastmodified: Boolean? = null,
	nested: (KReplaceRegExp.() -> Unit)? = null)
{
	ReplaceRegExp().execute("replaceregexp") { task ->
		if (file != null)
			task.setFile(project.resolveFile(file))
		if (match != null)
			task.setMatch(match)
		if (replace != null)
			task.setReplace(replace)
		if (flags != null)
			task.setFlags(flags)
		if (byline != null)
			task.setByLine(byline)
		if (encoding != null)
			task.setEncoding(encoding)
		if (preservelastmodified != null)
			task.setPreserveLastModified(preservelastmodified)
		if (nested != null)
			nested(KReplaceRegExp(task))
	}
}

class KReplaceRegExp(override val component: ReplaceRegExp) :
	IResourceCollectionNested
{
	fun regexp(pattern: String? = null) {
		component.createRegexp().apply {
			component.project.setProjectReference(this)
			_init(pattern)
		}
	}
	fun substitution(expression: String? = null) {
		component.createSubstitution().apply {
			component.project.setProjectReference(this)
			_init(expression)
		}
	}
	override fun _addResourceCollection(value: ResourceCollection) = component.addConfigured(value)
}
