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

import org.apache.tools.ant.types.selectors.DateSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IDateSelectorNested : INestedComponent {
	fun date(
		millis: Long? = null,
		datetime: String? = null,
		checkdirs: Boolean? = null,
		granularity: Int? = null,
		When: TimeComparison? = null,
		pattern: String? = null,
		error: String? = null)
	{
		_addDateSelector(DateSelector().apply {
			component.project.setProjectReference(this);
			_init(millis, datetime, checkdirs, granularity,
				When, pattern, error)
		})
	}

	fun _addDateSelector(value: DateSelector)
}

fun DateSelector._init(
	millis: Long?,
	datetime: String?,
	checkdirs: Boolean?,
	granularity: Int?,
	When: TimeComparison?,
	pattern: String?,
	error: String?)
{
	if (millis != null)
		setMillis(millis)
	if (datetime != null)
		setDatetime(datetime)
	if (checkdirs != null)
		setCheckdirs(checkdirs)
	if (granularity != null)
		setGranularity(granularity)
	if (When != null)
		setWhen(org.apache.tools.ant.types.TimeComparison().apply { this.value = When.value })
	if (pattern != null)
		setPattern(pattern)
	if (error != null)
		setError(error)
}
