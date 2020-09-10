package com.bschandramohan.learn.kotlinconnect.coroutines

import kotlinx.coroutines.*
import java.time.LocalDateTime
import kotlin.random.Random

class CoroutinesPlayground {
    fun launchSimplePrint() {
        println("[${printThreadInfo()}] Launching")

        GlobalScope.launch {
            println("[${printThreadInfo()}]In Coroutine")
        }

        println("[${printThreadInfo()}] Finished")
        Thread.sleep(1000)
    }

    fun launchSimpleFunction() {
        println("[${printThreadInfo()}] Launching")

        GlobalScope.launch {
            var sumSquares = 0
            (1..1000).forEach {
                sumSquares += it * it
            }
            println("[${printThreadInfo()}] In Coroutine - Sum squares = $sumSquares")
        }

        println("[${printThreadInfo()}] Finished")
        Thread.sleep(10000)
    }

    fun launchSimpleFunctionWait() {
        runBlocking {
            println("[${printThreadInfo()}] Launching")

            val job = GlobalScope.launch {
                var sumSquares = 0
                (1..1000).forEach {
                    sumSquares += it * it
                }
                println("[${printThreadInfo()}] In Coroutine - Sum squares = $sumSquares")
            }

            println("[${printThreadInfo()}] Finished")
            job.join()
        }
    }

    fun launchSimpleFunctionJoin() {
        runBlocking {
            println("[${printThreadInfo()}] Launching")

            launch {
                var sumSquares = 0
                (1..1000).forEach {
                    sumSquares += it * it
                }
                println("[${printThreadInfo()}] In Coroutine - Sum squares = $sumSquares")
            }

            println("[${printThreadInfo()}] Finished")
        }
    }

    private suspend fun printA() {
        (1..10).forEach {
            delay (100)
            println("[${printThreadInfo()}] A$it")
        }
    }

    private suspend fun printB() {
        (1..10).forEach {
            delay (100)
            println("[${printThreadInfo()}] B$it")
        }
    }

    suspend fun launchSequentialSuspendFun() {
        printA()
        printB()
    }

    private suspend fun printARandomCount() : Int {
        val randomCount = Random(System.currentTimeMillis()).nextInt(1, 10)
        (1..randomCount).forEach {
            delay (100)
            println("[${printThreadInfo()}] A$it")
        }

        return  randomCount
    }

    private suspend fun printBRandomCount() : Int {
        val randomCount = Random(System.currentTimeMillis()+3).nextInt(1, 10)
        (1..randomCount).forEach {
            delay (100)
            println("[${printThreadInfo()}] B$it")
        }

        return randomCount
    }

    fun launchSequentialSuspendFunReturn() {
        runBlocking {
            launch {
                val x = printARandomCount()
                val y = printBRandomCount()
                println("[${printThreadInfo()}] x+y=${x + y}")
            }
        }
    }

    fun launchAsyncSuspendFunReturn() {
        runBlocking {
            val x = async { printARandomCount() }
            val y = async { printBRandomCount() }
            println("[${printThreadInfo()}] x+y=${x.await() + y.await()}")
        }
    }

    suspend fun launchAsyncCoroutineScope() : Int = coroutineScope {
        val x = async { printARandomCount() }
        val y = async { printBRandomCount() }
        x.await() + y.await()
    }


    fun printThreadInfo() = "${LocalDateTime.now()} Thread=${Thread.currentThread().name}"
}

fun main() {
    CoroutinesPlayground().run {
//        launchSimplePrint()
//        launchSimpleFunction()
//        launchSimpleFunctionWait()
//        launchSimpleFunctionJoin()
//        launchSequentialSuspendFun()
//        launchSequentialSuspendFunReturn()
//        launchAsyncSuspendFunReturn()

        runBlocking {
            println("[${printThreadInfo()}] launchAsyncCoroutineScope return=${launchAsyncCoroutineScope()}")
        }
    }
}
