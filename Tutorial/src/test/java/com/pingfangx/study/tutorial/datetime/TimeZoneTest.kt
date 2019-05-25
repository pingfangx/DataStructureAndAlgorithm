package com.pingfangx.study.tutorial.datetime

import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

class TimeZoneTest {
    /**
     * 时区不一定是整小时的，输出不是整小时的时区
     */
    @Test
    fun testZone() {
        val availableZoneIds = ZoneId.getAvailableZoneIds()
        val zoneList = ArrayList<String>(availableZoneIds)
        zoneList.sort()

        val dateTime = LocalDateTime.now()
        zoneList.forEach { s ->
            val zone = ZoneId.of(s)
            val zonedDateTime = dateTime.atZone(zone)
            val offset = zonedDateTime.offset
            if (offset.totalSeconds % (3600) != 0) {
                //不是整数小时
                println(String.format("%35s %10s", zone, offset))
            }
        }
    }

    /**
     * 同一时刻不同时区的时间
     */
    @Test
    fun testDifferentZone() {
        val now = ZonedDateTime.now()
        println("当前时间为 $now")
        val newYorkTime = now.withZoneSameInstant(ZoneId.of("America/New_York"))
        println("纽约时间为 $newYorkTime")
    }
}