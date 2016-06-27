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

package com.devcharly.kotlin.ant.generator

import org.apache.tools.ant.taskdefs.*
import org.apache.tools.ant.types.*
import org.apache.tools.ant.types.selectors.*
import org.apache.tools.ant.util.FileNameMapper
import java.io.FileWriter

fun main(args: Array<String>) {
	// Types
	genType(DirSet::class.java, baseInterface = ResourceCollection::class.java, exclude = "refid")
	genType(FileSet::class.java, baseInterface = ResourceCollection::class.java, exclude = "refid")
	genType(ResourceCollection::class.java)
	genType(TarFileSet::class.java, baseInterface = ResourceCollection::class.java, exclude = "refid")
	genType(ZipFileSet::class.java, baseInterface = ResourceCollection::class.java, exclude = "refid")

	// Selectors
	genType(FileSelector::class.java, folder = "selectors")

	// Util
	genType(FileNameMapper::class.java, folder = "util")

	// Tasks
	genTask(BUnzip2::class.java, "src dest", "srcResource")
	genTask(BZip2::class.java, "src destfile", "zipfile srcResource")
	genTask(Echo::class.java)
	genTask(GUnzip::class.java, "src dest", "srcResource")
	genTask(GZip::class.java, "src destfile", "zipfile srcResource")
	genTask(Mkdir::class.java)
	genTask(Property::class.java, "name value location refid resource file url environment classpath classpathref prefix prefixValues relative basedir")
	genTask(Touch::class.java)
}

fun genTask(taskType: Class<*>, order: String? = null, exclude: String? = null) {
	val task = reflectTask(taskType, order, exclude)
	val code = genTaskFile(task)

	writeCode("src/main/kotlin/com/devcharly/kotlin/ant/taskdefs/${task.taskName}.kt", code)
}

fun genType(typeType: Class<*>, baseInterface: Class<*>? = null, order: String? = null, exclude: String? = null, folder: String = "types") {
	val task = reflectTask(typeType, order, exclude)
	val code = genTypeFile(task, baseInterface)

	writeCode("src/main/kotlin/com/devcharly/kotlin/ant/$folder/${task.taskName}.kt", code)
}

fun writeCode(filename: String, code: String) {
	println("Generate $filename")
	FileWriter(filename).use {
		it.write(code.replace("\n", System.getProperty("line.separator")))
	}
}
