package com.bschandramohan.learn.kotlinconnect

/**
 * com.bschandramohan.learn.kotlinconnect.Calculator class that takes a Number type as parameter and gives operations like com.bschandramohan.learn.kotlinconnect.getSum and com.bschandramohan.learn.kotlinconnect.getDiff
 *
 * @author bschandramohan
 */
class Calculator<T: Number> {

    // NOTE: Though a, b are T of type number, you cannot just do + directly
    fun doSum(a: T, b: T) : T  {
        val result : Number = when {
            a is Int && b is Int -> a + b
            a is Long && b is Long -> a + b
            a is Float && b is Float -> a + b
            a is Double && b is Double -> a + b
            else -> throw IllegalArgumentException()
        }

        @Suppress("UNCHECKED_CAST")
        return result as T
    }

    fun doDiff(a: T, b: T) : T  {
        val result : Number = when {
            a is Int && b is Int -> a - b
            a is Long && b is Long -> a - b
            a is Float && b is Float -> a - b
            a is Double && b is Double -> a - b
            else -> throw IllegalArgumentException()
        }

        @Suppress("UNCHECKED_CAST")
        return result as T
    }

    fun doOperation(a: T, b: T, operation: (a: T, b: T) -> T) : T {
        return operation(a, b)
    }
}

fun main(args: Array<String>) {
    val mathClass = Calculator<Int>()

    println(mathClass.doSum(3, 5))
    println(mathClass.doDiff(13, 5))

    println(mathClass.doOperation(4, 6, ::doSum))
    // Give the com.bschandramohan.learn.kotlinconnect.operation at invocation time instead of creating a function and invoking it
    println(mathClass.doOperation(5,7) { x, y -> x * y })
}
