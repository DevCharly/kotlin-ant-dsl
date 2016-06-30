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

import org.apache.tools.ant.taskdefs.Delete
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

fun Ant.delete(
	file: String? = null,
	dir: String? = null,
	verbose: Boolean? = null,
	quiet: Boolean? = null,
	failonerror: Boolean? = null,
	deleteonexit: Boolean? = null,
	includeemptydirs: Boolean? = null,
	performgconfaileddelete: Boolean? = null,
	includes: String? = null,
	excludes: String? = null,
	defaultexcludes: Boolean? = null,
	includesfile: String? = null,
	excludesfile: String? = null,
	casesensitive: Boolean? = null,
	followsymlinks: Boolean? = null,
	removenotfollowedsymlinks: Boolean? = null,
	nested: (KDelete.() -> Unit)? = null)
{
	Delete().execute("delete") { task ->
		if (file != null)
			task.setFile(project.resolveFile(file))
		if (dir != null)
			task.setDir(project.resolveFile(dir))
		if (verbose != null)
			task.setVerbose(verbose)
		if (quiet != null)
			task.setQuiet(quiet)
		if (failonerror != null)
			task.setFailOnError(failonerror)
		if (deleteonexit != null)
			task.setDeleteOnExit(deleteonexit)
		if (includeemptydirs != null)
			task.setIncludeEmptyDirs(includeemptydirs)
		if (performgconfaileddelete != null)
			task.setPerformGcOnFailedDelete(performgconfaileddelete)
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
		if (removenotfollowedsymlinks != null)
			task.setRemoveNotFollowedSymlinks(removenotfollowedsymlinks)
		if (nested != null)
			nested(KDelete(task))
	}
}

class KDelete(override val component: Delete) :
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
	IModifiedSelectorNested,
	IDifferentSelectorNested,
	ITypeSelectorNested
{
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
	override fun _addModifiedSelector(value: ModifiedSelector) = component.addModified(value)
	override fun _addDifferentSelector(value: DifferentSelector) = component.addDifferent(value)
	override fun _addTypeSelector(value: TypeSelector) = component.addType(value)
}
