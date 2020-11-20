package com.bschandramohan.learn.kotlinconnect.coroutines

import kotlinx.coroutines.*
import kotlin.random.Random

class CoroutinesDivideAndConquer

suspend fun performOperation(data: List<String>) {
    println("Begin DataSet")
    data.forEach {
        // Perform some time consuming operation
        delay(1)
        println(it)
    }
    println("Completed DataSet")
}

fun main() = runBlocking {
    val largeArrayList = createDataSet()
    val groups = largeArrayList.chunked(25)

    val deferredResults = mutableListOf<Deferred<Unit>>()
    groups.forEach {
        println("Perform async operation")
        deferredResults.add( async { performOperation(it) } )
    }

    deferredResults.awaitAll()
    println("Completed operation")
}

private fun createDataSet(): MutableList<String> {
    val largeArrayList = mutableListOf<String>()
    (0..99).forEach {
        val prefix = when {
                                it < 25 -> "A"
                                it < 50 -> "B"
                                it < 75 -> "C"
                                else -> "D"
                            }
        val suffix = Random(System.currentTimeMillis() + it*it).nextInt(10, 10000)

        largeArrayList.add(prefix + suffix.toString())
    }
    return largeArrayList
}
