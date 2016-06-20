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

package com.devcharly.kotlin.ant.generator

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*
import org.objectweb.asm.Type
import java.util.*

/**
 * Get a AClass.
 *
 * Uses ASM to preserve the method order, which is mostly identical
 * to the order used in Ant task parameters documentation.
 */
fun aClass(cls: Class<*>): AClass {
	val resName = cls.name.replace('.', '/') + ".class"
	val visitor = AClassVisitor()
	cls.classLoader.getResourceAsStream(resName).use {
		ClassReader(it).accept(visitor, ClassReader.SKIP_CODE + ClassReader.SKIP_DEBUG)
	}
	return visitor.aClass
}

//---- class AClass -----------------------------------------------------------

class AClass( var name: String, val superName: String, val interfaces: Array<String>)
{
	val methods = ArrayList<AMethod>()
}

//---- class AMethod ----------------------------------------------------------

data class AMethod(val name: String, val parameterTypes: Array<out String>)

//---- class AClassVisitor ----------------------------------------------------

private class AClassVisitor
	: ClassVisitor(ASM5)
{
	lateinit var aClass: AClass

	override fun visit(version: Int, access: Int, name: String?,
	                   signature: String?, superName: String?,
	                   interfaces: Array<String>?)
	{
		aClass = AClass(name!!.replace('/', '.'), superName!!.replace('/', '.'),
				interfaces!!.map { s -> s.replace('/', '.') }.toTypedArray())
	}

	override fun visitMethod(access: Int, name: String, desc: String?,
	                         signature: String?, exceptions: Array<out String>?): MethodVisitor?
	{
		if (access and ACC_PUBLIC == 0 || access and (ACC_STATIC + ACC_DEPRECATED) != 0)
			return null

		val argTypes = Type.getArgumentTypes(desc)
		aClass.methods.add(AMethod(name, argTypes.map { type -> type.className }.toTypedArray()))
		return null
	}
}
