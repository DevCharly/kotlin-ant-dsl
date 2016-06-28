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

import org.apache.tools.ant.taskdefs.Jar
import org.apache.tools.ant.taskdefs.Manifest
import org.apache.tools.ant.taskdefs.Zip
import org.apache.tools.ant.types.ResourceCollection
import org.apache.tools.ant.types.selectors.FileSelector
import org.apache.tools.ant.types.spi.Service

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.jar(
	whenempty: WhenEmpty? = null,
	whenmanifestonly: WhenEmpty? = null,
	strict: StrictMode? = null,
	index: Boolean? = null,
	indexMetaInf: Boolean? = null,
	manifestEncoding: String? = null,
	manifest: String? = null,
	filesetmanifest: FilesetManifestConfig? = null,
	mergeClassPathAttributes: Boolean? = null,
	flattenAttributes: Boolean? = null,
	destFile: String? = null,
	basedir: String? = null,
	compress: Boolean? = null,
	filesonly: Boolean? = null,
	update: Boolean? = null,
	duplicate: Duplicate? = null,
	encoding: String? = null,
	keepCompression: Boolean? = null,
	comment: String? = null,
	level: Int? = null,
	roundUp: Boolean? = null,
	preserve0Permissions: Boolean? = null,
	useLanguageEncodingFlag: Boolean? = null,
	createUnicodeExtraFields: UnicodeExtraField? = null,
	fallBackToUTF8: Boolean? = null,
	zip64Mode: Zip64ModeAttribute? = null,
	includes: String? = null,
	excludes: String? = null,
	defaultexcludes: Boolean? = null,
	includesfile: String? = null,
	excludesfile: String? = null,
	caseSensitive: Boolean? = null,
	followSymlinks: Boolean? = null,
	nested: (KJar.() -> Unit)? = null)
{
	Jar().execute("jar") { task ->
		if (whenempty != null)
			task.setWhenempty(Zip.WhenEmpty().apply { value = whenempty.value })
		if (whenmanifestonly != null)
			task.setWhenmanifestonly(Zip.WhenEmpty().apply { value = whenmanifestonly.value })
		if (strict != null)
			task.setStrict(Jar.StrictMode().apply { value = strict.value })
		if (index != null)
			task.setIndex(index)
		if (indexMetaInf != null)
			task.setIndexMetaInf(indexMetaInf)
		if (manifestEncoding != null)
			task.setManifestEncoding(manifestEncoding)
		if (manifest != null)
			task.setManifest(project.resolveFile(manifest))
		if (filesetmanifest != null)
			task.setFilesetmanifest(Jar.FilesetManifestConfig().apply { value = filesetmanifest.value })
		if (mergeClassPathAttributes != null)
			task.setMergeClassPathAttributes(mergeClassPathAttributes)
		if (flattenAttributes != null)
			task.setFlattenAttributes(flattenAttributes)
		if (destFile != null)
			task.setDestFile(project.resolveFile(destFile))
		if (basedir != null)
			task.setBasedir(project.resolveFile(basedir))
		if (compress != null)
			task.setCompress(compress)
		if (filesonly != null)
			task.setFilesonly(filesonly)
		if (update != null)
			task.setUpdate(update)
		if (duplicate != null)
			task.setDuplicate(Zip.Duplicate().apply { value = duplicate.value })
		if (encoding != null)
			task.setEncoding(encoding)
		if (keepCompression != null)
			task.setKeepCompression(keepCompression)
		if (comment != null)
			task.setComment(comment)
		if (level != null)
			task.setLevel(level)
		if (roundUp != null)
			task.setRoundUp(roundUp)
		if (preserve0Permissions != null)
			task.setPreserve0Permissions(preserve0Permissions)
		if (useLanguageEncodingFlag != null)
			task.setUseLanguageEncodingFlag(useLanguageEncodingFlag)
		if (createUnicodeExtraFields != null)
			task.setCreateUnicodeExtraFields(Zip.UnicodeExtraField().apply { value = createUnicodeExtraFields.value })
		if (fallBackToUTF8 != null)
			task.setFallBackToUTF8(fallBackToUTF8)
		if (zip64Mode != null)
			task.setZip64Mode(Zip.Zip64ModeAttribute().apply { value = zip64Mode.value })
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
		if (caseSensitive != null)
			task.setCaseSensitive(caseSensitive)
		if (followSymlinks != null)
			task.setFollowSymlinks(followSymlinks)
		if (nested != null)
			nested(KJar(task))
	}
}

class KJar(override val component: Jar) : IFileSelectorNested, IResourceCollectionNested {
	fun manifest(nested: (KManifest.() -> Unit)? = null) {
		component.addConfiguredManifest(Manifest().apply {
			_init(nested)
		})
	}
	fun service(provider: String? = null, type: String? = null) {
		component.addConfiguredService(Service().apply {
			_init(provider, type)
		})
	}
	fun include(name: String? = null, If: String? = null, unless: String? = null) {
		component.createInclude().apply {
			_init(name, If, unless)
		}
	}
	fun exclude(name: String? = null, If: String? = null, unless: String? = null) {
		component.createExclude().apply {
			_init(name, If, unless)
		}
	}
	fun patternset(includes: String? = null, excludes: String? = null, includesfile: String? = null, excludesfile: String? = null, nested: (KPatternSet.() -> Unit)? = null) {
		component.createPatternSet().apply {
			_init(includes, excludes, includesfile, excludesfile, nested)
		}
	}
	override fun _addFileSelector(value: FileSelector) = component.add(value)
	override fun _addResourceCollection(value: ResourceCollection) = component.add(value)
}

enum class StrictMode(val value: String) { FAIL("fail"), WARN("warn"), IGNORE("ignore") }
enum class FilesetManifestConfig(val value: String) { SKIP("skip"), MERGE("merge"), MERGEWITHOUTMAIN("mergewithoutmain") }
