package com.bschandramohan.learn.kotlinconnect


data class Vehicle(var id: String)

fun main(args: Array<String>) {
    var vehicleList = listOf(Vehicle("123"))
// ERROR    vehicleList.add(Vehicle("234"))
    val mutableList =  vehicleList.toMutableList()
    mutableList.add(Vehicle("234"))
    vehicleList = mutableList.toList()

    vehicleList.forEach {
        println(it)
    }
}
