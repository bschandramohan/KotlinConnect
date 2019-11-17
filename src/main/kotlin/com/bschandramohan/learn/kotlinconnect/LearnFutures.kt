package com.bschandramohan.learn.kotlinconnect

import java.time.LocalTime
import java.util.concurrent.CompletableFuture

class LearnFutures {
    fun getEvenNumbers() : List<Int> {
        log("Computing non prime numbers")
        // Non Prime numbers
        val data = (2..12 step 2).toList()
        log("Computed non prime numbers")
        return data
    }

    fun getPrimeNumbers() : CompletableFuture<List<Int>> {
        return CompletableFuture.supplyAsync {
            log("[${Thread.currentThread().name}] Computing 'Prime' numbers")
            // Long running task to get primes.
            Thread.sleep(1000)
            val primeNumbers = listOf(1, 2, 3, 5, 7, 11)
            log("Computed 'Prime' numbers")

            primeNumbers
        }
    }
}

fun <T> log(data: T) {
    println("${LocalTime.now()} [Thread:${Thread.currentThread().name}] - $data")
}


fun main() {
    log("Starting")
    val learnFutures = LearnFutures()

    val primeNumbers = learnFutures.getPrimeNumbers()
    val evenNumbers = learnFutures.getEvenNumbers()

    log("Combining data")
    val combinedData = mutableListOf<Int>()
    combinedData.addAll(0, evenNumbers)
    log("Combined even numbers")
    combinedData.addAll(combinedData.size, primeNumbers.get())
    log("Combined prime numbers")
    combinedData.sort()

    log(combinedData)
}