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
	val imports = HashSet<String>()
	val funCode = genTaskFun(task, imports)
	val nestedCode = genTaskNested(task, imports)

	var code = genFileHeader(imports)
	code += funCode

	if (nestedCode != null) {
		code += '\n'
		code += nestedCode
	}

	return code
}

fun genTypeFile(task: Task): String {
	val imports = HashSet<String>()
	val nestedFunCode = genTypeNestedFun(task, null, "\t", imports)
	val nestedInterface = genTypeNestedInterface(task, nestedFunCode, imports)
	val initFunCode = genTypeInitFun(task, imports)

	var code = genFileHeader(imports)
	code += nestedInterface
	if (initFunCode != null) {
		code += "\n"
		code += initFunCode
	}

	return code
}

fun genFileHeader(imports: Set<String>): String {
	var code = HEADER

	code += '\n'
	imports.sorted().forEach {
		code += "import $it\n"
	}
	code += DO_NOT_EDIT
	code += '\n'

	return code
}

fun genTaskFun(task: Task, imports: HashSet<String>): String {
	imports.add(task.type.name)

	// build parameters and initialization
	val params = genParams(task, true, "\t", imports)
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

fun genTypeInitFun(task: Task, imports: HashSet<String>): String? {
	if (task.type.isInterface)
		return null

	imports.add(task.type.name)

	// build parameters and initialization
	val params = genParams(task, false, "\t", imports)
	val init = genInit(task, "", "\t", imports)

	// build function
	val funCode = "fun ${task.type.simpleName}._init(\n" +
			params + ")\n" +
			"{\n" +
			init +
			"}\n"
	return funCode
}

fun genTypeNestedInterface(task: Task, body: String?, imports: HashSet<String>): String {
	imports.add(task.type.name)

	return "interface I${task.type.simpleName}Nested : INestedComponent {\n" +
		(if (body != null) "$body\n" else "") +
		"\tfun _add${task.type.simpleName}(value: ${task.type.simpleName})\n" +
		"}\n"
}

fun genTypeNestedFun(task: Task, forType: String?, indent: String, imports: HashSet<String>): String? {
	if (task.type.isInterface)
		return null

	val params = genParams(task, true, "${indent}\t", imports)

	var addCode = "${indent}\t_add${task.type.simpleName}(${task.type.simpleName}().apply {\n" +
		"${indent}\t\tcomponent.project.setProjectReference(this);\n" +
		"${indent}\t\t_init("
	task.params.forEachIndexed { i, param ->
		addCode += param.name
		if (i < task.params.size - 1)
			addCode += if ((i + 1) % 4 == 0) ",\n${indent}\t\t\t" else ", "
	}
	addCode += ")\n" +
		"${indent}\t})\n"

	val forTypeInterface = if (forType != null) "I${task.type.simpleName}Nested." else ""
	return "${indent}fun $forTypeInterface${task.taskName}(\n" +
		params + ")\n" +
		"${indent}{\n" +
		addCode +
		"${indent}}\n"
}

private fun genParams(task: Task, initNull: Boolean, indent: String, imports: HashSet<String>): String {
	var params = ""

	task.params.forEach {
		if (!params.isEmpty())
			params += ",\n"
		params += "${indent}${it.name}: ${paramType(it.type)}?"
		if (initNull)
			params += " = null"
	}

	if (!task.nested.isEmpty() || task.nestedText) {
		params += ",\n${indent}nested: (K${task.type.simpleName}.() -> Unit)?"
		if (initNull)
			params += " = null"
	}

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
