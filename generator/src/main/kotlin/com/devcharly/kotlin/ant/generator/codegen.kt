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

import java.util.*

const val HEADER = """/*
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

package com.devcharly.kotlin.ant
"""

const val DO_NOT_EDIT = """
/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/
"""

fun genTaskFile(task: Task): String {
	var code = HEADER

	val imports = HashSet<String>()
	val funCode = genTaskFun(task, imports)
	val nestedCode = genTaskNested(task, imports)

	code += '\n'
	imports.sorted().forEach {
		code += "import $it\n"
	}
	code += DO_NOT_EDIT
	code += '\n'
	code += funCode

	if (nestedCode != null) {
		code += '\n'
		code += nestedCode
	}

	return code
}

fun genTaskFun(task: Task, imports: HashSet<String>): String {
	imports.add(task.type.name)

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
	if (!task.nested.isEmpty() || task.nestedText) {
		params += ",\n\tnested: (K${task.type.simpleName}.() -> Unit)? = null"

		init += "\t\tif (nested != null)\n"
		init += "\t\t\tnested(K${task.type.simpleName}(task))\n"
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

fun genTaskNested(task: Task, imports: HashSet<String>): String? {
	if (task.nested.isEmpty() && !task.nestedText)
		return null

	var code = "class K${task.type.simpleName}(val task: ${task.type.simpleName}) {\n"
	if (task.nestedText) {
		code += "\toperator fun String.unaryPlus() {\n"
		code += "\t\ttask.addText(this)\n"
		code += "\t}\n"
	}
	code += "}\n"

	return code
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
