package com.bschandramohan.learn.kotlinconnect

class Test {
    val data: String? = null
    val data1: String = "TEST"
}

class Encompass {
    val test: Test? = null
}

fun main() {
    println(Encompass().test?.data ?: "EMPTY")
    println(Encompass().test?.data1 ?: "EMPTY")

    // Expected output:
//    EMPTY
//    EMPTY
}
