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

import org.apache.tools.ant.ProjectComponent
import org.apache.tools.ant.Task
import java.util.*

class TaskParam(val name: String, val method: String, val type: Class<*>)
class TaskNested()
class TaskInfo(val type: Class<*>, val params: Array<TaskParam>, val nested: Array<TaskNested>)

fun reflectTaskInfo(taskType: Class<*>) : TaskInfo {
	val params = ArrayList<TaskParam>()
	val nested = ArrayList<TaskNested>()

	for (m in taskType.methods) {
		if (m.declaringClass == Task::class.java || m.declaringClass == ProjectComponent::class.java)
			continue

		if (m.name.startsWith("set") && m.parameterCount == 1) {
			val name = m.name.substring(3).toLowerCase()
			var type = m.parameterTypes[0]
			params.add(TaskParam(name, m.name, type))
		}
	}

	return TaskInfo(taskType, params.toTypedArray(), nested.toTypedArray())
}
