package com.pingfangx.study.tutorial.specialized.reflect.field

import java.lang.reflect.Field
import java.lang.reflect.Modifier

/**
 *
 * @author pingfangx
 * @date 2019/9/28
 */
class FieldModifierTest(filed: Field) : BaseFieldTest(filed) {
    override fun print() {
        super.print()
        print("\nmodifier", Modifier.toString(field.modifiers))
        print("\nmodifiersByMethod", modifiersByMethod(field.modifiers))
    }

    override fun simplePrint(sb: StringBuilder) {
        super.simplePrint(sb)
        sb.append("\n\t")
                .append(if (field.isAccessible) {
                    "accessible "
                } else {
                    ""
                })
                .append(if (field.isSynthetic) {
                    "synthetic "
                } else {
                    ""
                })
                .append(if (field.isEnumConstant) {
                    "enumConstant "
                } else {
                    ""
                })
    }

    override fun modifiersByMethod(mod: Int): String {
        return super.modifiersByMethod(mod) + ",synthetic=${field.isSynthetic},enumConstant=${field.isEnumConstant}"
    }
}