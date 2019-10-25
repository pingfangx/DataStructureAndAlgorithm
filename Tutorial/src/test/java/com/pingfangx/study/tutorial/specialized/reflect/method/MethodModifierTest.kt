package com.pingfangx.study.tutorial.specialized.reflect.method

import java.lang.reflect.Method

/**
 *
 * @author pingfangx
 * @date 2019/9/28
 */
class MethodModifierTest(method: Method) : BaseMethodTest(method) {
    override fun print() {
        super.print()
        print("modifiers", modifiersByMethod(method.modifiers))
    }

    override fun simplePrint(sb: StringBuilder) {
        super.simplePrint(sb)
        sb.append("\n\t")
                .append(if (method.isVarArgs) {
                    "varArgs "
                } else {
                    ""
                })
                .append(if (method.isSynthetic) {
                    "synthetic "
                } else {
                    ""
                })
                .append(if (method.isBridge) {
                    "bridge "
                } else {
                    ""
                })
                .append(if (method.isDefault) {
                    "default "
                } else {
                    ""
                })
                .append(if (method.isAccessible) {
                    "accessible "
                } else {
                    ""
                })
    }

    override fun modifiersByMethod(mod: Int): String {
        return super.modifiersByMethod(mod) +
                "\n isSynthetic:${method.isSynthetic}" +
                "\n isVarArgs:${method.isVarArgs}" +
                "\n isBridge:${method.isBridge}" +
                "\n isDefault:${method.isDefault}" +
                "\n isAccessible:${method.isAccessible}"
    }
}