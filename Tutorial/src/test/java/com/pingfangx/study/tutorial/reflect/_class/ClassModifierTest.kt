@file:Suppress("DEPRECATION")

//仅测试

package com.pingfangx.study.tutorial.reflect._class

import org.junit.Assert
import java.io.InterruptedIOException
import java.lang.reflect.Modifier
import java.security.Identity
import java.util.concurrent.ConcurrentNavigableMap

/**
 *
 * @author pingfangx
 * @date 2019/9/28
 */
class ClassModifierTest(cls: Class<*>) : BaseClassTest(cls) {
    override fun print() {
        super.print()
        //各种名字
        print("name", cls.name)
        print("simpleName", cls.simpleName)
        print("canonicalName", cls.canonicalName)
        print("typeName", cls.typeName)

        //修饰符
        print("\nmodifiers", Modifier.toString(cls.modifiers))
        print("modifiersByMethod", modifiersByMethod(cls.modifiers))

        //泛型类型参数
        print("\nType Parameters", cls.typeParameters.arrayToString())
        //实现的接口
        print("\nImplemented Interface", cls.interfaces.arrayToString())
        //继承路径
        print("\nInheritance Path", inheritanceClass(cls).arrayToString())
        //注解
        print("\nAnnotations", cls.annotations.arrayToString())
    }

    override fun simplePrint(sb: StringBuilder) {
        super.simplePrint(sb)
        sb.append("\n类声明\n\t")
                .append(cls.annotations.joinToString(", "))//注解
                .append(modifiersByMethod(cls.modifiers))//修饰符
                .append(' ')
                .append(if (cls.isInterface) {//类或接口
                    "interface "
                } else {
                    "class "
                })
                .append(cls.name)//名字
        //泛型类型参数
        if (cls.typeParameters?.isNotEmpty() == true) {
            sb.append('<')
            sb.append(cls.typeParameters.joinToString(", "))
            sb.append('>')
        }
        sb.append("\nextends\n\t")
                .append(inheritanceClass(cls, true).joinToString(" -> ", transform = { clazz -> clazz.name }))
                .append("\nimplements\n\t")
                .append(cls.interfaces.joinToString(", ", transform = { clazz -> clazz.name }))
    }

    private fun inheritanceClass(cls: Class<*>, includeSelf: Boolean = false): Array<Class<*>> {
        val res = mutableListOf<Class<*>>()
        if (includeSelf) {
            res.add(cls)
        }
        var c = cls.superclass
        while (c != null) {
            res.add(c)
            c = c.superclass
        }
        return res.toTypedArray()
    }


    class Test {
        @org.junit.Test
        fun test() {
            ClassModifierTest(ConcurrentNavigableMap::class.java).print()
            val stringArrayClass = Array<String>::class.java
            ClassModifierTest(stringArrayClass).print()
            ClassModifierTest(InterruptedIOException::class.java).print()
            ClassModifierTest(Identity::class.java).print()
            //抽象是由于数组是运行时对象，因此所有类型信息都由 Java 虚拟机定义。
            Assert.assertEquals("public abstract final", ClassModifierTest(stringArrayClass).modifiersByMethod(stringArrayClass.modifiers))
        }

        @org.junit.Test
        fun test_simplePrint() {
            ClassModifierTest(String::class.java).doSimplePrint()
            ClassModifierTest(Integer::class.java).doSimplePrint()
            ClassModifierTest(ConcurrentNavigableMap::class.java).doSimplePrint()
            ClassModifierTest(Identity::class.java).doSimplePrint()
        }
    }
}
