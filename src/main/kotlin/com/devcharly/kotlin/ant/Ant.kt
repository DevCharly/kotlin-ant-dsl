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

package com.devcharly.kotlin.ant

import org.apache.tools.ant.DefaultLogger
import org.apache.tools.ant.Project
import org.apache.tools.ant.PropertyHelper
import org.apache.tools.ant.Target
import org.apache.tools.ant.Task
import java.io.File

open class Ant(private val tasks: Ant.() -> Unit) {
	lateinit var project: Project
	lateinit var target: Target

	fun execute(basedir: String = "", logLevel: LogLevel = LogLevel.INFO) {
		// create Ant logger
		val logger = DefaultLogger()
		logger.setMessageOutputLevel(logLevel.level)
		logger.setOutputPrintStream(System.out)
		logger.setErrorPrintStream(System.err)

		// create Ant project
		project = Project()
		project.addBuildListener(logger)
		project.init()
		project.baseDir = File(basedir).absoluteFile

		// create Ant target
		target = Target()
		target.project = project
		target.name = "Ant" // TODO

		// execute Ant tasks
		tasks(this)
	}

	fun <T : Task> T.execute(taskName: String, block: (T) -> Unit) {
		initTask(this, taskName)
		block(this)
		executeTask(this)
	}

	fun initTask(task: Task, taskName: String) {
		task.project = project
		task.owningTarget = target
		task.taskName = taskName
		task.taskType = taskName
	}

	open fun executeTask(task: Task) {
		if (task.project == null)
			throw IllegalStateException("initTask() not called")

		task.execute()
	}

	fun p(name: String): String {
		return PropertyHelper.getPropertyHelper(project).parseProperties("\${$name}").toString()
	}

	fun resolveFile(pathname: String?): File? {
		return if (pathname != null) project.resolveFile(pathname) else null
	}
}

fun Task.resolveFile(pathname: String?): File? {
	return if (pathname != null) project.resolveFile(pathname) else null
}

//---- enum LogLevel ----------------------------------------------------------

enum class LogLevel(val level: Int, val str: String) {
	ERR(Project.MSG_ERR, "error"),
	WARN(Project.MSG_WARN, "warn"),
	INFO(Project.MSG_INFO, "info"),
	VERBOSE(Project.MSG_VERBOSE, "verbose"),
	DEBUG(Project.MSG_DEBUG, "debug")
}
