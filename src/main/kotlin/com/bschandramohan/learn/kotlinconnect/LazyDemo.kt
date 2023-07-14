package com.bschandramohan.learn.kotlinconnect

import java.time.LocalDateTime
class LazyDemo {
    val customValue: String by lazy {
        "Year-" + LocalDateTime.now().year
    }
    val customException: RuntimeException by lazy {
        RuntimeException("Custom Exception")
    }

    fun init() {
        // No need of specific init method to initialize the lazy values
    }

    fun process() {
        println("Performing costly operations in Process")
        printCustomValue()
    }
    fun printCustomValue() {
        // Custom value is initialized only when it is accessed
        println("Custom Value: $customValue")
        // Multiple access of the same lazy value will not reinitialize it
        println("Custom Value: $customValue")
        // Useful for initializing objects that are rarely required in the lifecycle
        // of the class/object
        throw customException
    }
}
