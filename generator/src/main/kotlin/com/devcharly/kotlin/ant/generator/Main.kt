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
import org.apache.tools.ant.taskdefs.compilers.CompilerAdapter
import org.apache.tools.ant.taskdefs.optional.ReplaceRegExp
import org.apache.tools.ant.taskdefs.optional.unix.Chgrp
import org.apache.tools.ant.taskdefs.optional.unix.Chown
import org.apache.tools.ant.types.*
import org.apache.tools.ant.types.mappers.CutDirsMapper
import org.apache.tools.ant.types.optional.ScriptSelector
import org.apache.tools.ant.types.resources.*
import org.apache.tools.ant.types.selectors.*
import org.apache.tools.ant.types.selectors.modifiedselector.ModifiedSelector
import org.apache.tools.ant.types.spi.Provider
import org.apache.tools.ant.types.spi.Service
import org.apache.tools.ant.util.*
import java.io.FileWriter
import java.util.*

fun main(args: Array<String>) {
	// 1st pass: collect information
	initGen()

	// 2nd pass: generate code
	processGenQueue()
}

fun initGen() {
	// Compilers
	genType(CompilerAdapter::class.java, folder = "compilers")

	// Types
	genType(Assertions::class.java)
	genType(Assertions.DisabledAssertion::class.java, funName = "disable")
	genType(Assertions.EnabledAssertion::class.java, funName = "enable")
	genTypeInit(Commandline.Argument::class.java)
	genTypeInit(Commandline.Marker::class.java)
	genTypeInit(Environment.Variable::class.java)
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
	genType(Permissions::class.java)
	genTypeInit(Permissions.Permission::class.java)
	genTypeInit(RegularExpression::class.java)
	genType(ResourceCollection::class.java)
	genTypeInit(Substitution::class.java)
	genType(TarFileSet::class.java, baseInterface = ResourceCollection::class.java)
	genEnum(TimeComparison::class.java)
	genType(ZipFileSet::class.java, baseInterface = ResourceCollection::class.java)

	// Resources
	genType(FileResource::class.java, funName = "file", folder = "resources")
	genType(MultiRootFileSet::class.java, baseInterface = ResourceCollection::class.java, folder = "resources", exclude = "dir", order = "basedirs")

	// Selectors
	genType(AndSelector::class.java, funName = "and", folder = "selectors")
	genType(ContainsRegexpSelector::class.java, funName = "containsregexp", folder = "selectors")
	genType(ContainsSelector::class.java, funName = "contains", folder = "selectors")
	genType(DateSelector::class.java, folder = "selectors", funName="date")
	genType(DependSelector::class.java, folder = "selectors", funName="depend")
	genType(DepthSelector::class.java, folder = "selectors", funName="depth")
	genType(DifferentSelector::class.java, folder = "selectors", funName="different")
	genType(ExtendSelector::class.java, folder = "selectors", funName="custom")
	genType(FilenameSelector::class.java, folder = "selectors", funName="filename")
	genType(FileSelector::class.java, folder = "selectors", funName="file")
	genType(MajoritySelector::class.java, folder = "selectors", funName="majority")
	genType(ModifiedSelector::class.java, folder = "selectors", funName="modified")
	genType(NoneSelector::class.java, folder = "selectors", funName="none")
	genType(NotSelector::class.java, folder = "selectors", funName="not")
	genType(OrSelector::class.java, folder = "selectors", funName="or")
	genType(PresentSelector::class.java, folder = "selectors", funName="present")
	genType(ReadableSelector::class.java, folder = "selectors", funName="readable")
	genType(ScriptSelector::class.java, folder = "selectors")
	genType(SelectSelector::class.java, folder = "selectors", funName="selector")
	genType(SignedSelector::class.java, folder = "selectors")
	genType(SizeSelector::class.java, folder = "selectors", funName="size")
	genType(TypeSelector::class.java, folder = "selectors", funName="type")
	genType(WritableSelector::class.java, folder = "selectors", funName="writable")

	// SPI
	genType(Provider::class.java, folder = "spi")
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
	val executeonExclude = "spawn timeout executable command output input inputstring logerror error outputproperty errorproperty" +
		" failonerror newenvironment resolveexecutable searchpath resultproperty failifexecutionfails append vmlauncher relative" +
		" skipemptyfilesets dest forwardslash addsourcefile ignoremissing force"
	genTask(Chgrp::class.java, order = "file group parallel type maxparallel verbose os osfamily", exclude = "dir $executeonExclude")
	genTask(Chmod::class.java, order = "file dir perm includes excludes defaultexcludes parallel type maxparallel verbose os osfamily", exclude = executeonExclude)
	genTask(Chown::class.java, order = "file owner parallel type maxparallel verbose os osfamily", exclude = "dir $executeonExclude")
	genTask(Copy::class.java)
	genTask(Delete::class.java)
	genTask(Echo::class.java)
	genTask(ExecTask::class.java, taskName = "exec", order = "command executable dir os osfamily")
	genEnum(ExecuteOn.FileDirBoth::class.java, folder = "taskdefs")
	genTask(FixCRLF::class.java)
	genTask(GUnzip::class.java, order = "src dest")
	genTask(GZip::class.java, order = "src destfile", exclude = "zipfile")
	genTask(Jar::class.java)
	genTask(Java::class.java, order = "classname jar args classpath classpathref fork spawn jvm jvmargs maxmemory module")
	genTask(Javac::class.java, order = "srcdir destdir includes includesfile excludes excludesfile classpath sourcepath bootclasspath classpathref sourcepathref bootclasspathref extdirs")
	genTypeInit(Javac.ImplementationSpecificArgument::class.java, folder = "taskdefs")
	genTask(ManifestTask::class.java, taskName = "manifest", nestedClassName = "ManifestTask")
	genTask(ManifestClassPath::class.java)
	genTask(Mkdir::class.java)
	genTask(Move::class.java)
	genTask(Property::class.java, order = "name value location resource file url environment classpath classpathref prefix prefixvalues relative basedir")
	genTask(Recorder::class.java, taskName = "record")
	genTask(Replace::class.java, order = "file dir encoding token value")
	genType(Replace.NestedString::class.java, folder = "taskdefs")
	genType(Replace.Replacefilter::class.java, folder = "taskdefs")
	genTask(ReplaceRegExp::class.java)
	genTask(Tar::class.java)
	genTypeInit(Tar.TarFileSet::class.java, folder = "taskdefs")
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

	// java task
	"syspropertyset",
	"redirector",

	"",
	""
)

