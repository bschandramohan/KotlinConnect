package com.bschandramohan.learn.kotlinconnect

import kotlin.properties.Delegates

data class PropertyObservables(var country : String = "US") {
    var distance: Int by Delegates.observable(0) {
        _, _, newValue ->
        deduction = newValue * 0.557
    }

    var deduction: Double by Delegates.observable(0.0) {
            _, oldValue, newValue ->
        println("$oldValue changed to $newValue distance=$distance")
    }
}

fun main() {
    val propertyObservables = PropertyObservables()
    println(propertyObservables)
    propertyObservables.distance = 5
    println(propertyObservables.deduction)

    println("\n")

    val trip = Trip(100.0)
    println("$trip, ${trip.distance}")
    trip.distance = 10
    println("$trip, ${trip.distance}")
}

data class Trip(var deduction: Double) {
    var distance: Int by Delegates.observable(0) {
            _, _, newValue ->
        deduction = newValue * 0.557
    }
}
