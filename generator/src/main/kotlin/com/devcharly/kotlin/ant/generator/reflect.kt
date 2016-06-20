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
import java.util.*

fun reflectTask(taskType: Class<*>, order: String? = null): Task {
	val aClass = aClass(taskType)

	val params = ArrayList<TaskParam>()
	val nested = ArrayList<TaskNested>()

	for (aMethod in aClass.methods) {
		if (aMethod.name.startsWith("set") && aMethod.parameterTypes.size == 1) {
			val paramName = aMethod.name.substring(3).decapitalize()
			val paramType = aMethod.parameterTypes[0]
			var constructWithProject = false

			if (paramType.contains('.') &&
				paramType != "java.lang.String" &&
				paramType != "java.io.File")
			{
				val paramClass = taskType.classLoader.loadClass(paramType)
				try {
					paramClass.getConstructor(Project::class.java, java.lang.String::class.java)
					constructWithProject = true
				} catch (ex: NoSuchMethodException) {
					try {
						paramClass.getConstructor(java.lang.String::class.java)
					} catch (ex: NoSuchMethodException) {
						continue // not supported parameter type
					}
				}
			}

			params.add(TaskParam(paramName, aMethod.name, paramType, constructWithProject))
		}
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

	val addTextMethod = aClass.getMethod("addText", "java.lang.String")

	return Task(taskType, params.toTypedArray(), nested.toTypedArray(), addTextMethod != null)
}

//---- class Task -------------------------------------------------------------

class Task(val type: Class<*>,
           val params: Array<TaskParam>,
           val nested: Array<TaskNested>,
           val nestedText: Boolean)
{
	val taskName = type.simpleName!!.toLowerCase()
}

//---- class TaskParam --------------------------------------------------------

class TaskParam(val name: String, val method: String, val type: String, val constructWithProject: Boolean)

//---- class TaskNested -------------------------------------------------------

class TaskNested()
