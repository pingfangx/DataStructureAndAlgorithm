package com.pingfangx.study.tutorial.reflect.method

import com.pingfangx.study.tutorial.reflect.BaseReflectTest
import java.lang.reflect.Method

/**
 *
 * @author pingfangx
 * @date 2019/9/28
 */
open class BaseMethodTest(val method: Method) : BaseReflectTest() {
    override fun print() {
        super.print()
        print("\n[method ${javaClass.simpleName}]", method.toGenericString())
    }
}