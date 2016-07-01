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

import org.apache.tools.ant.Project
import org.apache.tools.ant.ProjectComponent
import org.apache.tools.ant.types.EnumeratedAttribute
import org.apache.tools.ant.types.Environment
import org.apache.tools.ant.types.Path
import org.apache.tools.ant.types.ResourceCollection
import java.lang.reflect.Method
import java.lang.reflect.Modifier
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
	val nestedClassCode = genNestedClass(task, imports)

	var code = genFileHeader(imports)
	code += funCode

	if (nestedClassCode != null) {
		code += '\n'
		code += nestedClassCode
	}

	code += genEnums(task)

	return code
}

fun genTypeFile(task: Task): String {
	val imports = HashSet<String>()
	val nestedFunCode = genTypeNestedFun(task, null, "\t", imports)
	val nestedInterface = genTypeNestedInterface(task, nestedFunCode, imports)
	val initFunCode = genTypeInitFun(task, imports)
	val nestedClassCode = genNestedClass(task, imports)

	var code = genFileHeader(imports)
	code += nestedInterface

	val baseInterface = task.baseInterface
	if (baseInterface != null && task.type != baseInterface && baseInterface.isAssignableFrom(task.type)) {
		var cls: Class<*>? = task.type.superclass
		while (cls != null && baseInterface.isAssignableFrom(cls)) {
			if (!Modifier.isAbstract(cls.modifiers)) {
				code += "\n"
				code += genTypeNestedFun(task, cls.simpleName, "", imports)
			}
			cls = cls.superclass
		}

		code += "\n"
		code += genTypeNestedFun(task, baseInterface.simpleName, "", imports)
	}

	if (initFunCode != null) {
		code += "\n"
		code += initFunCode
	}

	if (nestedClassCode != null) {
		code += '\n'
		code += nestedClassCode
	}

	code += genEnums(task)

	return code
}

fun genTypeInitFile(task: Task): String {
	val imports = HashSet<String>()
	val initFunCode = genTypeInitFun(task, imports)

	var code = genFileHeader(imports)
	code += initFunCode

	return code
}

fun genEnumFile(task: Task): String {
	val imports = HashSet<String>()

	var code = genFileHeader(imports)
	code += genEnum(task.type)

	return code
}

