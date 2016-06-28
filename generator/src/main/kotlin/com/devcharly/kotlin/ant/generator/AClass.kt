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

package com.devcharly.kotlin.ant.generator

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*
import org.objectweb.asm.Type
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.*

/**
 * Get a AClass.
 *
 * Uses ASM to preserve the method order, which is mostly identical
 * to the order used in Ant task parameters documentation.
 */
fun aClass(cls: Class<*>): AClass {
	// use reflection to get methods (including methods from superclasses)
	val methods = ArrayList<Method>()
	val deprecatedMethods = ArrayList<Method>()
	for (method in cls.methods) {
		if (method.isBridge ||
			method.isSynthetic ||
			method.isVarArgs ||
			method.isDefault ||
			Modifier.isStatic(method.modifiers) ||
			method.declaringClass == java.lang.Object::class.java)
		  continue

		if (method.getAnnotation(java.lang.Deprecated::class.java) != null) {
			deprecatedMethods.add(method)
			continue
		}

		methods.add(method)
	}

	// use ASM to order methods and remove deprecated methods
	val visitor = AClassVisitor(methods)
	var cls2: Class<*>? = cls
	while (cls2 != null && cls2 != java.lang.Object::class.java) {
		val resName = cls2.name.replace('.', '/') + ".class"
		cls.classLoader.getResourceAsStream(resName).use {
			ClassReader(it).accept(visitor, ClassReader.SKIP_CODE + ClassReader.SKIP_DEBUG)
		}

		cls2 = cls2.superclass
	}
	if (visitor.methods.size > 0)
		throw IllegalStateException()

	val aClass = AClass(cls)
	aClass.orderedMethods.addAll(visitor.orderedMethods)
	aClass.deprecatedMethods.addAll(visitor.deprecatedMethods)
	aClass.deprecatedMethods.addAll(deprecatedMethods)
	return aClass
}

//---- class AClass -----------------------------------------------------------

class AClass(var cls: Class<*>)
{
	val orderedMethods = ArrayList<Method>()
	val deprecatedMethods = ArrayList<Method>()
}

//---- class AClassVisitor ----------------------------------------------------

private class AClassVisitor(val methods: ArrayList<Method>)
	: ClassVisitor(ASM5)
{
	val orderedMethods = ArrayList<Method>()
	val deprecatedMethods = ArrayList<Method>()

	override fun visitMethod(access: Int, name: String, desc: String?,
	                         signature: String?, exceptions: Array<out String>?): MethodVisitor?
	{
		var i = 0
		for (method in methods) {
			if (method.name == name && Type.getType(method).descriptor == desc) {
				if (access and ACC_DEPRECATED == 0)
					orderedMethods.add(method)
				else
					deprecatedMethods.add(method)
				methods.removeAt(i)
				break
			}
			i++
		}
		return null
	}
}
