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

import org.apache.tools.ant.taskdefs.Echo
import org.apache.tools.ant.types.Resource

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.echo(
	message: String? = null,
	file: String? = null,
	output: String? = null,
	append: Boolean? = null,
	level: EchoLevel? = null,
	encoding: String? = null,
	force: Boolean? = null,
	nested: (KEcho.() -> Unit)? = null)
{
	Echo().execute("echo") { task ->
		if (message != null)
			task.setMessage(message)
		if (file != null)
			task.setFile(project.resolveFile(file))
		if (output != null)
			task.setOutput(Resource(output))
		if (append != null)
			task.setAppend(append)
		if (level != null)
			task.setLevel(Echo.EchoLevel().apply { this.value = level.value })
		if (encoding != null)
			task.setEncoding(encoding)
		if (force != null)
			task.setForce(force)
		if (nested != null)
			nested(KEcho(task))
	}
}

class KEcho(val component: Echo) {
	operator fun String.unaryPlus() = component.addText(this)
}

enum class EchoLevel(val value: String) { ERROR("error"), WARN("warn"), WARNING("warning"), INFO("info"), VERBOSE("verbose"), DEBUG("debug") }