fun genFileHeader(imports: Set<String>): String {
	var code = HEADER

	code += '\n'
	imports.sorted().forEach {
		code += "import ${it.replace('$', '.')}\n"
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

	val projectParam = if (needsProject(task.type)) {
		imports.add(Project::class.java.name)
		"\tproject: Project,\n"
	} else
		""

	// build function
	val funCode = "fun ${task.type.simpleName}._init(\n" +
			projectParam +
			params + ")\n" +
			"{\n" +
			init +
			"}\n"
	return funCode
}

fun genTypeNestedInterface(task: Task, body: String?, imports: HashSet<String>): String {
	if (!task.hasConstructor && !task.type.isInterface)
		return ""

	imports.add(task.type.name)

	return "interface I${task.type.simpleName}Nested " +
		(if (hasProject(task.type)) ": INestedComponent " else "") +
		"{\n" +
		(if (body != null) "$body\n" else "") +
		"\tfun _add${task.type.simpleName}(value: ${task.type.simpleName})\n" +
		"}\n"
}

fun genTypeNestedFun(task: Task, forType: String?, indent: String, imports: HashSet<String>): String? {
	if (task.type.isInterface)
		return null

	val params = genParams(task, true, "${indent}\t", imports)

	val addType = forType ?: task.type.simpleName
	val constrParam = if (task.projectAtConstructor) "component.project" else ""
	var addCode = "${indent}\t_add${addType}(${task.type.simpleName}($constrParam).apply {\n"
	if (hasProject(task.type))
		addCode += "${indent}\t\tcomponent.project.setProjectReference(this);\n"
	addCode += "${indent}\t\t_init("
	if (needsProject(task.type))
		addCode += "component.project, "
	task.params.forEachIndexed { i, param ->
		addCode += param.name
		if (i < task.params.size - 1)
			addCode += if ((i + 1) % 4 == 0) ",\n${indent}\t\t\t" else ", "
	}
	if (task.hasNested) {
		if (!task.params.isEmpty())
			addCode += ", "
		addCode += "nested"
	}
	addCode += ")\n" +
		"${indent}\t})\n"

	val forTypeInterface = if (forType != null) "I${forType}Nested." else ""
	return "${indent}fun $forTypeInterface${task.funName}(\n" +
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

	if (task.hasNested) {
		if (!task.params.isEmpty())
			params += ",\n"
		params += "${indent}nested: (K${task.nestedClassName}.() -> Unit)?"
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
		init += "${indent}\t$varNameDot${it.method.name}(${init(it.type, it.name, it.constructWithProject, imports)})\n"
	}

	if (task.hasNested) {
		val varName2 = if (varName == "") "this" else varName
		init += "${indent}if (nested != null)\n"
		init += "${indent}\tnested(K${task.nestedClassName}($varName2))\n"
	}

	return init
}

fun genNestedClass(task: Task, imports: HashSet<String>): String? {
	if (!task.hasNested)
		return null

	val interfaces = ArrayList<Class<*>>()
	task.addTypeMethods.forEach {
		interfaces.add(it.parameterTypes[0])
	}

	val addTypeMethods = ArrayList<Method>()
	addTypeMethods.addAll(task.addTypeMethods)

	var code = ""
	task.nested.forEach {
		val n = getTask(it.type)
		if (it.name == n.funName) {
			val baseInterface = n.baseInterface
			if (baseInterface != null &&
				baseInterface.isAssignableFrom(it.type) &&
				interfaces.contains(baseInterface))
			  return@forEach

			if (it.method.parameterCount == 1 &&
				it.method.parameterTypes[0] == it.type)
			{
				interfaces.add(it.type)
				addTypeMethods.add(it.method)
				return@forEach
			}
		}

		var nestedInitCode = "apply {\n"
		if (hasProject(it.type))
			nestedInitCode += "\t\t\tcomponent.project.setProjectReference(this)\n"
		nestedInitCode += "\t\t\t_init("
		if (needsProject(n.type))
			nestedInitCode += "component.project, "
		nestedInitCode += "${n.params.joinToString { it.name }}"
		if (n.hasNested) {
			if (!n.params.isEmpty())
				nestedInitCode += ", "
			nestedInitCode += "nested"
		}
		nestedInitCode += ")\n" +
			"\t\t}"

		code += "\tfun ${it.name}(${genParams(n, true, "", imports).replace('\n', ' ')}) {\n"
		if (it.method.parameterCount == 0)
			code += "\t\tcomponent.${it.method.name}().$nestedInitCode\n"
		else {
			val constrParam = if (n.projectAtConstructor) "component.project" else ""
			code += "\t\tcomponent.${it.method.name}(${it.type.simpleName}($constrParam).$nestedInitCode)\n"
			imports.add(it.type.name)
		}
		code += "\t}\n"
	}

	addTypeMethods.forEach { addTypeMethod ->
		val type = addTypeMethod.parameterTypes[0]
		imports.add(type.name)
		code += "\toverride fun _add${type.simpleName}(value: ${type.simpleName}) = component.${addTypeMethod.name}(value)\n"
	}

	if (task.addTextMethod != null)
		code += "\toperator fun String.unaryPlus() = component.${task.addTextMethod.name}(this)\n"
	code += "}\n"

	val consOvr = if (interfaces.any { hasProject(it) }) "override " else ""
	var code0 = "class K${task.nestedClassName}(${consOvr}val component: ${task.type.simpleName})"
	interfaces.forEachIndexed { i, it ->
		code0 += if (i == 0) " :\n\t" else ",\n\t"
		code0 += "I${it.simpleName}Nested"
	}
	code0 += if (interfaces.isEmpty()) " " else "\n"
	code0 += "{\n"

	return code0 + code
}

private fun genEnums(task: Task): String {
	var code = ""
	var enums = HashSet<Class<*>>()

	for (param in task.params) {
		if (!EnumeratedAttribute::class.java.isAssignableFrom(param.type) && !param.type.isEnum)
			continue

		if (!enums.add(param.type))
			continue

		val enclosingClass = param.type.enclosingClass
		if (enclosingClass != task.type)
			continue

		if (code.isEmpty())
			code += "\n"

		code += genEnum(param.type)
	}

	return code
}

private fun genEnum(type: Class<*>): String {
	var code = ""
	if (EnumeratedAttribute::class.java.isAssignableFrom(type)) {
		code += "enum class ${type.simpleName}(val value: String) { "
		val e = type.newInstance() as EnumeratedAttribute
		val values = e.values

		// remove duplicate values
		val values2 = values.filterIndexed { i, s ->
			for (j in (i - 1) downTo 0) {
				if (s.equals(values[j], ignoreCase = true))
					return@filterIndexed false
			}
			true
		}

		code += values2.joinToString { "${it.toUpperCase().replace('-', '_')}(\"$it\")" }
		code += " }\n"
	} else if (type.isEnum) {
		val origEnum = if (type.name.contains('$'))
			type.name.substringAfterLast('.').replace('$', '.')
		else
			type.name // not a nested enum --> use full qualified name

		val values = type.getMethod("values").invoke(null) as Array<java.lang.Enum<*>>
		code += "enum class ${type.simpleName}(val value: $origEnum) {\n\t"
		code += values.joinToString(",\n\t") { "${it.name().toUpperCase()}($origEnum.${it.name()})" }
		code += "\n}\n"
	}
	return code
}

private fun paramType(type: Class<*>): String {
	return when (type.name) {
		"java.lang.String" -> "String"
		"boolean" -> "Boolean"
		"byte" -> "Byte"
		"short" -> "Short"
		"int" -> "Int"
		"long" -> "Long"
		"char" -> "Char"
		"float" -> "Float"
		"double" -> "Double"
		"java.lang.Boolean" -> "Boolean"
		"java.lang.Byte" -> "Byte"
		"java.lang.Short" -> "Short"
		"java.lang.Integer" -> "Int"
		"java.lang.Long" -> "Long"
		"java.lang.Character" -> "Char"
		"java.lang.Float" -> "Float"
		"java.lang.Double" -> "Double"
		"java.io.File" -> "String"
		else ->
			if (EnumeratedAttribute::class.java.isAssignableFrom(type) || type.isEnum)
				type.simpleName
			else
				"String"
	}
}

private fun init(type: Class<*>, name: String, constructWithProject: Boolean, imports: HashSet<String>): String {
	return when (type.name) {
		"java.lang.String" -> name
		"boolean" -> name
		"byte" -> name
		"short" -> name
		"int" -> name
		"long" -> name
		"char" -> name
		"float" -> name
		"double" -> name
		"java.lang.Boolean" -> name
		"java.lang.Byte" -> name
		"java.lang.Short" -> name
		"java.lang.Integer" -> name
		"java.lang.Long" -> name
		"java.lang.Character" -> name
		"java.lang.Float" -> name
		"java.lang.Double" -> name
		"java.io.File" -> "project.resolveFile($name)"
		else -> {
			if (EnumeratedAttribute::class.java.isAssignableFrom(type)) {
				if (type.enclosingClass != null)
					imports.add(type.enclosingClass.name)
				val simpleType = if (type.name.contains('$'))
					type.name.substringAfterLast('.').replace('$', '.')
				else
					type.name // not a nested enum --> use full qualified name
				"$simpleType().apply { this.value = $name.value }"
			} else if (type.isEnum) {
				"$name.value"
			} else {
				imports.add(type.name)
				val simpleType = type.name.substringAfterLast('.')
				if (constructWithProject) "$simpleType(project, $name)" else "$simpleType($name)"
			}
		}
	}
}

private fun hasProject(cls: Class<*>): Boolean {
	return ProjectComponent::class.java.isAssignableFrom(cls) ||
		cls == ResourceCollection::class.java
}

private fun needsProject(cls: Class<*>): Boolean {
	return cls == Path.PathElement::class.java ||
		cls == Environment.Variable::class.java
}
