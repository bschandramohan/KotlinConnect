package com.bschandramohan.learn.kotlinconnect.coroutines

import kotlinx.coroutines.*
import java.time.LocalDateTime
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

private fun threadA() {
    println("[${printThreadInfo()}] Starting threadA()")
    (1..10000).forEach {
        thread {
            Thread.sleep(100)
            println("[${printThreadInfo()}] A$it")
        }
    }
}

private fun threadB() {
    println("[${printThreadInfo()}] Starting threadB()")
    (10001..20000).forEach {
        thread {
            Thread.sleep(100)
            println("[${printThreadInfo()}] B$it")
        }
    }
}

fun launchMultipleThreads() {
    println("[${printThreadInfo()}]")
    val executionTime = measureTimeMillis {
        thread {
            threadA()
        }
        thread {
            threadB()
        }
    }
    println("Time taken for launchMultipleThreads=$executionTime")
}

private suspend fun coroutineASimple() = coroutineScope {
    println("[${printThreadInfo()}] Starting coroutineA()")
    (1..100).forEach {
        delay(100)
        println("[${printThreadInfo()}] A$it")
    }
}

private suspend fun coroutineBSimple() = coroutineScope {
    println("[${printThreadInfo()}] Starting coroutineB()")
    (101..200).forEach {
        delay(100)
        println("[${printThreadInfo()}] B$it")
    }
}

private suspend fun coroutineA() = coroutineScope {
    println("[${printThreadInfo()}] [$coroutineContext] Starting coroutineA()")
    (1..1000).forEach {
        launch(Dispatchers.Default) {
            delay(1000)
            println("[${printThreadInfo()}] [$coroutineContext] A$it")
        }
    }
}

private suspend fun coroutineB() = coroutineScope {
    println("[${printThreadInfo()}] [$coroutineContext] Starting coroutineB()")

    (1..1000).forEach {
        launch(Dispatchers.Default) {
            delay(1000)
            println("[${printThreadInfo()}] [$coroutineContext] B$it")
        }
    }
}

private suspend fun launchMultipleCoroutines() = coroutineScope {
    println("[${printThreadInfo()}] [$coroutineContext] Start of launchMultipleCoroutines()")
    launch {
        println("[${printThreadInfo()}] [$coroutineContext] Launching coroutineA")
        coroutineA()
//        coroutineASimple()
    }
    launch {
        println("[${printThreadInfo()}] [$coroutineContext] Launching coroutineB")
        coroutineB()
//        coroutineBSimple()
    }
}

fun printThreadInfo() = "${LocalDateTime.now()} Thread=${Thread.currentThread().name}"

// -Dkotlinx.coroutines.debug
fun main() {
//    launchMultipleThreads()
//    Thread.sleep(6000)

    runBlocking {
        launch {
            println("[${printThreadInfo()}] [$coroutineContext] Launching coroutineA")
            coroutineA()
//        coroutineASimple()
        }
        launch {
            println("[${printThreadInfo()}] Launching coroutineB")
            coroutineB()
//        coroutineBSimple()
        }
    }
}
