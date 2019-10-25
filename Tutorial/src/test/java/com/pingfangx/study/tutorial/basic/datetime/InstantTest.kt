package com.pingfangx.study.tutorial.basic.datetime

import org.junit.Test
import java.time.Instant

class InstantTest {
    /**
     * 如何中 Instant 中获取毫秒值
     */
    @Test
    fun testInstant() {
        println(System.currentTimeMillis())
        val now = Instant.now()
        println(now.toEpochMilli())
    }
}