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

import org.apache.tools.ant.IntrospectionHelper
import org.apache.tools.ant.Project
import org.apache.tools.ant.types.EnumeratedAttribute
import java.lang.reflect.Method
import java.util.*

fun reflectTask(taskType: Class<*>, order: String? = null, exclude: String? = null): Task {
	val aClass = aClass(taskType, org.apache.tools.ant.Task::class.java)

	var projectAtConstructor = false
	try {
		taskType.getConstructor()
	} catch (ex: NoSuchMethodException) {
		try {
			taskType.getConstructor(Project::class.java)
			projectAtConstructor = true
		} catch (ex: NoSuchMethodException) {
			// ignore
		}
	}

	val params = ArrayList<TaskParam>()

	val reserved = arrayOf("if", "when")
	for (method in aClass.methods) {
		if (method.name.startsWith("set") && method.parameterTypes.size == 1) {
			var paramName = method.name.substring(3).decapitalize()
			val paramType = method.parameterTypes[0]
			var constructWithProject = false

			// avoid reserved names in parameters
			if (paramName in reserved)
				paramName = paramName.capitalize()

			if (paramType.name.contains('.') &&
				paramType != java.lang.String::class.java &&
				paramType != java.io.File::class.java)
			{
				try {
					paramType.getConstructor(Project::class.java, java.lang.String::class.java)
					constructWithProject = true
				} catch (ex: NoSuchMethodException) {
					try {
						paramType.getConstructor(java.lang.String::class.java)
					} catch (ex: NoSuchMethodException) {
						if (!EnumeratedAttribute::class.java.isAssignableFrom(paramType))
							continue // not supported parameter type
					}
				}
			}

			params.add(TaskParam(paramName, method.name, paramType, constructWithProject))
		}
	}

	// always exclude refid
	params.removeIf { it.name == "refid" }

	if (exclude != null) {
		val excludeList = exclude.split(' ')
		params.removeIf { excludeList.contains(it.name) }
	}

	if (order != null) {
		val orderList = order.split(' ')
		params.sortBy {
			val orderIndex = orderList.indexOf(it.name)
			if (orderIndex >= 0)
				orderIndex
			else
				orderList.size + params.indexOfFirst { param -> it.name == param.name }
		}
	}

	// use Ant IntrospectionHelper
	val ih = IntrospectionHelper.getHelper(taskType)
	val addTypeMethods = ih.extensionPoints.sortedBy { it.parameterTypes[0].simpleName }.toTypedArray()
	val addTextMethod = if (ih.supportsCharacters()) ih.addTextMethod else null

	val supported = arrayOf("patternset", "include", "exclude") //TODO
	val nested = ArrayList<TaskNested>()
	ih.nestedElementMap.forEach {
		if (it.key in supported) {
			val em = ih.getElementMethod(it.key)
			nested.add(TaskNested(it.key, it.value, em))
		}
	}

	// same order as in source code
	nested.sortBy { aClass.methods.indexOf(it.method) }

	return Task(taskType, projectAtConstructor, params.toTypedArray(), nested.toTypedArray(), addTypeMethods, addTextMethod)
}

//---- class Task -------------------------------------------------------------

class Task(val type: Class<*>,
		   val projectAtConstructor: Boolean,
           val params: Array<TaskParam>,
		   val nested: Array<TaskNested>,
		   val addTypeMethods: Array<Method>,
           val addTextMethod: Method?)
{
	val taskName = type.simpleName!!.toLowerCase()
	val hasNested = !nested.isEmpty() || !addTypeMethods.isEmpty() || addTextMethod != null
}

//---- class TaskParam --------------------------------------------------------

class TaskParam(val name: String, val method: String, val type: Class<*>, val constructWithProject: Boolean)


//---- class TaskNested -------------------------------------------------------

class TaskNested(val name: String, val type: Class<*>, val method: Method)
