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

import org.apache.tools.ant.taskdefs.Javadoc
import org.apache.tools.ant.taskdefs.Javadoc.ExtensionInfo
import org.apache.tools.ant.taskdefs.Javadoc.Html
import org.apache.tools.ant.taskdefs.Javadoc.PackageName
import org.apache.tools.ant.taskdefs.Javadoc.SourceFile
import org.apache.tools.ant.types.DirSet
import org.apache.tools.ant.types.FileSet
import org.apache.tools.ant.types.Path
import org.apache.tools.ant.types.Reference

/******************************************************************************
DO NOT EDIT - this file was generated
******************************************************************************/

fun Ant.javadoc(
	useexternalfile: Boolean? = null,
	defaultexcludes: Boolean? = null,
	maxmemory: String? = null,
	additionalparam: String? = null,
	sourcepath: String? = null,
	sourcepathref: String? = null,
	destdir: String? = null,
	sourcefiles: String? = null,
	packagenames: String? = null,
	excludepackagenames: String? = null,
	overview: String? = null,
	public: Boolean? = null,
	protected: Boolean? = null,
	Package: Boolean? = null,
	private: Boolean? = null,
	access: AccessType? = null,
	doclet: String? = null,
	docletpath: String? = null,
	docletpathref: String? = null,
	old: Boolean? = null,
	classpath: String? = null,
	classpathref: String? = null,
	bootclasspath: String? = null,
	bootclasspathref: String? = null,
	extdirs: String? = null,
	verbose: Boolean? = null,
	locale: String? = null,
	encoding: String? = null,
	version: Boolean? = null,
	use: Boolean? = null,
	author: Boolean? = null,
	splitindex: Boolean? = null,
	windowtitle: String? = null,
	doctitle: String? = null,
	header: String? = null,
	footer: String? = null,
	bottom: String? = null,
	linkoffline: String? = null,
	group: String? = null,
	link: String? = null,
	nodeprecated: Boolean? = null,
	nodeprecatedlist: Boolean? = null,
	notree: Boolean? = null,
	noindex: Boolean? = null,
	nohelp: Boolean? = null,
	nonavbar: Boolean? = null,
	serialwarn: Boolean? = null,
	stylesheetfile: String? = null,
	helpfile: String? = null,
	docencoding: String? = null,
	packagelist: String? = null,
	charset: String? = null,
	failonerror: Boolean? = null,
	failonwarning: Boolean? = null,
	source: String? = null,
	executable: String? = null,
	linksource: Boolean? = null,
	breakiterator: Boolean? = null,
	noqualifier: String? = null,
	includenosourcepackages: Boolean? = null,
	docfilessubdirs: Boolean? = null,
	excludedocfilessubdir: String? = null,
	postprocessgeneratedjavadocs: Boolean? = null,
	nested: (KJavadoc.() -> Unit)? = null)
{
	Javadoc().execute("javadoc") { task ->
		if (useexternalfile != null)
			task.setUseExternalFile(useexternalfile)
		if (defaultexcludes != null)
			task.setDefaultexcludes(defaultexcludes)
		if (maxmemory != null)
			task.setMaxmemory(maxmemory)
		if (additionalparam != null)
			task.setAdditionalparam(additionalparam)
		if (sourcepath != null)
			task.setSourcepath(Path(project, sourcepath))
		if (sourcepathref != null)
			task.setSourcepathRef(Reference(project, sourcepathref))
		if (destdir != null)
			task.setDestdir(project.resolveFile(destdir))
		if (sourcefiles != null)
			task.setSourcefiles(sourcefiles)
		if (packagenames != null)
			task.setPackagenames(packagenames)
		if (excludepackagenames != null)
			task.setExcludePackageNames(excludepackagenames)
		if (overview != null)
			task.setOverview(project.resolveFile(overview))
		if (public != null)
			task.setPublic(public)
		if (protected != null)
			task.setProtected(protected)
		if (Package != null)
			task.setPackage(Package)
		if (private != null)
			task.setPrivate(private)
		if (access != null)
			task.setAccess(Javadoc.AccessType().apply { this.value = access.value })
		if (doclet != null)
			task.setDoclet(doclet)
		if (docletpath != null)
			task.setDocletPath(Path(project, docletpath))
		if (docletpathref != null)
			task.setDocletPathRef(Reference(project, docletpathref))
		if (old != null)
			task.setOld(old)
		if (classpath != null)
			task.setClasspath(Path(project, classpath))
		if (classpathref != null)
			task.setClasspathRef(Reference(project, classpathref))
		if (bootclasspath != null)
			task.setBootclasspath(Path(project, bootclasspath))
		if (bootclasspathref != null)
			task.setBootClasspathRef(Reference(project, bootclasspathref))
		if (extdirs != null)
			task.setExtdirs(Path(project, extdirs))
		if (verbose != null)
			task.setVerbose(verbose)
		if (locale != null)
			task.setLocale(locale)
		if (encoding != null)
			task.setEncoding(encoding)
		if (version != null)
			task.setVersion(version)
		if (use != null)
			task.setUse(use)
		if (author != null)
			task.setAuthor(author)
		if (splitindex != null)
			task.setSplitindex(splitindex)
		if (windowtitle != null)
			task.setWindowtitle(windowtitle)
		if (doctitle != null)
			task.setDoctitle(doctitle)
		if (header != null)
			task.setHeader(header)
		if (footer != null)
			task.setFooter(footer)
		if (bottom != null)
			task.setBottom(bottom)
		if (linkoffline != null)
			task.setLinkoffline(linkoffline)
		if (group != null)
			task.setGroup(group)
		if (link != null)
			task.setLink(link)
		if (nodeprecated != null)
			task.setNodeprecated(nodeprecated)
		if (nodeprecatedlist != null)
			task.setNodeprecatedlist(nodeprecatedlist)
		if (notree != null)
			task.setNotree(notree)
		if (noindex != null)
			task.setNoindex(noindex)
		if (nohelp != null)
			task.setNohelp(nohelp)
		if (nonavbar != null)
			task.setNonavbar(nonavbar)
		if (serialwarn != null)
			task.setSerialwarn(serialwarn)
		if (stylesheetfile != null)
			task.setStylesheetfile(project.resolveFile(stylesheetfile))
		if (helpfile != null)
			task.setHelpfile(project.resolveFile(helpfile))
		if (docencoding != null)
			task.setDocencoding(docencoding)
		if (packagelist != null)
			task.setPackageList(packagelist)
		if (charset != null)
			task.setCharset(charset)
		if (failonerror != null)
			task.setFailonerror(failonerror)
		if (failonwarning != null)
			task.setFailonwarning(failonwarning)
		if (source != null)
			task.setSource(source)
		if (executable != null)
			task.setExecutable(executable)
		if (linksource != null)
			task.setLinksource(linksource)
		if (breakiterator != null)
			task.setBreakiterator(breakiterator)
		if (noqualifier != null)
			task.setNoqualifier(noqualifier)
		if (includenosourcepackages != null)
			task.setIncludeNoSourcePackages(includenosourcepackages)
		if (docfilessubdirs != null)
			task.setDocFilesSubDirs(docfilessubdirs)
		if (excludedocfilessubdir != null)
			task.setExcludeDocFilesSubDir(excludedocfilessubdir)
		if (postprocessgeneratedjavadocs != null)
			task.setPostProcessGeneratedJavadocs(postprocessgeneratedjavadocs)
		if (nested != null)
			nested(KJavadoc(task))
	}
}

