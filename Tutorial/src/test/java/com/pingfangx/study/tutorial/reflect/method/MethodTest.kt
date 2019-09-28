package com.pingfangx.study.tutorial.reflect.method

import java.lang.reflect.Method

/**
 *
 * @author pingfangx
 * @date 2019/9/28
 */
class MethodTest(method: Method) : BaseMethodTest(method) {
    override fun print() {
        super.print()
        //方法类型
        MethodTypeTest(method).print()
        //方法参数
        MethodParameterTest(method).print()
        //方法修饰符
        MethodModifierTest(method).print()
    }

    override fun simplePrint(sb: StringBuilder) {
        super.simplePrint(sb)
        sb.append('\n')
                .append(modifiersByMethod(method.modifiers))
                .append(' ')
                .append(method.genericReturnType.typeName)
                .append(' ')
                .append(method.name)
        addParametersInfo(sb, method.parameters)
        if (method.exceptionTypes?.isNotEmpty() == true) {
            sb.append(' ')
                    .append("throws")
                    .append(' ')
                    .append(method.exceptionTypes.joinToString(", ", transform = { clazz -> clazz.canonicalName }))
        }
        MethodModifierTest(method).simplePrint(sb)
    }

    companion object {
        fun printMethods(cls: Class<*>) {
            println("\nmethods")
            printMethods(cls.methods)
            println("\ndeclaredMethods")
            printMethods(cls.declaredMethods)
        }

        private fun printMethods(methods: Array<Method>?) {
            if (methods?.isNotEmpty() == true) {
                methods.forEach { MethodTest(it).print() }
            } else {
                println("\nNone")
            }
        }
    }

    class Test {
        @org.junit.Test
        fun test() {
            printMethods(String::class.java)
            printMethods(Class::class.java)
        }
    }
}