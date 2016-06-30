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

import org.apache.tools.ant.taskdefs.Java
import org.apache.tools.ant.types.Assertions
import org.apache.tools.ant.types.Environment.Variable
import org.apache.tools.ant.types.Path
import org.apache.tools.ant.types.Reference

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.java(
	classname: String? = null,
	jar: String? = null,
	args: String? = null,
	classpath: String? = null,
	classpathref: String? = null,
	fork: Boolean? = null,
	spawn: Boolean? = null,
	jvm: String? = null,
	jvmargs: String? = null,
	maxmemory: String? = null,
	module: String? = null,
	modulepath: String? = null,
	modulepathref: String? = null,
	clonevm: Boolean? = null,
	resultproperty: String? = null,
	failonerror: Boolean? = null,
	dir: String? = null,
	output: String? = null,
	input: String? = null,
	inputstring: String? = null,
	logerror: Boolean? = null,
	error: String? = null,
	outputproperty: String? = null,
	errorproperty: String? = null,
	jvmversion: String? = null,
	newenvironment: Boolean? = null,
	append: Boolean? = null,
	timeout: Long? = null,
	nested: (KJava.() -> Unit)? = null)
{
	Java().execute("java") { task ->
		if (classname != null)
			task.setClassname(classname)
		if (jar != null)
			task.setJar(project.resolveFile(jar))
		if (args != null)
			task.setArgs(args)
		if (classpath != null)
			task.setClasspath(Path(project, classpath))
		if (classpathref != null)
			task.setClasspathRef(Reference(project, classpathref))
		if (fork != null)
			task.setFork(fork)
		if (spawn != null)
			task.setSpawn(spawn)
		if (jvm != null)
			task.setJvm(jvm)
		if (jvmargs != null)
			task.setJvmargs(jvmargs)
		if (maxmemory != null)
			task.setMaxmemory(maxmemory)
		if (module != null)
			task.setModule(module)
		if (modulepath != null)
			task.setModulepath(Path(project, modulepath))
		if (modulepathref != null)
			task.setModulepathRef(Reference(project, modulepathref))
		if (clonevm != null)
			task.setCloneVm(clonevm)
		if (resultproperty != null)
			task.setResultProperty(resultproperty)
		if (failonerror != null)
			task.setFailonerror(failonerror)
		if (dir != null)
			task.setDir(project.resolveFile(dir))
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
		if (jvmversion != null)
			task.setJVMVersion(jvmversion)
		if (newenvironment != null)
			task.setNewenvironment(newenvironment)
		if (append != null)
			task.setAppend(append)
		if (timeout != null)
			task.setTimeout(timeout)
		if (nested != null)
			nested(KJava(task))
	}
}

class KJava(override val component: Java) :
	IAssertionsNested
{
	fun classpath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createClasspath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun bootclasspath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createBootclasspath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun modulepath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createModulepath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun upgrademodulepath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createUpgrademodulepath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun permissions(nested: (KPermissions.() -> Unit)? = null) {
		component.createPermissions().apply {
			_init(nested)
		}
	}
	fun arg(value: String? = null, line: String? = null, path: String? = null, pathref: String? = null, file: String? = null, prefix: String? = null, suffix: String? = null) {
		component.createArg().apply {
			component.project.setProjectReference(this)
			_init(value, line, path, pathref, file, prefix, suffix)
		}
	}
	fun jvmarg(value: String? = null, line: String? = null, path: String? = null, pathref: String? = null, file: String? = null, prefix: String? = null, suffix: String? = null) {
		component.createJvmarg().apply {
			component.project.setProjectReference(this)
			_init(value, line, path, pathref, file, prefix, suffix)
		}
	}
	fun sysproperty(key: String? = null, value: String? = null, path: String? = null, file: String? = null) {
		component.addSysproperty(Variable().apply {
			_init(component.project, key, value, path, file)
		})
	}
	fun env(key: String? = null, value: String? = null, path: String? = null, file: String? = null) {
		component.addEnv(Variable().apply {
			_init(component.project, key, value, path, file)
		})
	}
	override fun _addAssertions(value: Assertions) = component.addAssertions(value)
}
