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
import java.io.FileWriter

fun main(args: Array<String>) {
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

	val filename = "src/main/kotlin/com/devcharly/kotlin/ant/taskdefs/${task.taskName}.kt"
	println("Generate $filename")
	FileWriter(filename).use {
		it.write(code.replace("\n", System.getProperty("line.separator")))
	}
}
