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
fun aClass(cls: Class<*>, stopClass: Class<*>? = null): AClass {
	// use reflection to get methods (including methods from superclasses)
	val methods = ArrayList<Method>()
	for (method in cls.methods) {
		if (method.isBridge ||
			method.isSynthetic ||
			method.isVarArgs ||
			Modifier.isStatic(method.modifiers) ||
			method.getAnnotation(java.lang.Deprecated::class.java) != null ||
			isStopClass(method.declaringClass, stopClass))
		  continue

		methods.add(method)
	}

	// use ASM to order methods
	val visitor = AClassVisitor(methods)
	var cls2 = cls
	while (cls2 != java.lang.Object::class.java) {
		val resName = cls2.name.replace('.', '/') + ".class"
		cls2.classLoader.getResourceAsStream(resName).use {
			ClassReader(it).accept(visitor, ClassReader.SKIP_CODE + ClassReader.SKIP_DEBUG)
		}

		cls2 = cls2.superclass
		if (stopClass != null && cls2 == stopClass)
			break
	}
	if (visitor.methods.size > 0)
		throw IllegalStateException()

	val aClass = AClass(cls)
	aClass.methods.addAll(visitor.orderedMethods)
	return aClass
}

private fun isStopClass(cls: Class<*>, stopClass: Class<*>?): Boolean {
	if (stopClass == null)
		return false

	var stopCls = stopClass
	while (stopCls != null) {
		if (stopCls == cls)
			return true
		stopCls = stopCls.superclass
	}
	return false
}

//---- class AClass -----------------------------------------------------------

class AClass(var cls: Class<*>)
{
	val methods = ArrayList<Method>()

	fun getMethod(name: String, vararg parameterTypes: Class<*>): Method? {
		return methods.find { it.name == name && Arrays.equals(it.parameterTypes, parameterTypes) }
	}
}

//---- class AClassVisitor ----------------------------------------------------

private class AClassVisitor(val methods: ArrayList<Method>)
	: ClassVisitor(ASM5)
{
	val orderedMethods = ArrayList<Method>()

	override fun visitMethod(access: Int, name: String, desc: String?,
	                         signature: String?, exceptions: Array<out String>?): MethodVisitor?
	{
		var i = 0
		for (method in methods) {
			if (method.name == name && Type.getType(method).descriptor == desc) {
				orderedMethods.add(method)
				methods.removeAt(i)
				break
			}
			i++
		}
		return null
	}
}
