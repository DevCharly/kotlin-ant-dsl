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

import org.apache.tools.ant.util.CompositeMapper
import org.apache.tools.ant.util.FileNameMapper

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface ICompositeMapperNested {
	fun compositemapper(
		from: String? = null,
		to: String? = null,
		nested: (KCompositeMapper.() -> Unit)? = null)
	{
		_addCompositeMapper(CompositeMapper().apply {
			_init(from, to, nested)
		})
	}

	fun _addCompositeMapper(value: CompositeMapper)
}

fun IFileNameMapperNested.compositemapper(
	from: String? = null,
	to: String? = null,
	nested: (KCompositeMapper.() -> Unit)? = null)
{
	_addFileNameMapper(CompositeMapper().apply {
		_init(from, to, nested)
	})
}

fun CompositeMapper._init(
	from: String?,
	to: String?,
	nested: (KCompositeMapper.() -> Unit)?)
{
	if (from != null)
		setFrom(from)
	if (to != null)
		setTo(to)
	if (nested != null)
		nested(KCompositeMapper(this))
}

class KCompositeMapper(val component: CompositeMapper) :
	IFileNameMapperNested
{
	override fun _addFileNameMapper(value: FileNameMapper) = component.addConfigured(value)
}
