package com.pingfangx.study.tutorial.reflect._class

import com.pingfangx.study.tutorial.reflect.BaseReflectTest
import com.pingfangx.study.tutorial.reflect.constructor.ConstructorTest
import com.pingfangx.study.tutorial.reflect.field.FieldTest
import com.pingfangx.study.tutorial.reflect.method.MethodTest

/**
 *
 * @author pingfangx
 * @date 2019/9/28
 */
class ClassTest(cls: Class<*>) : BaseClassTest(cls) {
    override fun print() {
        super.print()
        val p: Package? = cls.`package`
        print("\nPackage", p?.name ?: "No package")
        //类修饰符和注解
        ClassModifierTest(cls).print()
        //类成员
        ClassMemberTest(cls).print()
    }

    override fun simplePrint(sb: StringBuilder) {
        super.simplePrint(sb)
        sb.append("\n\ntoGenericString")
        sb.append("\n\t" + cls.toGenericString())
        //包
        ClassPackageTest(cls).simplePrint(sb)
        //修饰符
        ClassModifierTest(cls).simplePrint(sb)
        filterAndPrintMember(sb, "字段", cls.fields, cls.declaredFields) { field -> FieldTest(field) }
        filterAndPrintMember(sb, "构造函数", cls.constructors, cls.declaredConstructors) { constructor -> ConstructorTest(constructor) }
        filterAndPrintMember(sb, "方法", cls.methods, cls.declaredMethods) { method -> MethodTest(method) }
        filterAndPrintMember(sb, "内部类", cls.classes, cls.declaredClasses) { clazz -> InnerClassTest(clazz) }
    }

    /**
     * @param inheritedPublicMembers 通过 getXX 获取的
     * @param declaredMembers 通过 getDeclaredXX 获取的
     * 返回结果为数组
     * 仅在 A 中表示是继承父类的
     * 仅在 B 中表示是类自己非公有的
     * AB 中都有表示是类自己公有的
     */
    private fun <T> filterAndPrintMember(sb: StringBuilder, memberType: String, inheritedPublicMembers: Array<T>?, declaredMembers: Array<T>?, action: (T) -> BaseReflectTest) {
        sb.append("\n\n$memberType")
        val elements = filter(inheritedPublicMembers, declaredMembers)
        val inA = elements[0]
        val inB = elements[1]
        val inBoth = elements[2]
        sb.append("\n\t从父类继承的$memberType")
        inA.forEach { action(it).simplePrint(sb) }
        sb.append("\n\t自己公有的$memberType")
        inBoth.forEach { action(it).simplePrint(sb) }
        sb.append("\n\t自己非公有的$memberType")
        inB.forEach { action(it).simplePrint(sb) }
    }

    /**
     * 过滤数组
     * 返回
     * [仅在 A 中的，仅在 B 中的，都在的]
     */
    private fun <T> filter(arrayA: Array<T>?, arrayB: Array<T>?): Array<List<T>> {
        val inA = mutableListOf<T>()
        val inB = mutableListOf<T>()
        val inBoth = mutableListOf<T>()
        if (arrayA != null && arrayB != null) {
            val listA: MutableList<T> = arrayA.toMutableList()
            val listB: MutableList<T> = arrayB.toMutableList()
            val iterator = listA.iterator()
            while (iterator.hasNext()) {
                val next = iterator.next()
                if (listB.contains(next)) {
                    //都有
                    inBoth.add(next)
                    iterator.remove()
                    listB.remove(next)
                } else {
                    //不包含
                    inA.add(next)
                    iterator.remove()
                }
            }
            //结束后 B 中剩作的
            inB.addAll(listB)
        } else if (arrayA != null) {
            inA.addAll(arrayA.toList())
        } else if (arrayB != null) {
            inB.addAll(arrayB.toList())
        }
        return arrayOf(inA, inB, inBoth)
    }


    class Test {
        @org.junit.Test
        fun test() {
            ClassTest(String::class.java).print()
        }

        @org.junit.Test
        fun test_simplePrint() {
            ClassTest(Thread.State::class.java).doSimplePrint()
        }
    }
}