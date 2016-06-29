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

import org.apache.tools.ant.util.FlatFileNameMapper

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IFlatFileNameMapperNested {
	fun flattenmapper(
		from: String? = null,
		to: String? = null)
	{
		_addFlatFileNameMapper(FlatFileNameMapper().apply {
			_init(from, to)
		})
	}

	fun _addFlatFileNameMapper(value: FlatFileNameMapper)
}

fun IFileNameMapperNested.flattenmapper(
	from: String? = null,
	to: String? = null)
{
	_addFileNameMapper(FlatFileNameMapper().apply {
		_init(from, to)
	})
}

fun FlatFileNameMapper._init(
	from: String?,
	to: String?)
{
	if (from != null)
		setFrom(from)
	if (to != null)
		setTo(to)
}
