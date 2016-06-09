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

package com.devcharly.kobalt.plugin.ant

import com.beust.kobalt.TaskResult
import com.beust.kobalt.api.*
import com.beust.kobalt.api.annotation.AnnotationDefault
import com.beust.kobalt.api.annotation.Directive
import com.beust.kobalt.misc.error
import com.devcharly.kotlin.ant.AntBuilder
import org.apache.tools.ant.BuildException
import org.apache.tools.ant.Task
import java.util.*

/**
 * antTask plugin for Kobalt
 *
 * Supports definition of per-project Kobalt tasks (similar to Ant targets)
 * and execution of Ant tasks.
 *
 * val project = project {
 *     antTask("hello") {
 *         echo("Hello World")
 *     }
 * }
 */
class AntTaskPlugin : BasePlugin() {
	override val name = PLUGIN_NAME

	companion object {
		const val PLUGIN_NAME = "AntTask"
		const val ANT_TASKS = "antTasks"
	}

	override fun accept(project: Project): Boolean {
		// enable plugin only for project with ant tasks
		return project.projectProperties.get(ANT_TASKS) != null
	}

	override fun apply(project: Project, context: KobaltContext) {
		super.apply(project, context)

		// add new tasks for project
		@Suppress("UNCHECKED_CAST")
		val antTasks = project.projectProperties.get(AntTaskPlugin.ANT_TASKS) as ArrayList<AntTask>
		antTasks.forEach {
			taskManager.addTask(this, project, it.taskName, it.description, it.group,
					it.dependsOn.toList(), it.reverseDependsOn.toList(),
					it.runBefore.toList(), it.runAfter.toList(), it.alwaysRunAfter.toList(),
					task = { project ->
						it.executeTasks()
					})
		}
	}
}

class AntTask(val taskName: String,
		val description: String = "", val group: String = AnnotationDefault.GROUP,
		val dependsOn: Array<String> = arrayOf(), val reverseDependsOn: Array<String> = arrayOf(),
		val runBefore: Array<String> = arrayOf(), val runAfter: Array<String> = arrayOf(),
		val alwaysRunAfter: Array<String> = arrayOf(),
		tasks: AntBuilder.() -> Unit)
	: AntBuilder(tasks)
{
	fun executeTasks() : TaskResult {
		// execute Ant tasks
		try {
			execute()
			return TaskResult()
		} catch (e: BuildException) {
			return TaskResult(false, e.message)
		}
	}

	override fun executeTask(task: Task) {
		try {
			super.executeTask(task)
		} catch (e: BuildException) {
			// always print stack trace
			// if running from an IDE, clicking on
			//     BuildKt$project.invoke(Build.kt:123)
			// in stack trace jump directly to the line in Build.kt
			e.printStackTrace()

			error("Ant task [${task.taskName}] failed: ${e.message}")
			throw e
		}
	}
}

@Directive
fun Project.antTask(taskName: String,
		description: String = "", group: String = AnnotationDefault.GROUP,
		dependsOn: Array<String> = arrayOf(), reverseDependsOn: Array<String> = arrayOf(),
		runBefore: Array<String> = arrayOf(), runAfter: Array<String> = arrayOf(),
		alwaysRunAfter: Array<String> = arrayOf(),
		tasks: AntBuilder.() -> Unit)
= AntTask(taskName, description, group, dependsOn, reverseDependsOn, runBefore, runAfter, alwaysRunAfter, tasks).apply {
	@Suppress("UNCHECKED_CAST")
	val antTasks = projectProperties.get(AntTaskPlugin.ANT_TASKS) as ArrayList<AntTask>?
		?: ArrayList<AntTask>().apply { projectProperties.put(AntTaskPlugin.ANT_TASKS, this) }
	antTasks.forEach {
		if( it.taskName == taskName )
			throw AssertionError("Duplicate antTask '$taskName' in project '$projectName'")
	}
	antTasks.add(this)
}
