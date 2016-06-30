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

import org.apache.tools.ant.types.resources.FileResource

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

interface IFileResourceNested : INestedComponent {
	fun file(
		name: String? = null,
		exists: Boolean? = null,
		lastmodified: Long? = null,
		directory: Boolean? = null,
		size: Long? = null,
		file: String? = null,
		basedir: String? = null)
	{
		_addFileResource(FileResource().apply {
			component.project.setProjectReference(this);
			_init(name, exists, lastmodified, directory,
				size, file, basedir)
		})
	}

	fun _addFileResource(value: FileResource)
}

fun FileResource._init(
	name: String?,
	exists: Boolean?,
	lastmodified: Long?,
	directory: Boolean?,
	size: Long?,
	file: String?,
	basedir: String?)
{
	if (name != null)
		setName(name)
	if (exists != null)
		setExists(exists)
	if (lastmodified != null)
		setLastModified(lastmodified)
	if (directory != null)
		setDirectory(directory)
	if (size != null)
		setSize(size)
	if (file != null)
		setFile(project.resolveFile(file))
	if (basedir != null)
		setBaseDir(project.resolveFile(basedir))
}
