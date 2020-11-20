package com.bschandramohan.learn.kotlinconnect

import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime

class HeavyObjectInitializationForWithout {
    init {
        Thread.sleep(1000)
        println("HeavyObjectInitializationForWithout initialized")
    }
}

class HeavyObjectInitializationForWith {
    init {
        Thread.sleep(1000)
        println("HeavyObjectInitializationForWith initialized")
    }
}

class WithoutLazyDemo {
    private val heavyObject = HeavyObjectInitializationForWithout()

    fun operation1() = measureNanoTime {
        println("Required operation1")
        println(heavyObject)
    }
    fun operation2() = measureNanoTime {
        println("Required operation2")
    }
    fun operation3() = measureNanoTime {
        println(heavyObject)
    }
}

class WithLazyDemo {
    private val heavyObject : HeavyObjectInitializationForWith by lazy {
        HeavyObjectInitializationForWith()
    }

    fun operation1() = measureNanoTime {
        println("Required operation1")
        println(heavyObject)
    }
    fun operation2() = measureNanoTime {
        println("Required operation2")
    }
    fun operation3() = measureNanoTime {
        println(heavyObject)
    }
}

@ExperimentalTime
fun main() {
    // Withoutlazy Demo
    lateinit var withoutLazyDemo: WithoutLazyDemo
    println("Initialization timeTaken=${measureTimeMillis { withoutLazyDemo = WithoutLazyDemo() } }")

    with(withoutLazyDemo) {
        //println(operation1())
        println(operation2() / 1000)
        println(operation3() / 1000)
        println(operation3() / 1000)
    }

    println("\n")

    // Withlazy Demo
    lateinit var withLazyDemo: WithLazyDemo
    println("Initialization timeTaken=${measureTimeMillis { withLazyDemo = WithLazyDemo() } }")

    with(withLazyDemo) {
        //println(operation1())
        println(operation2() / 1000)
        println(operation3() / 1000)
        println(operation3() / 1000)
    }
}