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
	refid: String? = null,
	resource: String? = null,
	file: String? = null,
	url: String? = null,
	environment: String? = null,
	classpath: String? = null,
	prefix: String? = null,
	prefixValues: Boolean? = null,
	relative: Boolean? = null,
	basedir: String? = null,
	classpathRef: String? = null,
	nested: (KProperty.() -> Unit)? = null)
{
	Property().execute("property") { task ->
		if (name != null)
			task.setName(name)
		if (value != null)
			task.setValue(value)
		if (location != null)
			task.setLocation(resolveFile(location))
		if (refid != null)
			task.setRefid(Reference(project, refid))
		if (resource != null)
			task.setResource(resource)
		if (file != null)
			task.setFile(resolveFile(file))
		if (url != null)
			task.setUrl(URL(url))
		if (environment != null)
			task.setEnvironment(environment)
		if (classpath != null)
			task.setClasspath(Path(project, classpath))
		if (prefix != null)
			task.setPrefix(prefix)
		if (prefixValues != null)
			task.setPrefixValues(prefixValues)
		if (relative != null)
			task.setRelative(relative)
		if (basedir != null)
			task.setBasedir(resolveFile(basedir))
		if (classpathRef != null)
			task.setClasspathRef(Reference(project, classpathRef))
		if (nested != null)
			nested(KProperty(task))
	}
}

class KProperty(val task: Property) {
	operator fun String.unaryPlus() {
		task.addText(this)
	}
}
