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
import org.apache.tools.ant.types.mappers.CutDirsMapper
import org.apache.tools.ant.types.optional.ScriptSelector
import org.apache.tools.ant.types.selectors.*
import org.apache.tools.ant.types.selectors.modifiedselector.ModifiedSelector
import org.apache.tools.ant.types.spi.Provider
import org.apache.tools.ant.types.spi.Service
import org.apache.tools.ant.util.*
import java.io.FileWriter

fun main(args: Array<String>) {
	// Types
	genType(DirSet::class.java, baseInterface = ResourceCollection::class.java)
	genType(FileList::class.java, baseInterface = ResourceCollection::class.java)
	genType(FileList.FileName::class.java)
	genType(FileSet::class.java, baseInterface = ResourceCollection::class.java)
	genType(Manifest::class.java)
	genType(Manifest.Attribute::class.java)
	genType(Manifest.Section::class.java)
	genType(Parameter::class.java)
	genType(Path::class.java, baseInterface = ResourceCollection::class.java)
	genType(Path.PathElement::class.java)
	genType(PatternSet::class.java)
	genTypeInit(PatternSet.NameEntry::class.java)
	genType(ResourceCollection::class.java)
	genType(TarFileSet::class.java, baseInterface = ResourceCollection::class.java)
	genEnum(TimeComparison::class.java)
	genType(ZipFileSet::class.java, baseInterface = ResourceCollection::class.java)

	// Selectors
	genType(AndSelector::class.java, folder = "selectors")
	genType(ContainsRegexpSelector::class.java, folder = "selectors")
	genType(ContainsSelector::class.java, folder = "selectors")
	genType(DateSelector::class.java, folder = "selectors")
	genType(DependSelector::class.java, folder = "selectors")
	genType(DepthSelector::class.java, folder = "selectors")
	genType(DifferentSelector::class.java, folder = "selectors")
	genType(ExtendSelector::class.java, folder = "selectors")
	genType(FilenameSelector::class.java, folder = "selectors")
	genType(FileSelector::class.java, folder = "selectors")
	genType(MajoritySelector::class.java, folder = "selectors")
	genType(ModifiedSelector::class.java, folder = "selectors")
	genType(NoneSelector::class.java, folder = "selectors")
	genType(NotSelector::class.java, folder = "selectors")
	genType(OrSelector::class.java, folder = "selectors")
	genType(PresentSelector::class.java, folder = "selectors")
	genType(ReadableSelector::class.java, folder = "selectors")
	genType(ScriptSelector::class.java, funName = "scriptselector", folder = "selectors")
	genType(SelectSelector::class.java, folder = "selectors")
	genType(SignedSelector::class.java, funName = "signedselector", folder = "selectors")
	genType(SizeSelector::class.java, folder = "selectors")
	genType(TypeSelector::class.java, folder = "selectors")
	genType(WritableSelector::class.java, folder = "selectors")

	// SPI
	genType(Provider::class.java, folder = "spi")//, order = "type provider")
	genType(Service::class.java, folder = "spi", order = "type provider")

	// Util
	genType(ChainedMapper::class.java, baseInterface = FileNameMapper::class.java, folder = "util")
	genType(CompositeMapper::class.java, baseInterface = FileNameMapper::class.java, folder = "util")
	genType(CutDirsMapper::class.java, baseInterface = FileNameMapper::class.java, folder = "util")
	genType(FlatFileNameMapper::class.java, funName = "flattenmapper", baseInterface = FileNameMapper::class.java, folder = "util")
	genType(FileNameMapper::class.java, folder = "util")
	genType(FirstMatchMapper::class.java, baseInterface = FileNameMapper::class.java, folder = "util")
	genType(GlobPatternMapper::class.java, funName = "globmapper", baseInterface = FileNameMapper::class.java, folder = "util")
	genType(IdentityMapper::class.java, baseInterface = FileNameMapper::class.java, folder = "util")
	genType(MergingMapper::class.java, funName = "mergemapper", baseInterface = FileNameMapper::class.java, folder = "util")
	genType(PackageNameMapper::class.java, funName = "packagemapper", baseInterface = FileNameMapper::class.java, folder = "util")
	genType(RegexpPatternMapper::class.java, funName = "regexpmapper", baseInterface = FileNameMapper::class.java, folder = "util")
	genType(UnPackageNameMapper::class.java, funName = "unpackagemapper", baseInterface = FileNameMapper::class.java, folder = "util")

	// Tasks
	genTask(BUnzip2::class.java, order = "src dest")
	genTask(BZip2::class.java, order = "src destfile", exclude = "zipfile")
	genTask(Checksum::class.java)
	genTask(Copy::class.java)
	genTask(Delete::class.java)
	genTask(Echo::class.java)
	genTask(FixCRLF::class.java)
	genTask(GUnzip::class.java, order = "src dest")
	genTask(GZip::class.java, order = "src destfile", exclude = "zipfile")
	genTask(Jar::class.java)
	genTask(Mkdir::class.java)
	genTask(Move::class.java)
	genTask(Property::class.java, order = "name value location resource file url environment classpath classpathref prefix prefixvalues relative basedir")
	genTask(Tar::class.java)
	genTask(Touch::class.java)
	genTask(Expand::class.java, taskName = "unjar", order = "src dest overwrite encoding")
	genTask(Untar::class.java, order = "src dest overwrite compression encoding")
	genTask(Expand::class.java, taskName = "unwar", order = "src dest overwrite encoding")
	genTask(Expand::class.java, taskName = "unzip", order = "src dest overwrite encoding")
	genTask(Zip::class.java)
}

val unsupportedNested = arrayOf(
	"filterchain",
	"filterset",
	"mapper",

	"",
	""
)

fun genTask(taskType: Class<*>, taskName: String? = null, order: String? = null, exclude: String? = null) {
	val task = reflectTask(taskType, taskName, order, exclude)
	val code = genTaskFile(task)

	writeCode(taskType, "taskdefs", code, taskName)
}

fun genType(typeType: Class<*>, funName: String? = null, baseInterface: Class<*>? = null, order: String? = null, exclude: String? = null, folder: String = "types") {
	val task = reflectTask(typeType, funName, order, exclude)
	val code = genTypeFile(task, baseInterface)

	writeCode(typeType, folder, code)
}

fun genTypeInit(typeType: Class<*>, order: String? = null, exclude: String? = null, folder: String = "types") {
	val task = reflectTask(typeType, null, order, exclude)
	val code = genTypeInitFile(task)

	writeCode(typeType, folder, code)
}

fun genEnum(enumType: Class<*>, order: String? = null, exclude: String? = null, folder: String = "types") {
	val task = reflectTask(enumType, null, order, exclude)
	val code = genEnumFile(task)

	writeCode(enumType, folder, code)
}

fun writeCode(cls: Class<*>, folder: String, code: String, taskName: String? = null) {
	val name = taskName ?: cls.name.substringAfterLast('.').replace('$', '-').toLowerCase()
	val filename = "src/main/kotlin/com/devcharly/kotlin/ant/$folder/$name.kt"

	println("Generate $filename")
	FileWriter(filename).use {
		it.write(code.replace("\n", System.getProperty("line.separator")))
	}
}
