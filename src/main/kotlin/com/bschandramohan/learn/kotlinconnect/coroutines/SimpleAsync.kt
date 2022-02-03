package com.bschandramohan.learn.kotlinconnect.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        runMethodsAsync()
    }
}

suspend fun runMethodsAsync() = coroutineScope {
    val sum = async {
        performAdds()
    }

    val product = async {
        performMultiplications()
    }

    sum.await()
    product.await()
}

suspend fun performAdds() {
    println("Performing addition")
    var sum = 0
    (1..1000).forEach {
        sum += it
        if (it % 100 == 0) println("$it elements added; So far sum=$sum")
        delay(1)
    }
    println("Sum is = $sum")
}

suspend fun performMultiplications() {
    println("Performing multiplication")
    var product = 1L
    (1..100).forEach {
        product *= it
        if (it % 10 == 0) println("$it elements multiplied; So far product=$product")
        delay(10)
    }
    println("product is = $product")
}
