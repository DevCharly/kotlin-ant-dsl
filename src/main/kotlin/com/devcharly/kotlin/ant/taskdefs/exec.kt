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

import org.apache.tools.ant.taskdefs.ExecTask
import org.apache.tools.ant.types.Commandline
import org.apache.tools.ant.types.Environment.Variable

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.exec(
	command: String? = null,
	executable: String? = null,
	dir: String? = null,
	os: String? = null,
	osfamily: String? = null,
	spawn: Boolean? = null,
	timeout: Long? = null,
	output: String? = null,
	input: String? = null,
	inputstring: String? = null,
	logerror: Boolean? = null,
	error: String? = null,
	outputproperty: String? = null,
	errorproperty: String? = null,
	failonerror: Boolean? = null,
	newenvironment: Boolean? = null,
	resolveexecutable: Boolean? = null,
	searchpath: Boolean? = null,
	resultproperty: String? = null,
	failifexecutionfails: Boolean? = null,
	append: Boolean? = null,
	vmlauncher: Boolean? = null,
	nested: (KExec.() -> Unit)? = null)
{
	ExecTask().execute("exec") { task ->
		if (command != null)
			task.setCommand(Commandline(command))
		if (executable != null)
			task.setExecutable(executable)
		if (dir != null)
			task.setDir(project.resolveFile(dir))
		if (os != null)
			task.setOs(os)
		if (osfamily != null)
			task.setOsFamily(osfamily)
		if (spawn != null)
			task.setSpawn(spawn)
		if (timeout != null)
			task.setTimeout(timeout)
		if (output != null)
			task.setOutput(project.resolveFile(output))
		if (input != null)
			task.setInput(project.resolveFile(input))
		if (inputstring != null)
			task.setInputString(inputstring)
		if (logerror != null)
			task.setLogError(logerror)
		if (error != null)
			task.setError(project.resolveFile(error))
		if (outputproperty != null)
			task.setOutputproperty(outputproperty)
		if (errorproperty != null)
			task.setErrorProperty(errorproperty)
		if (failonerror != null)
			task.setFailonerror(failonerror)
		if (newenvironment != null)
			task.setNewenvironment(newenvironment)
		if (resolveexecutable != null)
			task.setResolveExecutable(resolveexecutable)
		if (searchpath != null)
			task.setSearchPath(searchpath)
		if (resultproperty != null)
			task.setResultProperty(resultproperty)
		if (failifexecutionfails != null)
			task.setFailIfExecutionFails(failifexecutionfails)
		if (append != null)
			task.setAppend(append)
		if (vmlauncher != null)
			task.setVMLauncher(vmlauncher)
		if (nested != null)
			nested(KExec(task))
	}
}

class KExec(val component: ExecTask) {
	fun env(key: String? = null, value: String? = null, path: String? = null, file: String? = null) {
		component.addEnv(Variable().apply {
			_init(component.project, key, value, path, file)
		})
	}
	fun arg(value: String? = null, line: String? = null, path: String? = null, pathref: String? = null, file: String? = null, prefix: String? = null, suffix: String? = null) {
		component.createArg().apply {
			component.project.setProjectReference(this)
			_init(value, line, path, pathref, file, prefix, suffix)
		}
	}
}
