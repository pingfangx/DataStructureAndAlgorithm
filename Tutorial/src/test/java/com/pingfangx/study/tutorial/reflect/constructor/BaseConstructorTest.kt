package com.pingfangx.study.tutorial.reflect.constructor

import com.pingfangx.study.tutorial.reflect.BaseReflectTest
import java.lang.reflect.Constructor

/**
 * @author pingfangx
 * @date 2019/9/29
 */
open class BaseConstructorTest(val constructor: Constructor<*>) : BaseReflectTest() {
    override fun print() {
        super.print()
        print("\n[method ${javaClass.simpleName}]", constructor.toGenericString())
    }
}