package com.pingfangx.study.tutorial.specialized.reflect.field

import java.lang.reflect.Field

/**
 *
 * @author pingfangx
 * @date 2019/9/28
 */
class FieldTypeTest(filed: Field) : BaseFieldTest(filed) {
    override fun print() {
        super.print()
        print("\ntype", field.type.toString())
        print("\ngenericType", field.genericType.toString())
    }
}