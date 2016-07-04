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

import java.net.URL
import org.apache.tools.ant.Project
import org.apache.tools.ant.taskdefs.Javadoc.LinkArgument

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun LinkArgument._init(
	project: Project,
	href: String?,
	packagelistloc: String?,
	packagelisturl: String?,
	offline: Boolean?,
	resolvelink: Boolean?)
{
	if (href != null)
		setHref(href)
	if (packagelistloc != null)
		setPackagelistLoc(project.resolveFile(packagelistloc))
	if (packagelisturl != null)
		setPackagelistURL(URL(packagelisturl))
	if (offline != null)
		setOffline(offline)
	if (resolvelink != null)
		setResolveLink(resolvelink)
}
