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

package com.devcharly.kotlin.ant.generator

import org.apache.tools.ant.taskdefs.Echo
import org.apache.tools.ant.taskdefs.Mkdir
import org.apache.tools.ant.taskdefs.Touch
import java.io.FileWriter

fun main(args: Array<String>) {
	val taskTypes = arrayOf(
			Echo::class.java,
			Mkdir::class.java,
			Touch::class.java
	)
	taskTypes.forEach { type ->
		val task = reflectTask(type)
		val code = genTaskFile(task)

		val filename = "src/main/kotlin/com/devcharly/kotlin/ant/${task.taskName}.kt"
		println(filename)
		FileWriter(filename).use {
			it.write(code)
		}
	}
}
