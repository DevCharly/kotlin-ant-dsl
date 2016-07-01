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

import org.apache.tools.ant.taskdefs.ManifestClassPath
import org.apache.tools.ant.types.Path

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.manifestclasspath(
	property: String? = null,
	jarfile: String? = null,
	maxparentlevels: Int? = null,
	nested: (KManifestClassPath.() -> Unit)? = null)
{
	ManifestClassPath().execute("manifestclasspath") { task ->
		if (property != null)
			task.setProperty(property)
		if (jarfile != null)
			task.setJarFile(project.resolveFile(jarfile))
		if (maxparentlevels != null)
			task.setMaxParentLevels(maxparentlevels)
		if (nested != null)
			nested(KManifestClassPath(task))
	}
}

class KManifestClassPath(val component: ManifestClassPath) {
	fun classpath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.addClassPath(Path(component.project).apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		})
	}
}
