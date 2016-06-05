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
import org.apache.tools.ant.DefaultLogger
import org.apache.tools.ant.Target
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
		const val PLUGIN_NAME = "kobalt-ant-task"
		const val ANT_TASKS = "antTasks"
	}

	override fun accept(project: Project): Boolean {
		// enable plugin only for project with ant tasks
		return project.projectProperties.get(ANT_TASKS) != null
	}

	override fun apply(project: Project, context: KobaltContext) {
		super.apply(project, context)

		// add new tasks for project
		val antTasks = project.projectProperties.get(AntTaskPlugin.ANT_TASKS) as ArrayList<AntTask>
		antTasks.forEach {
			taskManager.addTask(this, project, it.taskName, it.description, it.group,
					it.dependsOn.toList(), it.reverseDependsOn.toList(),
					it.runBefore.toList(), it.runAfter.toList(), it.alwaysRunAfter.toList(),
					task = { project ->
						it.execute()
						TaskResult()
					})
		}
	}
}

class AntTask(val taskName: String,
		val description: String = "", val group: String = AnnotationDefault.GROUP,
		val dependsOn: Array<String> = arrayOf(), val reverseDependsOn: Array<String> = arrayOf(),
		val runBefore: Array<String> = arrayOf(), val runAfter: Array<String> = arrayOf(),
		val alwaysRunAfter: Array<String> = arrayOf(),
		val tasks: AntTask.() -> Unit)
{
	lateinit var project: org.apache.tools.ant.Project
	lateinit var target: Target

	fun execute() {
		// create Ant logger
		val logger = DefaultLogger()
		logger.setMessageOutputLevel(org.apache.tools.ant.Project.MSG_INFO)
		logger.setOutputPrintStream(System.out)
		logger.setErrorPrintStream(System.err)

		// create Ant project
		project = org.apache.tools.ant.Project()
		project.addBuildListener(logger)
		project.init()

		// create Ant target
		target = Target()
		target.project = project
		target.name = taskName

		// execute Ant tasks
		tasks(this)
	}

	fun executeTask(taskName: String, task: Task) {
		task.project = project
		task.owningTarget = target
		task.taskName = taskName
		task.taskType = taskName

		task.execute()
	}
}

@Directive
fun Project.antTask(taskName: String,
		description: String = "", group: String = AnnotationDefault.GROUP,
		dependsOn: Array<String> = arrayOf(), reverseDependsOn: Array<String> = arrayOf(),
		runBefore: Array<String> = arrayOf(), runAfter: Array<String> = arrayOf(),
		alwaysRunAfter: Array<String> = arrayOf(),
		tasks: AntTask.() -> Unit)
= AntTask(taskName, description, group, dependsOn, reverseDependsOn, runBefore, runAfter, alwaysRunAfter, tasks).apply {
	val antTasks = projectProperties.get(AntTaskPlugin.ANT_TASKS) as ArrayList<AntTask>?
		?: ArrayList<AntTask>().apply { projectProperties.put(AntTaskPlugin.ANT_TASKS, this) }
	antTasks.forEach {
		if( it.taskName == taskName )
			throw AssertionError("Duplicate antTask '$taskName' in project '$projectName'")
	}
	antTasks.add(this)
}
