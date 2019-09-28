package com.pingfangx.study.tutorial.reflect.method

import java.lang.reflect.Method

/**
 *
 * @author pingfangx
 * @date 2019/9/28
 */
class MethodParameterTest(method: Method) : BaseMethodTest(method) {
    override fun print() {
        super.print()
        print("parameters", method.parameters.arrayToString(StringBuilder()) { sb, it ->
            sb.append("\n param type:")
                    .append(it.type)
                    .append("\n param name:")
                    .append(it.name)
                    .append("\n modifier:")
                    .append(modifiersByMethod(it.modifiers))
                    .append("\n isImplicit:")
                    .append(it.isImplicit)
                    .append("\n isNamePresent:")
                    .append(it.isNamePresent)
                    .append("\n isSynthetic:")
                    .append(it.isSynthetic)
                    .append("\n isVarArgs:")
                    .append(it.isVarArgs)
        })
    }
}
