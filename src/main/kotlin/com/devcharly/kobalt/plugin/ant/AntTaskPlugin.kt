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
import com.beust.kobalt.misc.KobaltLogger
import com.beust.kobalt.misc.error
import com.devcharly.kotlin.ant.AntBuilder
import com.devcharly.kotlin.ant.LogLevel
import org.apache.tools.ant.BuildException
import org.apache.tools.ant.Task
import java.io.File
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
class AntTaskPlugin : BasePlugin(), ITaskContributor {
	override val name = PLUGIN_NAME

	companion object {
		const val PLUGIN_NAME = "AntTask"
		const val ANT_TASKS = "antTasks"
	}

	override fun accept(project: Project): Boolean {
		// enable plugin only for project with ant tasks
		return project.projectProperties.get(ANT_TASKS) != null
	}

	override fun tasksFor(project: Project, context: KobaltContext): List<DynamicTask> {
		@Suppress("UNCHECKED_CAST")
		val antTasks = project.projectProperties.get(AntTaskPlugin.ANT_TASKS) as ArrayList<AntTask>?
			?: return emptyList()

		// add new tasks for project
		val dynamicTasks = ArrayList<DynamicTask>()
		antTasks.forEach {
			dynamicTasks.add(DynamicTask(this, it.taskName, it.description, it.group, project,
					it.dependsOn.toList(), it.reverseDependsOn.toList(),
					it.runBefore.toList(), it.runAfter.toList(), it.alwaysRunAfter.toList(),
					closure = { project ->
						it.executeTasks()
					}))
		}
		return dynamicTasks
	}
}

class AntTask(val taskName: String,
		val description: String = "", val group: String = AnnotationDefault.GROUP,
		val dependsOn: Array<String> = arrayOf(), val reverseDependsOn: Array<String> = arrayOf(),
		val runBefore: Array<String> = arrayOf(), val runAfter: Array<String> = arrayOf(),
		val alwaysRunAfter: Array<String> = arrayOf(),
		val basedir: String = "", val logLevel: LogLevel? = null,
		tasks: AntBuilder.() -> Unit)
	: AntBuilder(tasks)
{
	fun executeTasks() : TaskResult {
		// create basedir
		if (basedir != "")
			File(basedir).mkdirs()

		// use Kobalt log level
		val logLevel2 = logLevel
			?: when (KobaltLogger.LOG_LEVEL) {
				0 -> LogLevel.WARN
				1 -> LogLevel.INFO
				2 -> LogLevel.VERBOSE
				3 -> LogLevel.DEBUG
				else -> LogLevel.INFO
			}

		// execute Ant tasks
		try {
			execute(basedir, logLevel2)
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
		basedir: String = "", logLevel: LogLevel? = null,
		tasks: AntBuilder.() -> Unit)
= AntTask(taskName, description, group,
		dependsOn, reverseDependsOn, runBefore, runAfter, alwaysRunAfter,
		basedir, logLevel, tasks).apply {
	@Suppress("UNCHECKED_CAST")
	val antTasks = projectProperties.get(AntTaskPlugin.ANT_TASKS) as ArrayList<AntTask>?
		?: ArrayList<AntTask>().apply { projectProperties.put(AntTaskPlugin.ANT_TASKS, this) }
	antTasks.forEach {
		if( it.taskName == taskName )
			throw AssertionError("Duplicate antTask '$taskName' in project '$projectName'")
	}
	antTasks.add(this)
}
