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

import java.net.URL
import org.apache.tools.ant.taskdefs.Property
import org.apache.tools.ant.types.Path
import org.apache.tools.ant.types.Reference

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.property(
	name: String? = null,
	value: String? = null,
	location: String? = null,
	resource: String? = null,
	file: String? = null,
	url: String? = null,
	environment: String? = null,
	classpath: String? = null,
	classpathref: String? = null,
	prefix: String? = null,
	prefixvalues: Boolean? = null,
	relative: Boolean? = null,
	basedir: String? = null,
	nested: (KProperty.() -> Unit)? = null)
{
	Property().execute("property") { task ->
		if (name != null)
			task.setName(name)
		if (value != null)
			task.setValue(value)
		if (location != null)
			task.setLocation(project.resolveFile(location))
		if (resource != null)
			task.setResource(resource)
		if (file != null)
			task.setFile(project.resolveFile(file))
		if (url != null)
			task.setUrl(URL(url))
		if (environment != null)
			task.setEnvironment(environment)
		if (classpath != null)
			task.setClasspath(Path(project, classpath))
		if (classpathref != null)
			task.setClasspathRef(Reference(project, classpathref))
		if (prefix != null)
			task.setPrefix(prefix)
		if (prefixvalues != null)
			task.setPrefixValues(prefixvalues)
		if (relative != null)
			task.setRelative(relative)
		if (basedir != null)
			task.setBasedir(project.resolveFile(basedir))
		if (nested != null)
			nested(KProperty(task))
	}
}

class KProperty(val component: Property) {
	fun classpath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createClasspath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	operator fun String.unaryPlus() = component.addText(this)
}
