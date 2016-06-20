/*
 * Copyright 2016 the original author or authors.
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

import org.apache.tools.ant.taskdefs.Touch

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun AntBuilder.touch(
	file: String? = null,
	millis: Long? = null,
	datetime: String? = null,
	mkdirs: Boolean? = null,
	verbose: Boolean? = null,
	pattern: String? = null)
{
	Touch().execute("touch") { task ->
		if (file != null)
			task.setFile(resolveFile(file))
		if (millis != null)
			task.setMillis(millis)
		if (datetime != null)
			task.setDatetime(datetime)
		if (mkdirs != null)
			task.setMkdirs(mkdirs)
		if (verbose != null)
			task.setVerbose(verbose)
		if (pattern != null)
			task.setPattern(pattern)
	}
}
