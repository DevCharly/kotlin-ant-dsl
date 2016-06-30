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

import org.apache.tools.ant.types.Parameter
import org.apache.tools.ant.types.Path
import org.apache.tools.ant.types.selectors.modifiedselector.ModifiedSelector

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IModifiedSelectorNested : INestedComponent {
	fun modified(
		algorithmclass: String? = null,
		comparatorclass: String? = null,
		cacheclass: String? = null,
		update: Boolean? = null,
		seldirs: Boolean? = null,
		selres: Boolean? = null,
		modified: Int? = null,
		delayupdate: Boolean? = null,
		cache: CacheName? = null,
		algorithm: AlgorithmName? = null,
		comparator: ComparatorName? = null,
		error: String? = null,
		nested: (KModifiedSelector.() -> Unit)? = null)
	{
		_addModifiedSelector(ModifiedSelector().apply {
			component.project.setProjectReference(this);
			_init(algorithmclass, comparatorclass, cacheclass, update,
				seldirs, selres, modified, delayupdate,
				cache, algorithm, comparator, error, nested)
		})
	}

	fun _addModifiedSelector(value: ModifiedSelector)
}

fun ModifiedSelector._init(
	algorithmclass: String?,
	comparatorclass: String?,
	cacheclass: String?,
	update: Boolean?,
	seldirs: Boolean?,
	selres: Boolean?,
	modified: Int?,
	delayupdate: Boolean?,
	cache: CacheName?,
	algorithm: AlgorithmName?,
	comparator: ComparatorName?,
	error: String?,
	nested: (KModifiedSelector.() -> Unit)?)
{
	if (algorithmclass != null)
		setAlgorithmClass(algorithmclass)
	if (comparatorclass != null)
		setComparatorClass(comparatorclass)
	if (cacheclass != null)
		setCacheClass(cacheclass)
	if (update != null)
		setUpdate(update)
	if (seldirs != null)
		setSeldirs(seldirs)
	if (selres != null)
		setSelres(selres)
	if (modified != null)
		setModified(modified)
	if (delayupdate != null)
		setDelayUpdate(delayupdate)
	if (cache != null)
		setCache(ModifiedSelector.CacheName().apply { this.value = cache.value })
	if (algorithm != null)
		setAlgorithm(ModifiedSelector.AlgorithmName().apply { this.value = algorithm.value })
	if (comparator != null)
		setComparator(ModifiedSelector.ComparatorName().apply { this.value = comparator.value })
	if (error != null)
		setError(error)
	if (nested != null)
		nested(KModifiedSelector(this))
}

class KModifiedSelector(val component: ModifiedSelector) {
	fun classpath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.addClasspath(Path(component.project).apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		})
	}
	fun param(name: String? = null, type: String? = null, value: String? = null) {
		component.addParam(Parameter().apply {
			_init(name, type, value)
		})
	}
}

enum class CacheName(val value: String) { PROPERTYFILE("propertyfile") }
enum class AlgorithmName(val value: String) { HASHVALUE("hashvalue"), DIGEST("digest"), CHECKSUM("checksum") }
enum class ComparatorName(val value: String) { EQUAL("equal"), RULE("rule") }
