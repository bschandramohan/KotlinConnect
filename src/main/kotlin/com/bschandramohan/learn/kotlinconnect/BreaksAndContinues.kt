package com.bschandramohan.learn.kotlinconnect

fun foo() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // local return to the caller of the lambda - the forEach loop
        print(it)
    }
    println(" done with explicit label")
}

fun foo1() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return@forEach // local return to the caller of the lambda - the forEach loop
        print(it)
    }
    println(" done with implicit label")
}

fun foo2() {
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop
            print(it)
        }
        println(" done with listOf")
    }
    println(" done with run loop@")
}


fun main() {
    foo()
    foo1()
    foo2()
}