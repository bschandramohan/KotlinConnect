package com.bschandramohan.learn.kotlinconnect

import java.util.*

class InfixDemo {
    var data = 10L
    infix fun printValue(description: String) = println("$description $data")

    infix fun toMiles(kms: Int) = kms / 1.6
    infix fun toKms(miles: Int) = miles * 1.6
}

data class Trips(val id: UUID, val distance: Int) {
    infix fun convert1(distanceType: String) :  Double {
        return 0.0
    }
}

fun Trips.convert(distanceType: String): Double =
       if (distanceType == "MILES")
            this.distance / 1.6
        else
            this.distance * 1.6

infix fun Trips.to(distanceType: String): Double =
    if (distanceType == "MILES")
        this.distance / 1.6
    else
        this.distance * 1.6

fun main() {
    val demo = InfixDemo()
    demo.printValue("Shweta")
    demo printValue "AwesomeValue"

    println(demo toMiles 25)
    println(demo toKms 25)

    val trip = Trips(UUID.randomUUID(), 25)
    println(trip.convert("MILES"))

    println("WOW!")
    println(trip to "MILES")
    println (trip convert1 "asdf")
}
