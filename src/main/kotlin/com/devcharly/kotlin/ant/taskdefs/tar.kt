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

import org.apache.tools.ant.taskdefs.Tar
import org.apache.tools.ant.types.ResourceCollection
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

fun Ant.tar(
	destfile: String? = null,
	basedir: String? = null,
	longfile: TarLongFileMode? = null,
	compression: TarCompressionMethod? = null,
	encoding: String? = null,
	includes: String? = null,
	excludes: String? = null,
	defaultexcludes: Boolean? = null,
	includesfile: String? = null,
	excludesfile: String? = null,
	casesensitive: Boolean? = null,
	followsymlinks: Boolean? = null,
	nested: (KTar.() -> Unit)? = null)
{
	Tar().execute("tar") { task ->
		if (destfile != null)
			task.setDestFile(project.resolveFile(destfile))
		if (basedir != null)
			task.setBasedir(project.resolveFile(basedir))
		if (longfile != null)
			task.setLongfile(Tar.TarLongFileMode().apply { this.value = longfile.value })
		if (compression != null)
			task.setCompression(Tar.TarCompressionMethod().apply { this.value = compression.value })
		if (encoding != null)
			task.setEncoding(encoding)
		if (includes != null)
			task.setIncludes(includes)
		if (excludes != null)
			task.setExcludes(excludes)
		if (defaultexcludes != null)
			task.setDefaultexcludes(defaultexcludes)
		if (includesfile != null)
			task.setIncludesfile(project.resolveFile(includesfile))
		if (excludesfile != null)
			task.setExcludesfile(project.resolveFile(excludesfile))
		if (casesensitive != null)
			task.setCaseSensitive(casesensitive)
		if (followsymlinks != null)
			task.setFollowSymlinks(followsymlinks)
		if (nested != null)
			nested(KTar(task))
	}
}

class KTar(override val component: Tar) :
	IFileSelectorNested,
	IResourceCollectionNested,
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
	fun tarfileset(dir: String? = null, file: String? = null, includes: String? = null, excludes: String? = null, includesfile: String? = null, excludesfile: String? = null, defaultexcludes: Boolean? = null, casesensitive: Boolean? = null, followsymlinks: Boolean? = null, maxlevelsofsymlinks: Int? = null, erroronmissingdir: Boolean? = null, src: String? = null, erroronmissingarchive: Boolean? = null, prefix: String? = null, fullpath: String? = null, encoding: String? = null, filemode: String? = null, dirmode: String? = null, username: String? = null, uid: Int? = null, group: String? = null, gid: Int? = null, mode: String? = null, preserveleadingslashes: Boolean? = null, nested: (KTarFileSet.() -> Unit)? = null) {
		component.createTarFileSet().apply {
			component.project.setProjectReference(this)
			_init(dir, file, includes, excludes, includesfile, excludesfile, defaultexcludes, casesensitive, followsymlinks, maxlevelsofsymlinks, erroronmissingdir, src, erroronmissingarchive, prefix, fullpath, encoding, filemode, dirmode, username, uid, group, gid, mode, preserveleadingslashes, nested)
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
	override fun _addFileSelector(value: FileSelector) = component.add(value)
	override fun _addResourceCollection(value: ResourceCollection) = component.add(value)
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

enum class TarLongFileMode(val value: String) { WARN("warn"), FAIL("fail"), TRUNCATE("truncate"), GNU("gnu"), POSIX("posix"), OMIT("omit") }
enum class TarCompressionMethod(val value: String) { NONE("none"), GZIP("gzip"), BZIP2("bzip2"), XZ("xz") }
