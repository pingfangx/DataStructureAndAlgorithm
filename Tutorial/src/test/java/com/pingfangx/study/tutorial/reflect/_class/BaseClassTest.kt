package com.pingfangx.study.tutorial.reflect._class

import com.pingfangx.study.tutorial.reflect.BaseReflectTest

/**
 *
 * @author pingfangx
 * @date 2019/9/28
 */
open class BaseClassTest(val cls: Class<*>) : BaseReflectTest() {
    override fun print() {
        print("\n[class ${javaClass.simpleName}]", cls.toGenericString())
    }
}