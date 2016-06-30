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

import org.apache.tools.ant.types.Assertions
import org.apache.tools.ant.types.Assertions.DisabledAssertion
import org.apache.tools.ant.types.Assertions.EnabledAssertion

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IAssertionsNested : INestedComponent {
	fun assertions(
		enablesystemassertions: Boolean? = null,
		nested: (KAssertions.() -> Unit)? = null)
	{
		_addAssertions(Assertions().apply {
			component.project.setProjectReference(this);
			_init(enablesystemassertions, nested)
		})
	}

	fun _addAssertions(value: Assertions)
}

fun Assertions._init(
	enablesystemassertions: Boolean?,
	nested: (KAssertions.() -> Unit)?)
{
	if (enablesystemassertions != null)
		setEnableSystemAssertions(enablesystemassertions)
	if (nested != null)
		nested(KAssertions(this))
}

class KAssertions(val component: Assertions) :
	IEnabledAssertionNested,
	IDisabledAssertionNested
{
	override fun _addEnabledAssertion(value: EnabledAssertion) = component.addEnable(value)
	override fun _addDisabledAssertion(value: DisabledAssertion) = component.addDisable(value)
}
