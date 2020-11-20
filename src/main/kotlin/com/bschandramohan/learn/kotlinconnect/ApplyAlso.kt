package com.bschandramohan.learn.kotlinconnect

// to analyze further

data class Data(var a: Int = 2, var b: Int = 3) {
    fun dummyMethod() {
        println("Dummy")
    }
}

fun main() {
    val data = Data()

    showApply(data)
    showAlso(data)

    swapUsingAlso(data)

    var x = 1
    var y = 2
    x = y.also { y = x }

    val z = x * x
    println("$x")
    println("$y")
    println("$z")
}

fun showApply(data: Data) {
    println("Before apply: $data")
    val updatedData = data.apply { b = 4 }
    println("After apply: $updatedData")
}

fun showAlso(data: Data) {
    println("Before also: $data")
    val updatedData = data.also { it.b = 5 }
    println("After also: $updatedData")
}

private fun swapUsingAlso(data: Data) {
    println("Before swap: $data")
    data.a = data.b.also { data.b = data.a }
    println("After swap: $data")
}
