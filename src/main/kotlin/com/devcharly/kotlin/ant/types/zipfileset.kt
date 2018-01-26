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

import org.apache.tools.ant.types.ResourceCollection
import org.apache.tools.ant.types.ZipFileSet
import org.apache.tools.ant.types.selectors.AndSelector
import org.apache.tools.ant.types.selectors.ContainsRegexpSelector
import org.apache.tools.ant.types.selectors.ContainsSelector
import org.apache.tools.ant.types.selectors.DateSelector
import org.apache.tools.ant.types.selectors.DependSelector
import org.apache.tools.ant.types.selectors.DepthSelector
import org.apache.tools.ant.types.selectors.DifferentSelector
import org.apache.tools.ant.types.selectors.ExecutableSelector
import org.apache.tools.ant.types.selectors.ExtendSelector
import org.apache.tools.ant.types.selectors.FileSelector
import org.apache.tools.ant.types.selectors.FilenameSelector
import org.apache.tools.ant.types.selectors.MajoritySelector
import org.apache.tools.ant.types.selectors.NoneSelector
import org.apache.tools.ant.types.selectors.NotSelector
import org.apache.tools.ant.types.selectors.OrSelector
import org.apache.tools.ant.types.selectors.OwnedBySelector
import org.apache.tools.ant.types.selectors.PresentSelector
import org.apache.tools.ant.types.selectors.ReadableSelector
import org.apache.tools.ant.types.selectors.SelectSelector
import org.apache.tools.ant.types.selectors.SizeSelector
import org.apache.tools.ant.types.selectors.SymlinkSelector
import org.apache.tools.ant.types.selectors.TypeSelector
import org.apache.tools.ant.types.selectors.WritableSelector
import org.apache.tools.ant.types.selectors.modifiedselector.ModifiedSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IZipFileSetNested : INestedComponent {
	fun zipfileset(
		dir: String? = null,
		file: String? = null,
		includes: String? = null,
		excludes: String? = null,
		includesfile: String? = null,
		excludesfile: String? = null,
		defaultexcludes: Boolean? = null,
		casesensitive: Boolean? = null,
		followsymlinks: Boolean? = null,
		maxlevelsofsymlinks: Int? = null,
		erroronmissingdir: Boolean? = null,
		src: String? = null,
		erroronmissingarchive: Boolean? = null,
		prefix: String? = null,
		fullpath: String? = null,
		encoding: String? = null,
		filemode: String? = null,
		dirmode: String? = null,
		nested: (KZipFileSet.() -> Unit)? = null)
	{
		_addZipFileSet(ZipFileSet().apply {
			component.project.setProjectReference(this);
			_init(dir, file, includes, excludes,
				includesfile, excludesfile, defaultexcludes, casesensitive,
				followsymlinks, maxlevelsofsymlinks, erroronmissingdir, src,
				erroronmissingarchive, prefix, fullpath, encoding,
				filemode, dirmode, nested)
		})
	}

	fun _addZipFileSet(value: ZipFileSet)
}

fun IFileSetNested.zipfileset(
	dir: String? = null,
	file: String? = null,
	includes: String? = null,
	excludes: String? = null,
	includesfile: String? = null,
	excludesfile: String? = null,
	defaultexcludes: Boolean? = null,
	casesensitive: Boolean? = null,
	followsymlinks: Boolean? = null,
	maxlevelsofsymlinks: Int? = null,
	erroronmissingdir: Boolean? = null,
	src: String? = null,
	erroronmissingarchive: Boolean? = null,
	prefix: String? = null,
	fullpath: String? = null,
	encoding: String? = null,
	filemode: String? = null,
	dirmode: String? = null,
	nested: (KZipFileSet.() -> Unit)? = null)
{
	_addFileSet(ZipFileSet().apply {
		component.project.setProjectReference(this);
		_init(dir, file, includes, excludes,
			includesfile, excludesfile, defaultexcludes, casesensitive,
			followsymlinks, maxlevelsofsymlinks, erroronmissingdir, src,
			erroronmissingarchive, prefix, fullpath, encoding,
			filemode, dirmode, nested)
	})
}

fun IResourceCollectionNested.zipfileset(
	dir: String? = null,
	file: String? = null,
	includes: String? = null,
	excludes: String? = null,
	includesfile: String? = null,
	excludesfile: String? = null,
	defaultexcludes: Boolean? = null,
	casesensitive: Boolean? = null,
	followsymlinks: Boolean? = null,
	maxlevelsofsymlinks: Int? = null,
	erroronmissingdir: Boolean? = null,
	src: String? = null,
	erroronmissingarchive: Boolean? = null,
	prefix: String? = null,
	fullpath: String? = null,
	encoding: String? = null,
	filemode: String? = null,
	dirmode: String? = null,
	nested: (KZipFileSet.() -> Unit)? = null)
{
	_addResourceCollection(ZipFileSet().apply {
		component.project.setProjectReference(this);
		_init(dir, file, includes, excludes,
			includesfile, excludesfile, defaultexcludes, casesensitive,
			followsymlinks, maxlevelsofsymlinks, erroronmissingdir, src,
			erroronmissingarchive, prefix, fullpath, encoding,
			filemode, dirmode, nested)
	})
}

