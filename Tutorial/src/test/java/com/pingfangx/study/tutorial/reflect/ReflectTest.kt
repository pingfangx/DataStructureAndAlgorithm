package com.pingfangx.study.tutorial.reflect

import com.pingfangx.study.tutorial.reflect._class.ClassObjectTest
import com.pingfangx.study.tutorial.reflect._class.ClassTest
import org.junit.Test

/**
 *
 * 反射测试，将反射中所学、所包含的的东西整合起来
 * @author pingfangx
 * @date 2019/9/28
 */
class ReflectTest {
    @Test
    fun test() {
        //获取测试类，涉及获取 Class 对象的方法
        val clsArray = ClassObjectTest.getTestClass()
        for (cls in clsArray) {
            ClassTest(cls).print()
        }
    }

    @Test
    fun test_simplePrint() {
        val cls = String::class.java
        ClassTest(cls).doSimplePrint()
    }
}