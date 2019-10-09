package com.pingfangx.study.tutorial.specialized.reflect.method

import java.lang.reflect.Method

/**
 *
 * @author pingfangx
 * @date 2019/9/28
 */
class MethodTypeTest(method: Method) : BaseMethodTest(method) {
    override fun print() {
        super.print()
        print("returnType", method.returnType.toString())
        print("genericReturnType", method.genericReturnType.toString())

        print("parameterTypes", types(method.parameterTypes))
        print("genericParameterTypes", types(method.genericParameterTypes))
        print("exceptionTypes", types(method.exceptionTypes))
        print("genericExceptionTypes", types(method.genericExceptionTypes))
    }

    private fun types(types: Array<*>?): String {
        return if (types?.isNotEmpty() == true) {
            val sb = StringBuilder()
            types.forEach { sb.append('\n').append(it.toString()) }
            sb.toString()
        } else {
            noneElement()
        }
    }
}