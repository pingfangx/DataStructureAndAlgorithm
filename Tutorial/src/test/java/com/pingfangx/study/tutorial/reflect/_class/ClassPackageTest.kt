package com.pingfangx.study.tutorial.reflect._class

/**
 * @author pingfangx
 * @date 2019/9/29
 */
class ClassPackageTest(cls: Class<*>) : BaseClassTest(cls) {
    override fun simplePrint(sb: StringBuilder) {
        super.simplePrint(sb)
        sb.append("\npackage")
        val p: Package? = cls.`package`
        if (p == null) {
            sb.append("\n\t<Default>")
        } else {
            sb.append("\n\tpackage ${p.name};")
        }
    }

    class Test {
        @org.junit.Test
        fun test_simplePrint() {
            ClassPackageTest(String::class.java).doSimplePrint()
            //默认包不能 import 只能 forName 获取
            ClassPackageTest(Class.forName("NoPackageTest")).doSimplePrint()
        }
    }
}