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

import org.apache.tools.ant.taskdefs.Recorder

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.record(
	name: String? = null,
	action: ActionChoices? = null,
	append: Boolean? = null,
	emacsmode: Boolean? = null,
	loglevel: VerbosityLevelChoices? = null)
{
	Recorder().execute("record") { task ->
		if (name != null)
			task.setName(name)
		if (action != null)
			task.setAction(Recorder.ActionChoices().apply { this.value = action.value })
		if (append != null)
			task.setAppend(append)
		if (emacsmode != null)
			task.setEmacsMode(emacsmode)
		if (loglevel != null)
			task.setLoglevel(Recorder.VerbosityLevelChoices().apply { this.value = loglevel.value })
	}
}

enum class ActionChoices(val value: String) { START("start"), STOP("stop") }
enum class VerbosityLevelChoices(val value: String) { ERROR("error"), WARN("warn"), WARNING("warning"), INFO("info"), VERBOSE("verbose"), DEBUG("debug") }
