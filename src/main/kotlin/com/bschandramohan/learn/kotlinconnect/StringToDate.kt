package com.bschandramohan.learn.kotlinconnect

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

object StringToDate {
    // const can be used only with primitives or strings
    // const val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.systemDefault())
}

fun main() {
    // printDate()
    dateToString()
}

private fun printDate() {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.systemDefault())
    val date = Date.from(ZonedDateTime.parse("2019-01-01T00:00:00Z", dateTimeFormatter).toInstant())

//    val date = Date.from(java.time.ZonedDateTime.parse("2019-01-01T00:00:00Z", dateTimeFormatter).toLocalDateTime().toInstant(
//        ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now())))
    println(date)
}

private fun dateToString() {
    val startLocalDate = LocalDate.of(2020, 1, 1)
    val dateField = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).atZone(ZoneId.of("UTC"))
        println(dateField.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")))
    //println(Date.from(startLocalDate.atStartOfDay().toInstant(ZoneOffset.UTC)))
        // .atZone(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_INSTANT)
        //.format(DateTimeFormatter.ISO_INSTANT)
}
