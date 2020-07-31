package com.bschandramohan.learn.kotlinconnect

fun main() {
    val rangeList = listOf(1..3, 4..6, 8..10)
    val completeList = 1..10
    println(completeList.filter { it !in rangeList.flatten() })

    println(completeList.minus(rangeList.flatten()))
}