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

import org.apache.tools.ant.types.selectors.FilenameSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IFilenameSelectorNested : INestedComponent {
	fun filename(
		name: String? = null,
		regex: String? = null,
		casesensitive: Boolean? = null,
		negate: Boolean? = null,
		error: String? = null)
	{
		_addFilenameSelector(FilenameSelector().apply {
			component.project.setProjectReference(this);
			_init(name, regex, casesensitive, negate,
				error)
		})
	}

	fun _addFilenameSelector(value: FilenameSelector)
}

fun FilenameSelector._init(
	name: String?,
	regex: String?,
	casesensitive: Boolean?,
	negate: Boolean?,
	error: String?)
{
	if (name != null)
		setName(name)
	if (regex != null)
		setRegex(regex)
	if (casesensitive != null)
		setCasesensitive(casesensitive)
	if (negate != null)
		setNegate(negate)
	if (error != null)
		setError(error)
}
