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

import org.apache.tools.ant.taskdefs.Javadoc.DocletInfo
import org.apache.tools.ant.types.Path
import org.apache.tools.ant.types.Reference

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/


fun DocletInfo._init(
	name: String?,
	path: String?,
	pathref: String?,
	nested: (KDocletInfo.() -> Unit)?)
{
	if (name != null)
		setName(name)
	if (path != null)
		setPath(Path(project, path))
	if (pathref != null)
		setPathRef(Reference(project, pathref))
	if (nested != null)
		nested(KDocletInfo(this))
}

class KDocletInfo(val component: DocletInfo) {
	fun path(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createPath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun param(name: String? = null, value: String? = null) {
		component.createParam().apply {
			_init(name, value)
		}
	}
}
