package com.pingfangx.study.tutorial.specialized.reflect._class

import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Member
import java.lang.reflect.Method
import java.nio.channels.ReadableByteChannel

/**
 * 反射中的 Member 包含构造函数，但成员的定义不包含
 * @author pingfangx
 * @date 2019/9/28
 */
class ClassMemberTest(cls: Class<*>) : BaseClassTest(cls) {
    override fun print() {
        super.print()
        print("\nfields", members(cls.fields))
        print("\ndeclaredFields", members(cls.declaredFields))
        print("\nconstructors", members(cls.constructors))
        print("\ndeclaredConstructors", members(cls.declaredConstructors))
        print("\nmethods", members(cls.methods))
        print("\ndeclaredMethods", members(cls.declaredMethods))
        print("\nclasses", classes(cls.classes))
        print("\ndeclaredClasses", classes(cls.declaredClasses))
    }

    private fun members(members: Array<out Member>?): String {
        return if (members?.isNotEmpty() == true) {
            val sb = java.lang.StringBuilder()
            members.forEach {
                sb.append('\n').append(
                        when (it) {
                            is Field -> it.toGenericString()
                            is Constructor<*> -> it.toGenericString()
                            is Method -> it.toGenericString()
                            else -> ""
                        })
            }
            sb.toString()
        } else {
            noneElement()
        }
    }

    private fun classes(classes: Array<Class<*>>?): String {
        return if (classes?.isNotEmpty() == true) {
            val sb = StringBuilder()
            classes.forEach { sb.append('\n').append(it.toGenericString()) }
            sb.toString()
        } else {
            noneElement()
        }
    }

    class Test {
        @org.junit.Test
        fun test() {
            ClassMemberTest(ClassCastException::class.java).print()
            ClassMemberTest(ReadableByteChannel::class.java).print()
            ClassMemberTest(Thread.State::class.java).print()
        }
    }
}