class KJavadoc(override val component: Javadoc) :
	IFileSetNested
{
	fun arg(value: String? = null, line: String? = null, path: String? = null, pathref: String? = null, file: String? = null, prefix: String? = null, suffix: String? = null) {
		component.createArg().apply {
			component.project.setProjectReference(this)
			_init(value, line, path, pathref, file, prefix, suffix)
		}
	}
	fun sourcepath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createSourcepath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun source(file: String? = null) {
		component.addSource(SourceFile().apply {
			_init(component.project, file)
		})
	}
	fun Package(name: String? = null) {
		component.addPackage(PackageName().apply {
			_init(name)
		})
	}
	fun excludepackage(name: String? = null) {
		component.addExcludePackage(PackageName().apply {
			_init(name)
		})
	}
	fun doclet(name: String? = null, path: String? = null, pathref: String? = null, nested: (KDocletInfo.() -> Unit)? = null) {
		component.createDoclet().apply {
			component.project.setProjectReference(this)
			_init(name, path, pathref, nested)
		}
	}
	fun taglet(name: String? = null, path: String? = null, pathref: String? = null, nested: (KExtensionInfo.() -> Unit)? = null) {
		component.addTaglet(ExtensionInfo().apply {
			component.project.setProjectReference(this)
			_init(name, path, pathref, nested)
		})
	}
	fun classpath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createClasspath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun bootclasspath(location: String? = null, path: String? = null, cache: Boolean? = null, nested: (KPath.() -> Unit)? = null) {
		component.createBootclasspath().apply {
			component.project.setProjectReference(this)
			_init(location, path, cache, nested)
		}
	}
	fun doctitle(nested: (KHtml.() -> Unit)? = null) {
		component.addDoctitle(Html().apply {
			_init(nested)
		})
	}
	fun header(nested: (KHtml.() -> Unit)? = null) {
		component.addHeader(Html().apply {
			_init(nested)
		})
	}
	fun footer(nested: (KHtml.() -> Unit)? = null) {
		component.addFooter(Html().apply {
			_init(nested)
		})
	}
	fun bottom(nested: (KHtml.() -> Unit)? = null) {
		component.addBottom(Html().apply {
			_init(nested)
		})
	}
	fun link(href: String? = null, packagelistloc: String? = null, packagelisturl: String? = null, offline: Boolean? = null, resolvelink: Boolean? = null) {
		component.createLink().apply {
			_init(component.project, href, packagelistloc, packagelisturl, offline, resolvelink)
		}
	}
	fun tag(dir: String? = null, file: String? = null, includes: String? = null, excludes: String? = null, includesfile: String? = null, excludesfile: String? = null, defaultexcludes: Boolean? = null, casesensitive: Boolean? = null, followsymlinks: Boolean? = null, maxlevelsofsymlinks: Int? = null, erroronmissingdir: Boolean? = null, name: String? = null, scope: String? = null, enabled: Boolean? = null, nested: (KTagArgument.() -> Unit)? = null) {
		component.createTag().apply {
			component.project.setProjectReference(this)
			_init(dir, file, includes, excludes, includesfile, excludesfile, defaultexcludes, casesensitive, followsymlinks, maxlevelsofsymlinks, erroronmissingdir, name, scope, enabled, nested)
		}
	}
	fun group(title: String? = null, packages: String? = null, nested: (KGroupArgument.() -> Unit)? = null) {
		component.createGroup().apply {
			_init(title, packages, nested)
		}
	}
	fun packageset(dir: String? = null, file: String? = null, includes: String? = null, excludes: String? = null, includesfile: String? = null, excludesfile: String? = null, defaultexcludes: Boolean? = null, casesensitive: Boolean? = null, followsymlinks: Boolean? = null, maxlevelsofsymlinks: Int? = null, erroronmissingdir: Boolean? = null, nested: (KDirSet.() -> Unit)? = null) {
		component.addPackageset(DirSet().apply {
			component.project.setProjectReference(this)
			_init(dir, file, includes, excludes, includesfile, excludesfile, defaultexcludes, casesensitive, followsymlinks, maxlevelsofsymlinks, erroronmissingdir, nested)
		})
	}
	override fun _addFileSet(value: FileSet) = component.addFileset(value)
}

enum class AccessType(val value: String) { PROTECTED("protected"), PUBLIC("public"), PACKAGE("package"), PRIVATE("private") }
