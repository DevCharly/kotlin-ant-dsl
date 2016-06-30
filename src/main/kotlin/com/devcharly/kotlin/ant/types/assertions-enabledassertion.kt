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

import org.apache.tools.ant.types.Assertions.EnabledAssertion

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IEnabledAssertionNested {
	fun enable(
		Class: String? = null,
		Package: String? = null)
	{
		_addEnabledAssertion(EnabledAssertion().apply {
			_init(Class, Package)
		})
	}

	fun _addEnabledAssertion(value: EnabledAssertion)
}

fun EnabledAssertion._init(
	Class: String?,
	Package: String?)
{
	if (Class != null)
		setClass(Class)
	if (Package != null)
		setPackage(Package)
}
