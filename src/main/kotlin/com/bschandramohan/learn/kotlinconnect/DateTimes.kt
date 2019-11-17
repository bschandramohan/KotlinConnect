package com.bschandramohan.learn.kotlinconnect

import org.joda.time.DateTimeZone

class DateTimes {
    fun printDates(jodaDateTime : org.joda.time.DateTime,
                   jodaLocalDateTime : org.joda.time.LocalDateTime,
                   javaDateTime: java.time.ZonedDateTime,
                   javaLocalDateTime: java.time.LocalDateTime
    ) {
        println(jodaDateTime)
        println(jodaLocalDateTime)
        println(javaDateTime)
        println(javaLocalDateTime)
    }
}


fun main() {
    val dateStringWithTimezoneInfo = "2019-07-23T15:56:23.998-04:00"
    val dateStringWithoutTimezoneInfo = "2019-07-23T15:56:23.998"

    println("\nWith Timezone")
    var jodaDateTime = org.joda.time.DateTime.parse(dateStringWithTimezoneInfo)
    println("Joda.DateTime $jodaDateTime ; Was stored with zone ${jodaDateTime.zone}")
    val jodaDateTimeC = org.joda.time.DateTime(dateStringWithTimezoneInfo)
    println("Joda.DateTime $jodaDateTimeC ; Was stored with zone ${jodaDateTimeC.zone}")
//    println(org.joda.time.LocalDateTime.parse(dateStringWithTimezoneInfo)) // Throws Exception : Exception in thread "main" java.lang.IllegalArgumentException: Invalid format: "2019-07-23T15:56-07:00" is malformed at "-07:00"


    val jodaDateTime1 = jodaDateTime.withZone(DateTimeZone.forID("America/Denver"))
    println("Joda.DateTime1 $jodaDateTime1 ; Was stored with zone ${jodaDateTime1.zone}")
    val jodaDateTime2 = jodaDateTime.withZoneRetainFields(DateTimeZone.forID("America/Denver"))
    println("Joda.DateTime2 $jodaDateTime2 ; Was stored with zone ${jodaDateTime2.zone}")


    val zonedDateTime = java.time.ZonedDateTime.parse(dateStringWithTimezoneInfo)
    println("ZonedDateTime $zonedDateTime ; Was stored with zone ${zonedDateTime.zone}")
    val localDateTime = zonedDateTime.toLocalDateTime()
    println("LocalDateTime converted from ZonedDateTime: $localDateTime")
//    println(java.time.LocalDateTime.parse(dateStringWithTimezoneInfo)) // Throws Exception : Exception in thread "main" java.time.format.DateTimeParseException: Text '2019-07-23T15:56-07:00' could not be parsed, unparsed text found at index 16

    println("\n\nWithout Timezone")
    jodaDateTime = org.joda.time.DateTime.parse(dateStringWithoutTimezoneInfo)
    println("Joda.DateTime $jodaDateTime ; Was stored with zone ${jodaDateTime.zone}")
    jodaDateTime = org.joda.time.DateTime(dateStringWithoutTimezoneInfo)
    println("$jodaDateTime ; Was stored with zone ${jodaDateTime.zone}")
    println(org.joda.time.LocalDateTime.parse(dateStringWithoutTimezoneInfo))
//    println(java.time.ZonedDateTime.parse(dateStringWithoutTimezoneInfo)) // Requires Timezone: Exception in thread "main" java.time.format.DateTimeParseException: Text '2019-07-23T15:56:23.998' could not be parsed at index 23
    println("ZonedDateTime ${java.time.LocalDateTime.parse(dateStringWithoutTimezoneInfo)}")
}
