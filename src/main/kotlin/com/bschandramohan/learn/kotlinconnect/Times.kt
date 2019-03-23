package com.bschandramohan.learn.kotlinconnect
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

fun convertJodaLocalDateTimeToJavaTimeLocalDateTime() {
    val dateTime = org.joda.time.LocalDateTime()
    val javaTimeLocalDateTim = LocalDateTime.of(dateTime.year, dateTime.monthOfYear, dateTime.dayOfMonth,
        dateTime.hourOfDay, dateTime.minuteOfHour, dateTime.secondOfMinute)

    val zdt = ZonedDateTime.of(javaTimeLocalDateTim, ZoneId.systemDefault())
    //val zdt = ZonedDateTime.of(javaTimeLocalDateTim, ZoneId.of("UTC"))
    val utcZDT = zdt.withZoneSameInstant(ZoneOffset.UTC)
    println("dateTime=$dateTime javaTimeLocalDateTim=$javaTimeLocalDateTim zdt=$zdt utcZDT=$utcZDT")
}

fun main() {
    convertJodaLocalDateTimeToJavaTimeLocalDateTime()
}
