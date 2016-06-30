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

import org.apache.tools.ant.types.selectors.SizeSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface ISizeSelectorNested : INestedComponent {
	fun size(
		value: Long? = null,
		units: ByteUnits? = null,
		When: SizeComparisons? = null,
		error: String? = null)
	{
		_addSizeSelector(SizeSelector().apply {
			component.project.setProjectReference(this);
			_init(value, units, When, error)
		})
	}

	fun _addSizeSelector(value: SizeSelector)
}

fun SizeSelector._init(
	value: Long?,
	units: ByteUnits?,
	When: SizeComparisons?,
	error: String?)
{
	if (value != null)
		setValue(value)
	if (units != null)
		setUnits(SizeSelector.ByteUnits().apply { this.value = units.value })
	if (When != null)
		setWhen(SizeSelector.SizeComparisons().apply { this.value = When.value })
	if (error != null)
		setError(error)
}

enum class ByteUnits(val value: String) { K("K"), KILO("kilo"), KI("Ki"), KIBI("kibi"), M("M"), MEGA("mega"), MI("Mi"), MEBI("mebi"), G("G"), GIGA("giga"), GI("Gi"), GIBI("gibi"), T("T"), TERA("tera"), TI("Ti"), TEBI("tebi") }
enum class SizeComparisons(val value: String) { EQUAL("equal"), GREATER("greater"), LESS("less"), NE("ne"), GE("ge"), LE("le"), EQ("eq"), GT("gt"), LT("lt"), MORE("more") }
