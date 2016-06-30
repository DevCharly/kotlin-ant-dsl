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

import org.apache.tools.ant.types.Path
import org.apache.tools.ant.types.Reference
import org.apache.tools.ant.types.optional.ScriptSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IScriptSelectorNested : INestedComponent {
	fun scriptselector(
		manager: String? = null,
		language: String? = null,
		src: String? = null,
		classpath: String? = null,
		classpathref: String? = null,
		setbeans: Boolean? = null,
		selected: Boolean? = null,
		error: String? = null,
		nested: (KScriptselector.() -> Unit)? = null)
	{
		_addScriptSelector(ScriptSelector().apply {
			component.project.setProjectReference(this);
			_init(manager, language, src, classpath,
				classpathref, setbeans, selected, error, nested)
		})
	}

	fun _addScriptSelector(value: ScriptSelector)
}

fun ScriptSelector._init(
	manager: String?,
	language: String?,
	src: String?,
	classpath: String?,
	classpathref: String?,
	setbeans: Boolean?,
	selected: Boolean?,
	error: String?,
	nested: (KScriptselector.() -> Unit)?)
{
	if (manager != null)
		setManager(manager)
	if (language != null)
		setLanguage(language)
	if (src != null)
		setSrc(project.resolveFile(src))
	if (classpath != null)
		setClasspath(Path(project, classpath))
	if (classpathref != null)
		setClasspathRef(Reference(project, classpathref))
	if (setbeans != null)
		setSetBeans(setbeans)
	if (selected != null)
		setSelected(selected)
	if (error != null)
		setError(error)
	if (nested != null)
		nested(KScriptselector(this))
}

class KScriptselector(val component: ScriptSelector) {
	fun classpath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createClasspath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	operator fun String.unaryPlus() = component.addText(this)
}
