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

import org.apache.tools.ant.types.Path
import org.apache.tools.ant.types.ResourceCollection

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IPathNested : INestedComponent {
	fun path(
		location: String? = null,
		path: String? = null,
		cache: Boolean? = null,
		nested: (KPath.() -> Unit)? = null)
	{
		_addPath(Path(component.project).apply {
			component.project.setProjectReference(this);
			_init(location, path, cache, nested)
		})
	}

	fun _addPath(value: Path)
}

fun IResourceCollectionNested.path(
	location: String? = null,
	path: String? = null,
	cache: Boolean? = null,
	nested: (KPath.() -> Unit)? = null)
{
	_addResourceCollection(Path(component.project).apply {
		component.project.setProjectReference(this);
		_init(location, path, cache, nested)
	})
}

fun Path._init(
	location: String?,
	path: String?,
	cache: Boolean?,
	nested: (KPath.() -> Unit)?)
{
	if (location != null)
		setLocation(project.resolveFile(location))
	if (path != null)
		setPath(path)
	if (cache != null)
		setCache(cache)
	if (nested != null)
		nested(KPath(this))
}

class KPath(override val component: Path) :
	IPathNested,
	IResourceCollectionNested
{
	fun pathelement(location: String? = null, path: String? = null) {
		component.createPathElement().apply {
			_init(component.project, location, path)
		}
	}
	fun existing(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.addExisting(Path(component.project).apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		})
	}
	fun extdirs(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.addExtdirs(Path(component.project).apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		})
	}
	override fun _addPath(value: Path) = component.add(value)
	override fun _addResourceCollection(value: ResourceCollection) = component.add(value)
}
