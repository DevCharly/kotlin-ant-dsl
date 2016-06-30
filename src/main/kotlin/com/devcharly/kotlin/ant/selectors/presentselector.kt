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

import org.apache.tools.ant.types.selectors.PresentSelector
import org.apache.tools.ant.util.FileNameMapper

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IPresentSelectorNested : INestedComponent {
	fun present(
		targetdir: String? = null,
		present: FilePresence? = null,
		error: String? = null,
		nested: (KPresentSelector.() -> Unit)? = null)
	{
		_addPresentSelector(PresentSelector().apply {
			component.project.setProjectReference(this);
			_init(targetdir, present, error, nested)
		})
	}

	fun _addPresentSelector(value: PresentSelector)
}

fun PresentSelector._init(
	targetdir: String?,
	present: FilePresence?,
	error: String?,
	nested: (KPresentSelector.() -> Unit)?)
{
	if (targetdir != null)
		setTargetdir(project.resolveFile(targetdir))
	if (present != null)
		setPresent(PresentSelector.FilePresence().apply { this.value = present.value })
	if (error != null)
		setError(error)
	if (nested != null)
		nested(KPresentSelector(this))
}

class KPresentSelector(val component: PresentSelector) :
	IFileNameMapperNested
{
	override fun _addFileNameMapper(value: FileNameMapper) = component.addConfigured(value)
}

enum class FilePresence(val value: String) { SRCONLY("srconly"), BOTH("both") }
