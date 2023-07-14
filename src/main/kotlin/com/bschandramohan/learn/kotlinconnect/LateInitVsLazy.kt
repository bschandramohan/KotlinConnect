package com.bschandramohan.learn.kotlinconnect
class LateInitVsLazy {
    lateinit var name: String
    val lazyName: String by lazy {
        println("Initializing lazyName")
        "LazyName"
    }

    fun init() {
        name = "Name"
    }
}

fun main() {
    val lateInitVsLazy = LateInitVsLazy()
    try {
        println("Before init: ${lateInitVsLazy.name}")
    } catch (e: Exception) {
        println("Before init: $ {lateInitVsLazy.name} Exception: ${e.message}")
    }
    println("Before init: ${lateInitVsLazy.lazyName}")

    lateInitVsLazy.init()
    println("After init: ${lateInitVsLazy.name}")
    println("After init: ${lateInitVsLazy.lazyName}")
}