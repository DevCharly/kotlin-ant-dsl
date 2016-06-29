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

import org.apache.tools.ant.types.mappers.CutDirsMapper

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface ICutDirsMapperNested {
	fun cutdirsmapper(
		dirs: Int? = null,
		from: String? = null,
		to: String? = null)
	{
		_addCutDirsMapper(CutDirsMapper().apply {
			_init(dirs, from, to)
		})
	}

	fun _addCutDirsMapper(value: CutDirsMapper)
}

fun IFileNameMapperNested.cutdirsmapper(
	dirs: Int? = null,
	from: String? = null,
	to: String? = null)
{
	_addFileNameMapper(CutDirsMapper().apply {
		_init(dirs, from, to)
	})
}

fun CutDirsMapper._init(
	dirs: Int?,
	from: String?,
	to: String?)
{
	if (dirs != null)
		setDirs(dirs)
	if (from != null)
		setFrom(from)
	if (to != null)
		setTo(to)
}
