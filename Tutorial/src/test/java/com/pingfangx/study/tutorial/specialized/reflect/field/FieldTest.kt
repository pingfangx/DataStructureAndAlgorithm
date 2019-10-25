package com.pingfangx.study.tutorial.specialized.reflect.field

import java.lang.reflect.Field

/**
 *
 * @author pingfangx
 * @date 2019/9/28
 */
class FieldTest(filed: Field) : BaseFieldTest(filed) {
    override fun print() {
        super.print()
        //类型
        FieldTypeTest(field).print()
        //修饰符
        FieldModifierTest(field).print()
    }

    override fun simplePrint(sb: StringBuilder) {
        super.simplePrint(sb)
        sb.append('\n')
                .append(modifiersByMethod(field.modifiers))
                .append(' ')
                .append(if (field.isEnumConstant) {
                    "enum "
                } else {
                })
                .append(field.genericType.typeName)
                .append(' ')
                .append(field.name)
                .append(';')
        FieldModifierTest(field).simplePrint(sb)
    }

    companion object {
        fun printFields(cls: Class<*>) {

            println("\nfields")
            printFields(cls.fields)
            println("\ndeclaredFields")
            printFields(cls.declaredFields)
        }

        private fun printFields(fields: Array<Field>?) {
            if (fields?.isNotEmpty() == true) {
                fields.forEach { FieldTest(it).print() }
            } else {
                println("\nNone")
            }
        }
    }

    class Test {
        @org.junit.Test
        fun test() {
            printFields(String::class.java)
            printFields(Thread.State::class.java)
        }
    }
}