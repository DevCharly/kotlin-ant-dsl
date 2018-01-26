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

import org.apache.tools.ant.taskdefs.Javac
import org.apache.tools.ant.taskdefs.compilers.CompilerAdapter
import org.apache.tools.ant.types.Path
import org.apache.tools.ant.types.Reference
import org.apache.tools.ant.types.selectors.AndSelector
import org.apache.tools.ant.types.selectors.ContainsRegexpSelector
import org.apache.tools.ant.types.selectors.ContainsSelector
import org.apache.tools.ant.types.selectors.DateSelector
import org.apache.tools.ant.types.selectors.DependSelector
import org.apache.tools.ant.types.selectors.DepthSelector
import org.apache.tools.ant.types.selectors.DifferentSelector
import org.apache.tools.ant.types.selectors.ExtendSelector
import org.apache.tools.ant.types.selectors.FileSelector
import org.apache.tools.ant.types.selectors.FilenameSelector
import org.apache.tools.ant.types.selectors.MajoritySelector
import org.apache.tools.ant.types.selectors.NoneSelector
import org.apache.tools.ant.types.selectors.NotSelector
import org.apache.tools.ant.types.selectors.OrSelector
import org.apache.tools.ant.types.selectors.PresentSelector
import org.apache.tools.ant.types.selectors.SelectSelector
import org.apache.tools.ant.types.selectors.SizeSelector
import org.apache.tools.ant.types.selectors.TypeSelector
import org.apache.tools.ant.types.selectors.modifiedselector.ModifiedSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.javac(
	srcdir: String? = null,
	destdir: String? = null,
	includes: String? = null,
	includesfile: String? = null,
	excludes: String? = null,
	excludesfile: String? = null,
	classpath: String? = null,
	sourcepath: String? = null,
	bootclasspath: String? = null,
	classpathref: String? = null,
	sourcepathref: String? = null,
	bootclasspathref: String? = null,
	extdirs: String? = null,
	debuglevel: String? = null,
	source: String? = null,
	nativeheaderdir: String? = null,
	modulesourcepath: String? = null,
	modulesourcepathref: String? = null,
	modulepath: String? = null,
	modulepathref: String? = null,
	upgrademodulepath: String? = null,
	upgrademodulepathref: String? = null,
	listfiles: Boolean? = null,
	failonerror: Boolean? = null,
	proceed: Boolean? = null,
	deprecation: Boolean? = null,
	memoryinitialsize: String? = null,
	memorymaximumsize: String? = null,
	encoding: String? = null,
	debug: Boolean? = null,
	optimize: Boolean? = null,
	depend: Boolean? = null,
	verbose: Boolean? = null,
	target: String? = null,
	release: String? = null,
	includeantruntime: Boolean? = null,
	includejavaruntime: Boolean? = null,
	fork: Boolean? = null,
	executable: String? = null,
	nowarn: Boolean? = null,
	tempdir: String? = null,
	updatedproperty: String? = null,
	errorproperty: String? = null,
	includedestclasses: Boolean? = null,
	createmissingpackageinfoclass: Boolean? = null,
	compiler: String? = null,
	defaultexcludes: Boolean? = null,
	casesensitive: Boolean? = null,
	followsymlinks: Boolean? = null,
	nested: (KJavac.() -> Unit)? = null)
{
	Javac().execute("javac") { task ->
		if (srcdir != null)
			task.setSrcdir(Path(project, srcdir))
		if (destdir != null)
			task.setDestdir(project.resolveFile(destdir))
		if (includes != null)
			task.setIncludes(includes)
		if (includesfile != null)
			task.setIncludesfile(project.resolveFile(includesfile))
		if (excludes != null)
			task.setExcludes(excludes)
		if (excludesfile != null)
			task.setExcludesfile(project.resolveFile(excludesfile))
		if (classpath != null)
			task.setClasspath(Path(project, classpath))
		if (sourcepath != null)
			task.setSourcepath(Path(project, sourcepath))
		if (bootclasspath != null)
			task.setBootclasspath(Path(project, bootclasspath))
		if (classpathref != null)
			task.setClasspathRef(Reference(project, classpathref))
		if (sourcepathref != null)
			task.setSourcepathRef(Reference(project, sourcepathref))
		if (bootclasspathref != null)
			task.setBootClasspathRef(Reference(project, bootclasspathref))
		if (extdirs != null)
			task.setExtdirs(Path(project, extdirs))
		if (debuglevel != null)
			task.setDebugLevel(debuglevel)
		if (source != null)
			task.setSource(source)
		if (nativeheaderdir != null)
			task.setNativeHeaderDir(project.resolveFile(nativeheaderdir))
		if (modulesourcepath != null)
			task.setModulesourcepath(Path(project, modulesourcepath))
		if (modulesourcepathref != null)
			task.setModulesourcepathRef(Reference(project, modulesourcepathref))
		if (modulepath != null)
			task.setModulepath(Path(project, modulepath))
		if (modulepathref != null)
			task.setModulepathRef(Reference(project, modulepathref))
		if (upgrademodulepath != null)
			task.setUpgrademodulepath(Path(project, upgrademodulepath))
		if (upgrademodulepathref != null)
			task.setUpgrademodulepathRef(Reference(project, upgrademodulepathref))
		if (listfiles != null)
			task.setListfiles(listfiles)
		if (failonerror != null)
			task.setFailonerror(failonerror)
		if (proceed != null)
			task.setProceed(proceed)
		if (deprecation != null)
			task.setDeprecation(deprecation)
		if (memoryinitialsize != null)
			task.setMemoryInitialSize(memoryinitialsize)
		if (memorymaximumsize != null)
			task.setMemoryMaximumSize(memorymaximumsize)
		if (encoding != null)
			task.setEncoding(encoding)
		if (debug != null)
			task.setDebug(debug)
		if (optimize != null)
			task.setOptimize(optimize)
		if (depend != null)
			task.setDepend(depend)
		if (verbose != null)
			task.setVerbose(verbose)
		if (target != null)
			task.setTarget(target)
		if (release != null)
			task.setRelease(release)
		if (includeantruntime != null)
			task.setIncludeantruntime(includeantruntime)
		if (includejavaruntime != null)
			task.setIncludejavaruntime(includejavaruntime)
		if (fork != null)
			task.setFork(fork)
		if (executable != null)
			task.setExecutable(executable)
		if (nowarn != null)
			task.setNowarn(nowarn)
		if (tempdir != null)
			task.setTempdir(project.resolveFile(tempdir))
		if (updatedproperty != null)
			task.setUpdatedProperty(updatedproperty)
		if (errorproperty != null)
			task.setErrorProperty(errorproperty)
		if (includedestclasses != null)
			task.setIncludeDestClasses(includedestclasses)
		if (createmissingpackageinfoclass != null)
			task.setCreateMissingPackageInfoClass(createmissingpackageinfoclass)
		if (compiler != null)
			task.setCompiler(compiler)
		if (defaultexcludes != null)
			task.setDefaultexcludes(defaultexcludes)
		if (casesensitive != null)
			task.setCaseSensitive(casesensitive)
		if (followsymlinks != null)
			task.setFollowSymlinks(followsymlinks)
		if (nested != null)
			nested(KJavac(task))
	}
}

