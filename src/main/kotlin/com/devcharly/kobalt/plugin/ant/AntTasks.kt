/*
 * Copyright 2016 the original author or authors.
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

package com.devcharly.kobalt.plugin.ant

import org.apache.tools.ant.taskdefs.*
import java.io.File

fun AntTask.copy(file: String, tofile: String? = null, todir: String? = null, overwrite: Boolean = false) {
	val task = Copy()
	task.setFile(fileOrNull(file))
	task.setTofile(fileOrNull(tofile))
	task.setTodir(fileOrNull(todir))
	task.setOverwrite(overwrite)
	executeTask("copy", task)
}

fun AntTask.delete(file: String? = null, dir: String? = null) {
	val task = Delete()
	task.setFile(fileOrNull(file))
	task.setDir(fileOrNull(dir))
	executeTask("delete", task)
}

fun AntTask.echo(message: String) {
	val task = Echo()
	task.setMessage(message)
	executeTask("echo", task)
}

fun AntTask.property(name: String, value: String) {
	val task = Property()
	task.name = name
	task.value = value
	executeTask("property", task)
}

fun AntTask.touch(file: String) {
	val task = Touch()
	task.setFile(File(file))
	executeTask("touch", task)
}


fun fileOrNull(pathname: String?): File? {
	return if (pathname != null) File(pathname) else null
}