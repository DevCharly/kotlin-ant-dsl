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

import org.apache.tools.ant.types.PatternSet

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IPatternSetNested : INestedComponent {
	fun patternset(
		includes: String? = null,
		excludes: String? = null,
		includesfile: String? = null,
		excludesfile: String? = null,
		nested: (KPatternSet.() -> Unit)? = null)
	{
		_addPatternSet(PatternSet().apply {
			component.project.setProjectReference(this);
			_init(includes, excludes, includesfile, excludesfile, nested)
		})
	}

	fun _addPatternSet(value: PatternSet)
}

fun PatternSet._init(
	includes: String?,
	excludes: String?,
	includesfile: String?,
	excludesfile: String?,
	nested: (KPatternSet.() -> Unit)?)
{
	if (includes != null)
		setIncludes(includes)
	if (excludes != null)
		setExcludes(excludes)
	if (includesfile != null)
		setIncludesfile(project.resolveFile(includesfile))
	if (excludesfile != null)
		setExcludesfile(project.resolveFile(excludesfile))
	if (nested != null)
		nested(KPatternSet(this))
}

class KPatternSet(override val component: PatternSet) :
	IPatternSetNested
{
	fun include(name: String? = null, If: String? = null, unless: String? = null) {
		component.createInclude().apply {
			_init(name, If, unless)
		}
	}
	fun includesfile(name: String? = null, If: String? = null, unless: String? = null) {
		component.createIncludesFile().apply {
			_init(name, If, unless)
		}
	}
	fun exclude(name: String? = null, If: String? = null, unless: String? = null) {
		component.createExclude().apply {
			_init(name, If, unless)
		}
	}
	fun excludesfile(name: String? = null, If: String? = null, unless: String? = null) {
		component.createExcludesFile().apply {
			_init(name, If, unless)
		}
	}
	fun invert(includes: String? = null, excludes: String? = null, includesfile: String? = null, excludesfile: String? = null, nested: (KPatternSet.() -> Unit)? = null) {
		component.addConfiguredInvert(PatternSet().apply {
			component.project.setProjectReference(this)
			_init(includes, excludes, includesfile, excludesfile, nested)
		})
	}
	override fun _addPatternSet(value: PatternSet) = component.addConfiguredPatternset(value)
}