fun ZipFileSet._init(
	dir: String?,
	file: String?,
	includes: String?,
	excludes: String?,
	includesfile: String?,
	excludesfile: String?,
	defaultexcludes: Boolean?,
	casesensitive: Boolean?,
	followsymlinks: Boolean?,
	maxlevelsofsymlinks: Int?,
	erroronmissingdir: Boolean?,
	src: String?,
	erroronmissingarchive: Boolean?,
	prefix: String?,
	fullpath: String?,
	encoding: String?,
	filemode: String?,
	dirmode: String?,
	nested: (KZipFileSet.() -> Unit)?)
{
	if (dir != null)
		setDir(project.resolveFile(dir))
	if (file != null)
		setFile(project.resolveFile(file))
	if (includes != null)
		setIncludes(includes)
	if (excludes != null)
		setExcludes(excludes)
	if (includesfile != null)
		setIncludesfile(project.resolveFile(includesfile))
	if (excludesfile != null)
		setExcludesfile(project.resolveFile(excludesfile))
	if (defaultexcludes != null)
		setDefaultexcludes(defaultexcludes)
	if (casesensitive != null)
		setCaseSensitive(casesensitive)
	if (followsymlinks != null)
		setFollowSymlinks(followsymlinks)
	if (maxlevelsofsymlinks != null)
		setMaxLevelsOfSymlinks(maxlevelsofsymlinks)
	if (erroronmissingdir != null)
		setErrorOnMissingDir(erroronmissingdir)
	if (src != null)
		setSrc(project.resolveFile(src))
	if (erroronmissingarchive != null)
		setErrorOnMissingArchive(erroronmissingarchive)
	if (prefix != null)
		setPrefix(prefix)
	if (fullpath != null)
		setFullpath(fullpath)
	if (encoding != null)
		setEncoding(encoding)
	if (filemode != null)
		setFileMode(filemode)
	if (dirmode != null)
		setDirMode(dirmode)
	if (nested != null)
		nested(KZipFileSet(this))
}

class KZipFileSet(override val component: ZipFileSet) :
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
	IDifferentSelectorNested,
	IFilenameSelectorNested,
	ITypeSelectorNested,
	IExtendSelectorNested,
	IContainsSelectorNested,
	IPresentSelectorNested,
	IDepthSelectorNested,
	IDependSelectorNested,
	IContainsRegexpSelectorNested,
	IModifiedSelectorNested,
	IReadableSelectorNested,
	IWritableSelectorNested,
	IExecutableSelectorNested,
	ISymlinkSelectorNested,
	IOwnedBySelectorNested
{
	fun patternset(includes: String? = null, excludes: String? = null, includesfile: String? = null, excludesfile: String? = null, nested: (KPatternSet.() -> Unit)? = null) {
		component.createPatternSet().apply {
			component.project.setProjectReference(this)
			_init(includes, excludes, includesfile, excludesfile, nested)
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
	override fun _addFileSelector(value: FileSelector) = component.add(value)
	override fun _addResourceCollection(value: ResourceCollection) = component.addConfigured(value)
	override fun _addSelectSelector(value: SelectSelector) = component.addSelector(value)
	override fun _addAndSelector(value: AndSelector) = component.addAnd(value)
	override fun _addOrSelector(value: OrSelector) = component.addOr(value)
	override fun _addNotSelector(value: NotSelector) = component.addNot(value)
	override fun _addNoneSelector(value: NoneSelector) = component.addNone(value)
	override fun _addMajoritySelector(value: MajoritySelector) = component.addMajority(value)
	override fun _addDateSelector(value: DateSelector) = component.addDate(value)
	override fun _addSizeSelector(value: SizeSelector) = component.addSize(value)
	override fun _addDifferentSelector(value: DifferentSelector) = component.addDifferent(value)
	override fun _addFilenameSelector(value: FilenameSelector) = component.addFilename(value)
	override fun _addTypeSelector(value: TypeSelector) = component.addType(value)
	override fun _addExtendSelector(value: ExtendSelector) = component.addCustom(value)
	override fun _addContainsSelector(value: ContainsSelector) = component.addContains(value)
	override fun _addPresentSelector(value: PresentSelector) = component.addPresent(value)
	override fun _addDepthSelector(value: DepthSelector) = component.addDepth(value)
	override fun _addDependSelector(value: DependSelector) = component.addDepend(value)
	override fun _addContainsRegexpSelector(value: ContainsRegexpSelector) = component.addContainsRegexp(value)
	override fun _addModifiedSelector(value: ModifiedSelector) = component.addModified(value)
	override fun _addReadableSelector(value: ReadableSelector) = component.addReadable(value)
	override fun _addWritableSelector(value: WritableSelector) = component.addWritable(value)
	override fun _addExecutableSelector(value: ExecutableSelector) = component.addExecutable(value)
	override fun _addSymlinkSelector(value: SymlinkSelector) = component.addSymlink(value)
	override fun _addOwnedBySelector(value: OwnedBySelector) = component.addOwnedBy(value)
}
