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

package com.devcharly.kotlin.ant

import org.apache.tools.ant.DefaultLogger
import org.apache.tools.ant.PropertyHelper
import org.apache.tools.ant.Target
import org.apache.tools.ant.Task
import java.io.File

open class AntBuilder(private val tasks: AntBuilder.() -> Unit) {
	lateinit var project: org.apache.tools.ant.Project
	lateinit var target: Target

	fun execute(basedir: String = "") {
		// create Ant logger
		val logger = DefaultLogger()
		logger.setMessageOutputLevel(org.apache.tools.ant.Project.MSG_INFO)
		logger.setOutputPrintStream(System.out)
		logger.setErrorPrintStream(System.err)

		// create Ant project
		project = org.apache.tools.ant.Project()
		project.addBuildListener(logger)
		project.init()
		project.baseDir = File(basedir).absoluteFile

		// create Ant target
		target = Target()
		target.project = project
		target.name = "AntBuilder" // TODO

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
}

fun fileOrNull(pathname: String?): File? {
	return if (pathname != null) File(pathname) else null
}
