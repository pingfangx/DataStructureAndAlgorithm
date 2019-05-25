package com.pingfangx.study.tutorial.datetime

import org.junit.Assert
import org.junit.Test
import java.time.DayOfWeek
import java.time.Month
import java.time.format.TextStyle
import java.util.*

class EnumTest {
    /**
     * 测试枚举
     */
    @Test
    fun testEnum() {
        //相关方法也定义于 enum 中
        Assert.assertEquals(DayOfWeek.THURSDAY, DayOfWeek.MONDAY.plus(3))
        //不是星期天
        Assert.assertEquals("星期日", DayOfWeek.SUNDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        Assert.assertEquals("日", DayOfWeek.SUNDAY.getDisplayName(TextStyle.NARROW, Locale.getDefault()))
        Assert.assertEquals("星期日", DayOfWeek.SUNDAY.getDisplayName(TextStyle.SHORT, Locale.getDefault()))

        Assert.assertEquals("八月", Month.AUGUST.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        //如果未定义特定的 TextStyle，则返回表示常量的数值的字符串。
        Assert.assertEquals("8", Month.AUGUST.getDisplayName(TextStyle.NARROW, Locale.getDefault()))
        Assert.assertEquals("八月", Month.AUGUST.getDisplayName(TextStyle.SHORT, Locale.getDefault()))
    }
}