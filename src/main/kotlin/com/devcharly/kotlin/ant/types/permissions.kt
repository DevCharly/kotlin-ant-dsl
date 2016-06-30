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

import org.apache.tools.ant.types.Permissions
import org.apache.tools.ant.types.Permissions.Permission

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IPermissionsNested {
	fun permissions(
		nested: (KPermissions.() -> Unit)? = null)
	{
		_addPermissions(Permissions().apply {
			_init(nested)
		})
	}

	fun _addPermissions(value: Permissions)
}

fun Permissions._init(
	nested: (KPermissions.() -> Unit)?)
{
	if (nested != null)
		nested(KPermissions(this))
}

class KPermissions(val component: Permissions) {
	fun grant(Class: String? = null, name: String? = null, actions: String? = null) {
		component.addConfiguredGrant(Permission().apply {
			_init(Class, name, actions)
		})
	}
	fun revoke(Class: String? = null, name: String? = null, actions: String? = null) {
		component.addConfiguredRevoke(Permission().apply {
			_init(Class, name, actions)
		})
	}
}