fun genTask(taskType: Class<*>, taskName: String? = null, nestedClassName: String? = null, order: String? = null, exclude: String? = null) {
	val task = reflectTask(taskType, taskName, taskName, order, exclude)
	if (nestedClassName != null)
		task.nestedClassName = nestedClassName
	queueGen(task, ::genTaskFile, "taskdefs", nestedClassName?.toLowerCase() ?: taskName)
}

fun genType(typeType: Class<*>, funName: String? = null, baseInterface: Class<*>? = null, order: String? = null, exclude: String? = null, folder: String = "types") {
	val task = reflectTask(typeType, null, funName, order, exclude)
	task.baseInterface = baseInterface
	queueGen(task, ::genTypeFile, folder)
}

fun genTypeInit(typeType: Class<*>, order: String? = null, exclude: String? = null, folder: String = "types") {
	val task = reflectTask(typeType, null, null, order, exclude)
	queueGen(task, ::genTypeInitFile, folder)
}

fun genEnum(enumType: Class<*>, order: String? = null, exclude: String? = null, folder: String = "types") {
	val task = reflectTask(enumType, null, null, order, exclude)
	queueGen(task, ::genEnumFile, folder)
}

fun queueGen(task: Task, genCode: (Task) -> String, folder: String, name: String? = null) {
	genQueue.add(QueueGenItem(task, genCode, folder, name))
}

class QueueGenItem(val task: Task,
				   val genCode: (Task) -> String,
				   val folder: String,
				   val name: String?)

var genQueue = ArrayList<QueueGenItem>()

fun processGenQueue() {
	genQueue.forEach {
		val code = it.genCode(it.task)
		writeCode(it.task.type, it.folder, it.name, code)
	}
}

fun writeCode(cls: Class<*>, folder: String, name: String?, code: String) {
	val name2 = name ?: cls.name.substringAfterLast('.').replace('$', '-').toLowerCase()
	val filename = "src/main/kotlin/com/devcharly/kotlin/ant/$folder/$name2.kt"

	println("Generate $filename")
	FileWriter(filename).use {
		it.write(code.replace("\n", System.getProperty("line.separator")))
	}
}
