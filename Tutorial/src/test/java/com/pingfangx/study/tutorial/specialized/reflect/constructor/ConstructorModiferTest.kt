package com.pingfangx.study.tutorial.specialized.reflect.constructor

import java.lang.reflect.Constructor

/**
 * @author pingfangx
 * @date 2019/9/29
 */
class ConstructorModiferTest(constructor: Constructor<*>) : BaseConstructorTest(constructor) {
    override fun simplePrint(sb: StringBuilder) {
        super.simplePrint(sb)
        sb.append("\n\t")
                .append(if (constructor.isVarArgs) {
                    "varArgs "
                } else {
                    ""
                })
                .append(if (constructor.isSynthetic) {
                    "synthetic "
                } else {
                    ""
                })
                .append(if (constructor.isAccessible) {
                    "accessible "
                } else {
                    ""
                })
    }
}