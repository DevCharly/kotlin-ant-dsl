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

package com.devcharly.kotlin.ant.converter

import java.io.FileReader
import java.io.FileWriter

const val NAME				= "[\\w-_.]+"
const val STRING			= "\"[^\"]*?\""
const val WHITESPACES		= "\\s+"
const val WHITESPACES_OPT	= "(?:\\s*)"

const val ATTRIBUTE			= "(${NAME})${WHITESPACES_OPT}=${WHITESPACES_OPT}(${STRING})"
const val ATTRIBUTES		= "(${ATTRIBUTE}${WHITESPACES_OPT})*"

const val TAG				= "<(${NAME})${WHITESPACES_OPT}(${ATTRIBUTES})/>"
const val TAG_OPEN			= "<(${NAME})${WHITESPACES_OPT}(${ATTRIBUTES})>"
const val TAG_CLOSE			= "</(${NAME})>"

/**
 * Converts Ant XML files to Kotlin Ant DSL.
 *
 * The primary goal is support migration of Ant builds to Kotlin based build systems (e.g. Kobalt)
 *
 * Note that Kotlin Ant DSL does not support some Ant tags (e.g. <project> and <target>).
 * So you can not compile/run the output, but you can copy the parts that you need.
 *
 * Uses simple regex string manipulation to preserve formatting.
 */
fun main(args: Array<String>) {
	args.forEach { convertAntXml2Kotlin(it) }
}

fun convertAntXml2Kotlin(fileName: String) {
	println("Convert $fileName")

	// read Ant XML file
	var s = FileReader(fileName).readText()

	// determine used line separator
	val lineSeparator = if (s.contains("\r\n")) "\r\n" else "\n"

	// <?xml version="1.0"?>
	s = s.replace(Regex("^\\s*<\\?xml.*\\?>\\s*"), "")

	// comments
	s = s.replace(Regex("<!--(.+?)-->", RegexOption.DOT_MATCHES_ALL), "/*$1*/")

	// top-level elements must be assigned to a val in Kotlin
	s = s.replace("<project", "val p = <project")

	// tag
	s = s.replace(Regex(TAG), { match ->
		"${match.groupValues[1]}(${attrs2params(match.groupValues[2])})"
	})

	// open tag
	s = s.replace(Regex(TAG_OPEN), { match ->
		val name = match.groupValues[1]
		val params = attrs2params(match.groupValues[2])
		if (params.isEmpty())
			"$name {"
		else
			"$name($params) {"
	})

	// close tag
	s = s.replace(Regex(TAG_CLOSE), "}")

	// insert imports
	s = "import com.devcharly.kotlin.ant.*$lineSeparator$lineSeparator$s"

	// write Kotlin file
	val kotlinFileName = fileName.removeSuffix(".xml") + ".kt"
	println("     to $kotlinFileName")
	FileWriter(kotlinFileName).apply {
		write(s)
		flush()
	}
}

private fun attrs2params(attrs: String): String {
	var s = attrs.replace(Regex("(?:${ATTRIBUTE})(${WHITESPACES_OPT})"), { match ->
		"${match.groupValues[1]}=${attr2paramValue(match.groupValues[2])},${match.groupValues[3]}"
	})

	// remove trailing ',', but keep whitespace (in case it contains line separator
	s = s.replace(Regex(",(\\s*)$"), "$1")

	// remove trailing spaces
	s = s.trimEnd(' ')

	return s
}

private fun attr2paramValue(attrValue: String): String {
	return when(attrValue) {
		"\"true\"" -> "true"
		"\"false\"" -> "false"
		"\"yes\"" -> "true"
		"\"no\"" -> "false"
		else -> attrValue
	}
}

private fun printArray(a: Array<Any>) {
	println("---- ${a.size} ----")
	a.forEachIndexed { i, any -> println("$i: $any") }
}
