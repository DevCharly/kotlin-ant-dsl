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

import org.apache.tools.ant.taskdefs.Javadoc.GroupArgument
import org.apache.tools.ant.taskdefs.Javadoc.Html
import org.apache.tools.ant.taskdefs.Javadoc.PackageName

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/


fun GroupArgument._init(
	title: String?,
	packages: String?,
	nested: (KGroupArgument.() -> Unit)?)
{
	if (title != null)
		setTitle(title)
	if (packages != null)
		setPackages(packages)
	if (nested != null)
		nested(KGroupArgument(this))
}

class KGroupArgument(val component: GroupArgument) {
	fun title(nested: (KHtml.() -> Unit)? = null) {
		component.addTitle(Html().apply {
			_init(nested)
		})
	}
	fun Package(name: String? = null) {
		component.addPackage(PackageName().apply {
			_init(name)
		})
	}
}
