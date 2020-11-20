package com.bschandramohan.learn.kotlinconnect

import kotlin.properties.Delegates

class VetoableObservables {
    var country : String by Delegates.vetoable("US") {
        _, prevValue, newValue ->
        if (newValue in arrayOf("US", "UK", "AU", "CA"))
            return@vetoable true
        println("ERROR! Invalid country=$newValue passed. We will use prevValue=$prevValue")
        return@vetoable false
    }
}

fun main() {
    val vetoableObservables = VetoableObservables()
    println(vetoableObservables.country)
    vetoableObservables.country = "UK"
    println(vetoableObservables.country)
    vetoableObservables.country = "IN"
    println(vetoableObservables.country)

    println("\n")
}
