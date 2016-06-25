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

import java.util.*

const val HEADER = """/*
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

	// build parameters and initialization
	val params = genParams(task, "\t", imports)
	val init = genInit(task, "task", "\t\t", imports)

	// build function
	val funCode = "fun Ant.${task.taskName}(\n" +
			params + ")\n" +
			"{\n" +
			"\t${task.type.simpleName}().execute(\"${task.taskName}\") { task ->\n" +
			init +
			"\t}\n" +
			"}\n"
	return funCode
}

private fun genParams(task: Task, indent: String, imports: HashSet<String>): String {
	var params = ""

	task.params.forEach {
		if (!params.isEmpty())
			params += ",\n"
		params += "${indent}${it.name}: ${paramType(it.type)}? = null"
	}

	if (!task.nested.isEmpty() || task.nestedText)
		params += ",\n${indent}nested: (K${task.type.simpleName}.() -> Unit)? = null"

	return params
}

private fun genInit(task: Task, varName: String, indent: String, imports: HashSet<String>): String {
	var init = ""

	// build parameters and initialization
	task.params.forEach {
		val varNameDot = if (varName == "") "" else "$varName."
		init += "${indent}if (${it.name} != null)\n"
		init += "${indent}\t$varNameDot${it.method}(${init(it.type, it.name, it.constructWithProject, imports)})\n"
	}

	if (!task.nested.isEmpty() || task.nestedText) {
		val varName2 = if (varName == "") "this" else varName
		init += "${indent}if (nested != null)\n"
		init += "${indent}\tnested(K${task.type.simpleName}($varName2))\n"
	}

	return init
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
		else -> "String"
	}
}

private fun init(type: String, name: String, constructWithProject: Boolean, imports: HashSet<String>): String {
	return when (type) {
		"java.lang.String" -> name
		"boolean" -> name
		"byte" -> name
		"short" -> name
		"int" -> name
		"long" -> name
		"char" -> name
		"float" -> name
		"double" -> name
		"java.io.File" -> "project.resolveFile($name)"
		else -> {
			imports.add(type)
			val simpleType = type.substringAfterLast('.')
			if (constructWithProject) "$simpleType(project, $name)" else "$simpleType($name)"
		}
	}
}
