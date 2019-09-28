package com.pingfangx.study.tutorial.reflect._class

/**
 * @author pingfangx
 * @date 2019/9/29
 */
class InnerClassTest(cls: Class<*>) : BaseClassTest(cls) {
    override fun simplePrint(sb: StringBuilder) {
        super.simplePrint(sb)
        sb.append('\n')
                .append(cls.canonicalName)
    }
}