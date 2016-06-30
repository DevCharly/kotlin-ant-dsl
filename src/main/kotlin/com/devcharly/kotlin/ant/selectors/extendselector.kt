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

import org.apache.tools.ant.types.Parameter
import org.apache.tools.ant.types.Path
import org.apache.tools.ant.types.Reference
import org.apache.tools.ant.types.selectors.ExtendSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IExtendSelectorNested : INestedComponent {
	fun custom(
		classname: String? = null,
		classpath: String? = null,
		classpathref: String? = null,
		error: String? = null,
		nested: (KExtendSelector.() -> Unit)? = null)
	{
		_addExtendSelector(ExtendSelector().apply {
			component.project.setProjectReference(this);
			_init(classname, classpath, classpathref, error, nested)
		})
	}

	fun _addExtendSelector(value: ExtendSelector)
}

fun ExtendSelector._init(
	classname: String?,
	classpath: String?,
	classpathref: String?,
	error: String?,
	nested: (KExtendSelector.() -> Unit)?)
{
	if (classname != null)
		setClassname(classname)
	if (classpath != null)
		setClasspath(Path(project, classpath))
	if (classpathref != null)
		setClasspathref(Reference(project, classpathref))
	if (error != null)
		setError(error)
	if (nested != null)
		nested(KExtendSelector(this))
}

class KExtendSelector(val component: ExtendSelector) {
	fun param(name: String? = null, type: String? = null, value: String? = null) {
		component.addParam(Parameter().apply {
			_init(name, type, value)
		})
	}
	fun classpath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createClasspath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
}
