package com.bschandramohan.learn.kotlinconnect

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

object StringToDate {
    // const can be used only with primitives or strings
    // const val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.systemDefault())
}

fun main() {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.systemDefault())
    val date = Date.from(java.time.ZonedDateTime.parse("2019-01-01T00:00:00Z", dateTimeFormatter).toInstant())

//    val date = Date.from(java.time.ZonedDateTime.parse("2019-01-01T00:00:00Z", dateTimeFormatter).toLocalDateTime().toInstant(
//        ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now())))
    println(date)
}