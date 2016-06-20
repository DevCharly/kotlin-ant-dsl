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

fun genTaskFun(task: Task): String {
	var params = ""
	var init = ""

	// build parameters and initialization
	task.params.forEach {
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
	val funCode = "fun AntBuilder.${task.taskName}(\n" +
			params + ")\n" +
			"{\n" +
			"\t${task.type.simpleName}().execute(\"${task.taskName}\") { task ->\n" +
			init +
			"\t}\n" +
			"}\n"
	return funCode
}

private fun paramType(type: String): String {
	return when (type) {
		"java.lang.String" -> "String"
		"boolean" -> "Boolean"
		"byte" -> "Byte"
		"short" -> "Short"
		"int" -> "Int"
		"long" -> "Long"
		"char" -> "Char"
		"float" -> "Float"
		"double" -> "Double"
		"java.io.File" -> "String"
		else -> type
	}
}

private fun init(type: String, name: String): String {
	return when (type) {
		"java.io.File" -> "resolveFile($name)"
		else -> name
	}
}
