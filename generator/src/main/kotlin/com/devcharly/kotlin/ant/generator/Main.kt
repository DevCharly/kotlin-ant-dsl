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

import org.apache.tools.ant.taskdefs.*
import org.apache.tools.ant.types.*
import org.apache.tools.ant.types.selectors.*
import org.apache.tools.ant.types.spi.Service
import org.apache.tools.ant.util.FileNameMapper
import java.io.FileWriter

fun main(args: Array<String>) {
	// Types
	genType(DirSet::class.java, baseInterface = ResourceCollection::class.java)
	genType(FileSet::class.java, baseInterface = ResourceCollection::class.java)
	genType(Manifest::class.java)
	genType(Manifest.Attribute::class.java)
	genType(Manifest.Section::class.java)
	genType(PatternSet::class.java)
	genTypeInit(PatternSet.NameEntry::class.java)
	genType(ResourceCollection::class.java)
	genType(TarFileSet::class.java, baseInterface = ResourceCollection::class.java, exclude = "srcresource")
	genType(ZipFileSet::class.java, baseInterface = ResourceCollection::class.java, exclude = "srcresource")

	// Selectors
	genType(FileSelector::class.java, folder = "selectors")

	// SPI
	genType(Provider::class.java, folder = "spi")//, order = "type provider")
	genType(Service::class.java, folder = "spi", order = "type provider")

	// Util
	genType(FileNameMapper::class.java, folder = "util")

	// Tasks
	genTask(BUnzip2::class.java, order = "src dest", exclude = "srcresource")
	genTask(BZip2::class.java, order = "src destfile", exclude = "zipfile srcresource")
	genTask(Copy::class.java)
	genTask(Delete::class.java)
	genTask(Echo::class.java)
	genTask(GUnzip::class.java, order = "src dest", exclude = "srcresource")
	genTask(GZip::class.java, order = "src destfile", exclude = "zipfile srcresource")
	genTask(Jar::class.java)
	genTask(Mkdir::class.java)
	genTask(Property::class.java, order = "name value location resource file url environment classpath classpathref prefix prefixvalues relative basedir")
	genTask(Tar::class.java)
	genTask(Touch::class.java)
	genTask(Zip::class.java)
}

fun genTask(taskType: Class<*>, order: String? = null, exclude: String? = null) {
	val task = reflectTask(taskType, order, exclude)
	val code = genTaskFile(task)

	writeCode("src/main/kotlin/com/devcharly/kotlin/ant/taskdefs/${task.taskName}.kt", code)
}

fun genType(typeType: Class<*>, baseInterface: Class<*>? = null, order: String? = null, exclude: String? = null, folder: String = "types") {
	val task = reflectTask(typeType, order, exclude)
	val code = genTypeFile(task, baseInterface)

	writeCode("src/main/kotlin/com/devcharly/kotlin/ant/$folder/${task.taskName}.kt", code)
}

fun genTypeInit(typeType: Class<*>, order: String? = null, exclude: String? = null, folder: String = "types") {
	val task = reflectTask(typeType, order, exclude)
	val code = genTypeInitFile(task)

	writeCode("src/main/kotlin/com/devcharly/kotlin/ant/$folder/${task.taskName}.kt", code)
}

fun writeCode(filename: String, code: String) {
	println("Generate $filename")
	FileWriter(filename).use {
		it.write(code.replace("\n", System.getProperty("line.separator")))
	}
}
