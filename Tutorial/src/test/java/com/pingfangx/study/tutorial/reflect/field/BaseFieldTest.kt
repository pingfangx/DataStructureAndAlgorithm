package com.pingfangx.study.tutorial.reflect.field

import com.pingfangx.study.tutorial.reflect.BaseReflectTest
import java.lang.reflect.Field

/**
 *
 * @author pingfangx
 * @date 2019/9/28
 */
open class BaseFieldTest(val field: Field) : BaseReflectTest() {
    override fun print() {
        print("\n[field ${javaClass.simpleName}]", field.toGenericString())
    }
}