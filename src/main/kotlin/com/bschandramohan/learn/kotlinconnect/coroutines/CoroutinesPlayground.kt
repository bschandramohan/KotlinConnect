package com.bschandramohan.learn.kotlinconnect.coroutines

import jdk.nashorn.internal.objects.Global
import kotlinx.coroutines.*
import java.time.LocalDateTime
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class CoroutinesPlayground {
    fun launchSimplePrint() {
        val executionTime = measureTimeMillis {
            println("[${printThreadInfo()}] Launching")

            GlobalScope.launch {
                println("[${printThreadInfo()}]In Coroutine")
            }

            println("[${printThreadInfo()}] Finished")
            Thread.sleep(100)
        }
        // Note: The first GlobalScope.launch takes time in a program execution
        println("Time taken for launchSimplePrint=$executionTime")
    }

    fun launchSimpleFunction() {
        val executionTime = measureTimeMillis {
            println("[${printThreadInfo()}] Launching")

            GlobalScope.launch {
                var sumSquares = 0L
                (1..10000).forEach {
                    sumSquares += it * it
                }
                println("[${printThreadInfo()}] In Coroutine launchSimpleFunction - Sum squares = $sumSquares")
            }

            println("[${printThreadInfo()}] Finished")
            Thread.sleep(100)
        }
        println("Time taken for launchSimpleFunction=$executionTime")
    }

    fun launchSimpleFunctionJoin() {
        val executionTime = measureTimeMillis {
            runBlocking {
                println("[${printThreadInfo()}] Launching")

                val job = GlobalScope.launch {
                    var sumSquares = 0
                    (1..1000).forEach {
                        sumSquares += it * it
                    }
                    println("[${printThreadInfo()}] In Coroutine launchSimpleFunctionWait - Sum squares = $sumSquares")
                }

                println("[${printThreadInfo()}] Finished")
                job.join()
            }
        }
        println("Time taken for launchSimpleFunctionWait=$executionTime")
    }

    fun launchSimpleFunctionWaitWithDelay() {
        val executionTime = measureTimeMillis {
            runBlocking {
                println("[${printThreadInfo()}] Launching")

                launch {
                    var sumSquares = 0L
                    (1..1000000).forEach {
                        sumSquares += it * it
                    }
                    delay(500)
                    println("[${printThreadInfo()}] In Coroutine launchSimpleFunctionJoin - Sum squares = $sumSquares")
                }

                println("[${printThreadInfo()}] Finished")
            }
        }
        println("Time taken for launchSimpleFunctionJoin=$executionTime")
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
        val executionTime = measureTimeMillis {
            printA()
            printB()
        }
        println("Time taken for launchSequentialSuspendFun=$executionTime")
    }

    fun launchSequentialSuspendFunInGlobalLaunch() {
        GlobalScope.launch {
            val executionTime = measureTimeMillis {
                printA()
                printB()
            }
            println("Time taken for launchSequentialSuspendFunInGlobalLaunch=$executionTime")
        }
    }

    fun launchSequentialSuspendFunInRunBlocking() {
        runBlocking {
            val executionTime = measureTimeMillis {
                printA()
                printB()
            }
            println("Time taken for launchSequentialSuspendFunInRunBlocking=$executionTime")
        }
    }

    fun launchSequentialSuspendFunInGlobalLaunchMultipleLaunch() {
        GlobalScope.launch {
            val executionTime = measureTimeMillis {
                launch {
                    printA()
                }
                launch {
                    printB()
                }
            }
            println("Time taken for launchSequentialSuspendFunInGlobalLaunch=$executionTime")
        }
    }


    fun launchSequentialSuspendFunInRunBlockingMultipleLaunch() {
        println("[${printThreadInfo()}]")

        runBlocking(Dispatchers.Default) {
            println("[${printThreadInfo()}]")
            val executionTime = measureTimeMillis {
                launch {
                    printA()
                }
                launch {
                    printB()
                }
            }
            println("Time taken for launchSequentialSuspendFunInGlobalLaunch=$executionTime")
        }
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
        val executionTime = measureTimeMillis {
            runBlocking {
                launch {
                    val x = printARandomCount()
                    val y = printBRandomCount()
                    println("[${printThreadInfo()}] x+y=${x + y}")
                }
            }
        }
        println("Time taken for launchSequentialSuspendFunReturn=$executionTime")
    }

    fun launchAsyncSuspendFunReturn() {
        val executionTime = measureTimeMillis {
            runBlocking {
                val x = async { printARandomCount() }
                val y = async { printBRandomCount() }
                println("[${printThreadInfo()}] x+y=${x.await() + y.await()}")
            }
        }
        println("Time taken for launchAsyncSuspendFunReturn=$executionTime")
    }

    suspend fun launchAsyncCoroutineScope() : Int = coroutineScope {
        var result = 0
        val executionTime = measureTimeMillis {
            val x = async { printARandomCount() }
            val y = async { printBRandomCount() }
            result = x.await() + y.await()
        }
        println("Time taken for launchAsyncCoroutineScope=$executionTime")
        return@coroutineScope result
    }


    fun printThreadInfo() = "${LocalDateTime.now()} Thread=${Thread.currentThread().name}"
}

fun main() {
    CoroutinesPlayground().run {
//        println("launchSimplePrint")
//        launchSimplePrint()
//        println("launchSimpleFunction")
//        launchSimpleFunction()
//        println("launchSimpleFunctionWaitWithDelay")
//        launchSimpleFunctionWaitWithDelay()
//        println("launchSimpleFunctionJoin")
//        launchSimpleFunctionJoin()
//        println("launchSequentialSuspendFun")
//        runBlocking {
//            launchSequentialSuspendFun()
//        }

//        println("launchSequentialSuspendFunInGlobalLaunch")
//        launchSequentialSuspendFunInGlobalLaunch()
//        println("launchSequentialSuspendFunInRunBlocking")
//        launchSequentialSuspendFunInRunBlocking()
//        println("launchSequentialSuspendFunInGlobalLaunchMultipleLaunch")
//        launchSequentialSuspendFunInGlobalLaunchMultipleLaunch()
        println("launchSequentialSuspendFunInRunBlockingMultipleLaunch")
        launchSequentialSuspendFunInRunBlockingMultipleLaunch()

//        println("launchSequentialSuspendFunReturn")
//        launchSequentialSuspendFunReturn()
//        println("launchAsyncSuspendFunReturn")
//        launchAsyncSuspendFunReturn()
//
//        println("launchAsyncCoroutineScope")
//        runBlocking {
//            println("[${printThreadInfo()}] launchAsyncCoroutineScope return=${launchAsyncCoroutineScope()}")
//        }
        Thread.sleep(5000)
    }
}
