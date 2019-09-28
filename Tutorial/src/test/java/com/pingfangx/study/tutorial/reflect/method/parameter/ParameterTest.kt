package com.pingfangx.study.tutorial.reflect.method.parameter

import com.pingfangx.study.tutorial.reflect.BaseReflectTest
import java.lang.reflect.Parameter

/**
 * @author pingfangx
 * @date 2019/9/29
 */
class ParameterTest(val parameter: Parameter) : BaseReflectTest() {
    override fun simplePrint(sb: StringBuilder) {
        super.simplePrint(sb)
        if (parameter.isImplicit) {
            sb.append("implicit ")
        }
        if (parameter.isSynthetic) {
            sb.append("synthetic ")
        }
        if (parameter.isVarArgs) {
            sb.append("varArgs ")
                    .append(parameter.type.canonicalName.replace("[]", ""))
                    .append(' ')
                    .append(parameter.name)
                    .append("...")
        } else {
            sb.append(parameter.type.canonicalName)
                    .append(' ')
                    .append(parameter.name)
        }
    }
}