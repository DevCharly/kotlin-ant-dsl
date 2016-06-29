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

import org.apache.tools.ant.types.selectors.ContainsSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IContainsSelectorNested : INestedComponent {
	fun contains(
		text: String? = null,
		encoding: String? = null,
		casesensitive: Boolean? = null,
		ignorewhitespace: Boolean? = null,
		error: String? = null)
	{
		_addContainsSelector(ContainsSelector().apply {
			component.project.setProjectReference(this);
			_init(text, encoding, casesensitive, ignorewhitespace,
				error)
		})
	}

	fun _addContainsSelector(value: ContainsSelector)
}

fun ContainsSelector._init(
	text: String?,
	encoding: String?,
	casesensitive: Boolean?,
	ignorewhitespace: Boolean?,
	error: String?)
{
	if (text != null)
		setText(text)
	if (encoding != null)
		setEncoding(encoding)
	if (casesensitive != null)
		setCasesensitive(casesensitive)
	if (ignorewhitespace != null)
		setIgnorewhitespace(ignorewhitespace)
	if (error != null)
		setError(error)
}
