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

import org.apache.tools.ant.types.selectors.DifferentSelector
import org.apache.tools.ant.util.FileNameMapper

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IDifferentSelectorNested : INestedComponent {
	fun different(
		ignorefiletimes: Boolean? = null,
		ignorecontents: Boolean? = null,
		targetdir: String? = null,
		granularity: Int? = null,
		error: String? = null,
		nested: (KDifferentSelector.() -> Unit)? = null)
	{
		_addDifferentSelector(DifferentSelector().apply {
			component.project.setProjectReference(this);
			_init(ignorefiletimes, ignorecontents, targetdir, granularity,
				error, nested)
		})
	}

	fun _addDifferentSelector(value: DifferentSelector)
}

fun DifferentSelector._init(
	ignorefiletimes: Boolean?,
	ignorecontents: Boolean?,
	targetdir: String?,
	granularity: Int?,
	error: String?,
	nested: (KDifferentSelector.() -> Unit)?)
{
	if (ignorefiletimes != null)
		setIgnoreFileTimes(ignorefiletimes)
	if (ignorecontents != null)
		setIgnoreContents(ignorecontents)
	if (targetdir != null)
		setTargetdir(project.resolveFile(targetdir))
	if (granularity != null)
		setGranularity(granularity)
	if (error != null)
		setError(error)
	if (nested != null)
		nested(KDifferentSelector(this))
}

class KDifferentSelector(val component: DifferentSelector) :
	IFileNameMapperNested
{
	override fun _addFileNameMapper(value: FileNameMapper) = component.addConfigured(value)
}
