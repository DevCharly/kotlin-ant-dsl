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

import org.apache.tools.ant.types.Commandline.Argument
import org.apache.tools.ant.types.Path
import org.apache.tools.ant.types.Reference

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Argument._init(
	value: String?,
	line: String?,
	path: String?,
	pathref: String?,
	file: String?,
	prefix: String?,
	suffix: String?)
{
	if (value != null)
		setValue(value)
	if (line != null)
		setLine(line)
	if (path != null)
		setPath(Path(project, path))
	if (pathref != null)
		setPathref(Reference(project, pathref))
	if (file != null)
		setFile(project.resolveFile(file))
	if (prefix != null)
		setPrefix(prefix)
	if (suffix != null)
		setSuffix(suffix)
}
