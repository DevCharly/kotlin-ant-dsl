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

import org.apache.tools.ant.types.selectors.TypeSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface ITypeSelectorNested : INestedComponent {
	fun type(
		type: FileType? = null,
		error: String? = null)
	{
		_addTypeSelector(TypeSelector().apply {
			component.project.setProjectReference(this);
			_init(type, error)
		})
	}

	fun _addTypeSelector(value: TypeSelector)
}

fun TypeSelector._init(
	type: FileType?,
	error: String?)
{
	if (type != null)
		setType(TypeSelector.FileType().apply { value = type.value })
	if (error != null)
		setError(error)
}

enum class FileType(val value: String) { FILE("file"), DIR("dir") }
