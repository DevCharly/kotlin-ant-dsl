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

import org.apache.tools.ant.util.RegexpPatternMapper

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IRegexpPatternMapperNested {
	fun regexpmapper(
		handledirsep: Boolean? = null,
		casesensitive: Boolean? = null,
		from: String? = null,
		to: String? = null)
	{
		_addRegexpPatternMapper(RegexpPatternMapper().apply {
			_init(handledirsep, casesensitive, from, to)
		})
	}

	fun _addRegexpPatternMapper(value: RegexpPatternMapper)
}

fun IFileNameMapperNested.regexpmapper(
	handledirsep: Boolean? = null,
	casesensitive: Boolean? = null,
	from: String? = null,
	to: String? = null)
{
	_addFileNameMapper(RegexpPatternMapper().apply {
		_init(handledirsep, casesensitive, from, to)
	})
}

fun RegexpPatternMapper._init(
	handledirsep: Boolean?,
	casesensitive: Boolean?,
	from: String?,
	to: String?)
{
	if (handledirsep != null)
		setHandleDirSep(handledirsep)
	if (casesensitive != null)
		setCaseSensitive(casesensitive)
	if (from != null)
		setFrom(from)
	if (to != null)
		setTo(to)
}
