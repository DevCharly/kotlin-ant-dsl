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

import org.apache.tools.ant.util.FileNameMapper
import org.apache.tools.ant.util.FirstMatchMapper

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IFirstMatchMapperNested {
	fun firstmatchmapper(
		from: String? = null,
		to: String? = null,
		nested: (KFirstMatchMapper.() -> Unit)? = null)
	{
		_addFirstMatchMapper(FirstMatchMapper().apply {
			_init(from, to, nested)
		})
	}

	fun _addFirstMatchMapper(value: FirstMatchMapper)
}

fun IFileNameMapperNested.firstmatchmapper(
	from: String? = null,
	to: String? = null,
	nested: (KFirstMatchMapper.() -> Unit)? = null)
{
	_addFileNameMapper(FirstMatchMapper().apply {
		_init(from, to, nested)
	})
}

fun FirstMatchMapper._init(
	from: String?,
	to: String?,
	nested: (KFirstMatchMapper.() -> Unit)?)
{
	if (from != null)
		setFrom(from)
	if (to != null)
		setTo(to)
	if (nested != null)
		nested(KFirstMatchMapper(this))
}

class KFirstMatchMapper(val component: FirstMatchMapper) :
	IFileNameMapperNested
{
	override fun _addFileNameMapper(value: FileNameMapper) = component.addConfigured(value)
}
