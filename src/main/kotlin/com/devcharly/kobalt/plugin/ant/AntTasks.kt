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
import org.apache.tools.ant.types.FileSet
import java.io.File

fun AntTask.copy(file: String? = null, tofile: String? = null, todir: String? = null,
	overwrite: Boolean = false,
	nested: (AntCopyNested.() -> Unit)? = null)
{
	val task = Copy()
	task.setFile(fileOrNull(file))
	task.setTofile(fileOrNull(tofile))
	task.setTodir(fileOrNull(todir))
	task.setOverwrite(overwrite)
	if (nested != null)
		nested(AntCopyNested(task))
	executeTask("copy", task)
}

class AntCopyNested(val task: Copy) {
	fun fileset(dir: String, nested: (AntFileSet.() -> Unit)? = null) {
		val fileset = FileSet()
		fileset.dir = fileOrNull(dir)
		if (nested != null)
			nested(AntFileSet(fileset))
		task.addFileset(fileset)
	}
}

class AntFileSet(val fileset: FileSet) {
	fun include(name: String) {
		fileset.createInclude().name = name
	}

	fun exclude(name: String) {
		fileset.createExclude().name = name
	}
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
