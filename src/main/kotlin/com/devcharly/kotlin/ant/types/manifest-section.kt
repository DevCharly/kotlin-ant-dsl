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

import org.apache.tools.ant.taskdefs.Manifest.Attribute
import org.apache.tools.ant.taskdefs.Manifest.Section

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface ISectionNested {
	fun section(
		name: String? = null,
		nested: (KSection.() -> Unit)? = null)
	{
		_addSection(Section().apply {
			_init(name, nested)
		})
	}

	fun _addSection(value: Section)
}

fun Section._init(
	name: String?,
	nested: (KSection.() -> Unit)?)
{
	if (name != null)
		setName(name)
	if (nested != null)
		nested(KSection(this))
}

class KSection(val component: Section) :
	IAttributeNested
{
	override fun _addAttribute(value: Attribute) = component.addConfiguredAttribute(value)
}