class KJavac(override val component: Javac) :
	ICompilerAdapterNested,
	IFileSelectorNested,
	ISelectSelectorNested,
	IAndSelectorNested,
	IOrSelectorNested,
	INotSelectorNested,
	INoneSelectorNested,
	IMajoritySelectorNested,
	IDateSelectorNested,
	ISizeSelectorNested,
	IFilenameSelectorNested,
	IExtendSelectorNested,
	IContainsSelectorNested,
	IPresentSelectorNested,
	IDepthSelectorNested,
	IDependSelectorNested,
	IContainsRegexpSelectorNested,
	IDifferentSelectorNested,
	ITypeSelectorNested,
	IModifiedSelectorNested
{
	fun src(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createSrc().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun sourcepath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createSourcepath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun modulesourcepath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createModulesourcepath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun classpath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createClasspath().apply {
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
	fun bootclasspath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createBootclasspath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun extdirs(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createExtdirs().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun compilerarg(value: String? = null, line: String? = null, path: String? = null, pathref: String? = null, file: String? = null, prefix: String? = null, suffix: String? = null, implementation: String? = null, compiler: String? = null) {
		component.createCompilerArg().apply {
			component.project.setProjectReference(this)
			_init(value, line, path, pathref, file, prefix, suffix, implementation, compiler)
		}
	}
	fun compilerclasspath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createCompilerClasspath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun include(name: String? = null, If: String? = null, unless: String? = null) {
		component.createInclude().apply {
			_init(name, If, unless)
		}
	}
	fun includesfile(name: String? = null, If: String? = null, unless: String? = null) {
		component.createIncludesFile().apply {
			_init(name, If, unless)
		}
	}
	fun exclude(name: String? = null, If: String? = null, unless: String? = null) {
		component.createExclude().apply {
			_init(name, If, unless)
		}
	}
	fun excludesfile(name: String? = null, If: String? = null, unless: String? = null) {
		component.createExcludesFile().apply {
			_init(name, If, unless)
		}
	}
	fun patternset(includes: String? = null, excludes: String? = null, includesfile: String? = null, excludesfile: String? = null, nested: (KPatternSet.() -> Unit)? = null) {
		component.createPatternSet().apply {
			component.project.setProjectReference(this)
			_init(includes, excludes, includesfile, excludesfile, nested)
		}
	}
	override fun _addCompilerAdapter(value: CompilerAdapter) = component.add(value)
	override fun _addFileSelector(value: FileSelector) = component.add(value)
	override fun _addSelectSelector(value: SelectSelector) = component.addSelector(value)
	override fun _addAndSelector(value: AndSelector) = component.addAnd(value)
	override fun _addOrSelector(value: OrSelector) = component.addOr(value)
	override fun _addNotSelector(value: NotSelector) = component.addNot(value)
	override fun _addNoneSelector(value: NoneSelector) = component.addNone(value)
	override fun _addMajoritySelector(value: MajoritySelector) = component.addMajority(value)
	override fun _addDateSelector(value: DateSelector) = component.addDate(value)
	override fun _addSizeSelector(value: SizeSelector) = component.addSize(value)
	override fun _addFilenameSelector(value: FilenameSelector) = component.addFilename(value)
	override fun _addExtendSelector(value: ExtendSelector) = component.addCustom(value)
	override fun _addContainsSelector(value: ContainsSelector) = component.addContains(value)
	override fun _addPresentSelector(value: PresentSelector) = component.addPresent(value)
	override fun _addDepthSelector(value: DepthSelector) = component.addDepth(value)
	override fun _addDependSelector(value: DependSelector) = component.addDepend(value)
	override fun _addContainsRegexpSelector(value: ContainsRegexpSelector) = component.addContainsRegexp(value)
	override fun _addDifferentSelector(value: DifferentSelector) = component.addDifferent(value)
	override fun _addTypeSelector(value: TypeSelector) = component.addType(value)
	override fun _addModifiedSelector(value: ModifiedSelector) = component.addModified(value)
}
