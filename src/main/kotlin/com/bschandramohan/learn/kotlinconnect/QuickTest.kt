package com.bschandramohan.learn.kotlinconnect

import java.math.BigDecimal
import java.math.RoundingMode

fun sortAndPrintTest() {
    listOf("1000#123", "1001#125", "2001#15", "2012#45", "10000#125").sorted().forEach { println(it) }
    println()
    listOf("1234566#1000#123", "1234566#1001#125", "1234566#2001#15", "1234566#2012#45", "1234566#10000#125").sorted().forEach { println(it) }
}

fun percentageCalculatorBigDecimal() {
    var soldUnits = BigDecimal(1120.11122)
    var totalUnits = BigDecimal(2225.1234)

    println(soldUnits.multiply(BigDecimal(100)).divide(totalUnits, 2, RoundingMode.HALF_UP).toDouble())
}


fun percentageCalculatorLong() {
    var soldUnits = 100L
    var totalUnits = 125

    var businessTripsPercentage = soldUnits * 100.0 / totalUnits
    println(Math.round(businessTripsPercentage * 100.0) / 100.0) // Scale 2 rounding of double
}

fun main() {
//    sortAndPrintTest()
//    percentageCalculatorLong()
    percentageCalculatorBigDecimal()
}
