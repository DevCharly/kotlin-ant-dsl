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
	val aClass = AClass(cls)

	// use reflection to get methods that have deprecated annotation
	for (method in cls.methods) {
		if (method.getAnnotation(java.lang.Deprecated::class.java) != null) {
			aClass.deprecatedMethods.add(method)
			continue
		}
	}

	// use ASM to order methods and remove deprecated methods
	val orderedMethods = ArrayList<Method>()
	var addAtIndex = 0
	var insertBefore = true
	var lastNonAbstractClass: Class<*>? = cls
	var cls2: Class<*>? = cls
	while (cls2 != null && cls2 != java.lang.Object::class.java) {
		if (!Modifier.isAbstract(cls2.modifiers))
			lastNonAbstractClass = cls2
		cls2 = cls2.superclass
	}

	cls2 = cls
	while (cls2 != null && cls2 != java.lang.Object::class.java) {
		val visitor = AClassVisitor(cls, aClass)
		val resName = cls2.name.replace('.', '/') + ".class"
		cls.classLoader.getResourceAsStream(resName).use {
			ClassReader(it).accept(visitor, ClassReader.SKIP_CODE + ClassReader.SKIP_DEBUG)
		}

		orderedMethods.addAll(addAtIndex, visitor.orderedMethods)

		if (cls2 == lastNonAbstractClass)
			insertBefore = false
		if (!insertBefore)
			addAtIndex += visitor.orderedMethods.size

		cls2 = cls2.superclass
	}

	orderedMethods.forEachIndexed { i, method ->
		if (!aClass.orderedMethods.contains(method))
			aClass.orderedMethods.put(method, i)
	}

	return aClass
}

//---- class AClass -----------------------------------------------------------

class AClass(var cls: Class<*>)
{
	val orderedMethods = HashMap<Method, Int>()
	val deprecatedMethods = HashSet<Method>()
}

//---- class AClassVisitor ----------------------------------------------------

private class AClassVisitor(val cls: Class<*>, val aClass: AClass)
	: ClassVisitor(ASM5)
{
	val orderedMethods = ArrayList<Method>()

	override fun visitMethod(access: Int, name: String, desc: String?,
	                         signature: String?, exceptions: Array<out String>?): MethodVisitor?
	{
		if (access and ACC_PUBLIC == 0 || name == "<init>")
			return null

		val parameterTypes = Type.getArgumentTypes(desc).map {
			when (it.sort) {
				Type.BOOLEAN -> java.lang.Boolean.TYPE
				Type.BYTE -> java.lang.Byte.TYPE
				Type.SHORT -> java.lang.Short.TYPE
				Type.INT -> java.lang.Integer.TYPE
				Type.LONG -> java.lang.Long.TYPE
				Type.FLOAT -> java.lang.Float.TYPE
				Type.DOUBLE -> java.lang.Double.TYPE
				Type.CHAR -> java.lang.Character.TYPE
				Type.ARRAY -> Class.forName(it.getDescriptor().replace('/', '.'))
				else -> Class.forName(it.className)
			}
		}.toTypedArray()

		val method = cls.getMethod(name, *parameterTypes)

		if (access and ACC_DEPRECATED == 0)
			orderedMethods.add(method)
		else
			aClass.deprecatedMethods.add(method)

		return null
	}
}
