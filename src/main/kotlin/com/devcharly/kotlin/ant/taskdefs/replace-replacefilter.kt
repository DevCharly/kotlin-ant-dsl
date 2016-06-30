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

import org.apache.tools.ant.taskdefs.Replace.Replacefilter

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/


fun Replacefilter._init(
	token: String?,
	value: String?,
	property: String?,
	nested: (KReplacefilter.() -> Unit)?)
{
	if (token != null)
		setToken(token)
	if (value != null)
		setValue(value)
	if (property != null)
		setProperty(property)
	if (nested != null)
		nested(KReplacefilter(this))
}

class KReplacefilter(val component: Replacefilter) {
	fun replacetoken(expandproperties: Boolean? = null, nested: (KNestedString.() -> Unit)? = null) {
		component.createReplaceToken().apply {
			_init(expandproperties, nested)
		}
	}
	fun replacevalue(expandproperties: Boolean? = null, nested: (KNestedString.() -> Unit)? = null) {
		component.createReplaceValue().apply {
			_init(expandproperties, nested)
		}
	}
}
