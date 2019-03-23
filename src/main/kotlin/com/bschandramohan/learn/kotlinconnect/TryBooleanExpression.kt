package com.bschandramohan.learn.kotlinconnect
val printAllOptionsOne: (Boolean, Boolean) -> Unit = { a: Boolean, b: Boolean -> println( a || (!a && !b)) }
val printAllOptionsTwo: (Boolean, Boolean) -> Unit = { a: Boolean, b: Boolean -> println( a || !b) }

fun evaluate(f: (Boolean, Boolean) -> Unit, a: Boolean, b: Boolean) {
    f(a, b)
}

fun main(args: Array<String>) {
    check(printAllOptionsOne)
    check(printAllOptionsTwo)
}

private fun check(function: (Boolean, Boolean) -> Unit) {
    evaluate(function, true, true)
    evaluate(function, true, false)
    evaluate(function, false, true)
    evaluate(function, false, false)
}
