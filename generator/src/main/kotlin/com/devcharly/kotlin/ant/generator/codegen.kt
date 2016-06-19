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

import java.io.File

fun genTaskFun(taskInfo: TaskInfo): String {
	var params = ""
	var init = ""

	// build parameters and initialization
	taskInfo.params.forEach {
		val name = it.name
		val method = it.method
		var type = it.type

		if (!params.isEmpty())
			params += ",\n"
		params += "\t$name: ${paramType(type)}? = null"

		init += "\t\tif ($name != null)\n"
		init += "\t\t\ttask.${method}(${init(type, name)})\n"
	}

	// build function
	val taskName = taskInfo.type.simpleName!!.toLowerCase()
	val funCode = "fun AntBuilder.$taskName(\n" +
			params + ")\n" +
			"{\n" +
			"\t${taskInfo.type.simpleName}().execute(\"$taskName\") { task ->\n" +
			init +
			"\t}\n" +
			"}\n"
	return funCode
}

private fun paramType(type: Class<*>): String {
	return when (type) {
		Boolean::class.java -> "Boolean"
		Byte::class.java -> "Byte"
		Short::class.java -> "Short"
		Int::class.java -> "Int"
		Long::class.java -> "Long"
		Char::class.java -> "Char"
		Float::class.java -> "Float"
		Double::class.java -> "Double"
		File::class.java -> "String"
		else -> type.simpleName
	}
}

private fun init(type: Class<*>, name: String): String {
	return when (type) {
		File::class.java -> "resolveFile($name)"
		else -> name
	}
}
