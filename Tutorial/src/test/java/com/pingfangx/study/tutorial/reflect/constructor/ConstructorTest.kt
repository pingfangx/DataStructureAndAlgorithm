package com.pingfangx.study.tutorial.reflect.constructor

import java.lang.reflect.Constructor

/**
 * @author pingfangx
 * @date 2019/9/29
 */
class ConstructorTest(constructor: Constructor<*>) : BaseConstructorTest(constructor) {
    override fun print() {
        super.print()
        print("\nparameterTypes", constructor.parameterTypes.arrayToString())
        print("\nmodifiers", modifiersByMethod(constructor.modifiers))
    }

    override fun simplePrint(sb: StringBuilder) {
        super.simplePrint(sb)
        sb.append('\n')
                .append(modifiersByMethod(constructor.modifiers))
                .append(' ')
                .append(constructor.name)
        addParametersInfo(sb, constructor.parameters)
        ConstructorModiferTest(constructor).simplePrint(sb)
    }

    companion object {
        fun printElementsOfClass(cls: Class<*>) {
            println("\nconstructors")
            printElements(cls.constructors)
            println("\ndeclaredConstructors")
            printElements(cls.declaredConstructors)
        }

        private fun printElements(methods: Array<Constructor<*>>?) {
            if (methods?.isNotEmpty() == true) {
                methods.forEach { ConstructorTest(it).print() }
            } else {
                println("\nNone")
            }
        }
    }

    class Test {
        @org.junit.Test
        fun test() {
            printElementsOfClass(String::class.java)
        }
    }
}