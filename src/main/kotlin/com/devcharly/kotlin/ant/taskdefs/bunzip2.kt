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

import org.apache.tools.ant.taskdefs.BUnzip2
import org.apache.tools.ant.types.ResourceCollection

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.bunzip2(
	src: String? = null,
	dest: String? = null,
	nested: (KBUnzip2.() -> Unit)? = null)
{
	BUnzip2().execute("bunzip2") { task ->
		if (src != null)
			task.setSrc(project.resolveFile(src))
		if (dest != null)
			task.setDest(project.resolveFile(dest))
		if (nested != null)
			nested(KBUnzip2(task))
	}
}

class KBUnzip2(override val component: BUnzip2) :
	IResourceCollectionNested
{
	override fun _addResourceCollection(value: ResourceCollection) = component.addConfigured(value)
}
