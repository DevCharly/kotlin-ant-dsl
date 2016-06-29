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

import org.apache.tools.ant.types.selectors.DependSelector
import org.apache.tools.ant.util.FileNameMapper

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IDependSelectorNested : INestedComponent {
	fun depend(
		targetdir: String? = null,
		granularity: Int? = null,
		error: String? = null,
		nested: (KDependSelector.() -> Unit)? = null)
	{
		_addDependSelector(DependSelector().apply {
			component.project.setProjectReference(this);
			_init(targetdir, granularity, error, nested)
		})
	}

	fun _addDependSelector(value: DependSelector)
}

fun DependSelector._init(
	targetdir: String?,
	granularity: Int?,
	error: String?,
	nested: (KDependSelector.() -> Unit)?)
{
	if (targetdir != null)
		setTargetdir(project.resolveFile(targetdir))
	if (granularity != null)
		setGranularity(granularity)
	if (error != null)
		setError(error)
	if (nested != null)
		nested(KDependSelector(this))
}

class KDependSelector(val component: DependSelector) :
	IFileNameMapperNested
{
	override fun _addFileNameMapper(value: FileNameMapper) = component.addConfigured(value)
}
