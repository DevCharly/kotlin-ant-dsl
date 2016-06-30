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

import org.apache.tools.ant.taskdefs.Tar.TarFileSet

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun TarFileSet._init(
	dir: String?,
	file: String?,
	includes: String?,
	excludes: String?,
	includesfile: String?,
	excludesfile: String?,
	defaultexcludes: Boolean?,
	casesensitive: Boolean?,
	followsymlinks: Boolean?,
	maxlevelsofsymlinks: Int?,
	erroronmissingdir: Boolean?,
	src: String?,
	erroronmissingarchive: Boolean?,
	prefix: String?,
	fullpath: String?,
	encoding: String?,
	filemode: String?,
	dirmode: String?,
	username: String?,
	uid: Int?,
	group: String?,
	gid: Int?,
	mode: String?,
	preserveleadingslashes: Boolean?,
	nested: (KTarFileSet.() -> Unit)?)
{
	if (dir != null)
		setDir(project.resolveFile(dir))
	if (file != null)
		setFile(project.resolveFile(file))
	if (includes != null)
		setIncludes(includes)
	if (excludes != null)
		setExcludes(excludes)
	if (includesfile != null)
		setIncludesfile(project.resolveFile(includesfile))
	if (excludesfile != null)
		setExcludesfile(project.resolveFile(excludesfile))
	if (defaultexcludes != null)
		setDefaultexcludes(defaultexcludes)
	if (casesensitive != null)
		setCaseSensitive(casesensitive)
	if (followsymlinks != null)
		setFollowSymlinks(followsymlinks)
	if (maxlevelsofsymlinks != null)
		setMaxLevelsOfSymlinks(maxlevelsofsymlinks)
	if (erroronmissingdir != null)
		setErrorOnMissingDir(erroronmissingdir)
	if (src != null)
		setSrc(project.resolveFile(src))
	if (erroronmissingarchive != null)
		setErrorOnMissingArchive(erroronmissingarchive)
	if (prefix != null)
		setPrefix(prefix)
	if (fullpath != null)
		setFullpath(fullpath)
	if (encoding != null)
		setEncoding(encoding)
	if (filemode != null)
		setFileMode(filemode)
	if (dirmode != null)
		setDirMode(dirmode)
	if (username != null)
		setUserName(username)
	if (uid != null)
		setUid(uid)
	if (group != null)
		setGroup(group)
	if (gid != null)
		setGid(gid)
	if (mode != null)
		setMode(mode)
	if (preserveleadingslashes != null)
		setPreserveLeadingSlashes(preserveleadingslashes)
	if (nested != null)
		nested(KTarFileSet(this))
}
