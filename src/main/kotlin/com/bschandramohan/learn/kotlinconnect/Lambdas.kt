package com.bschandramohan.learn.kotlinconnect

// Lambda Expression
var sum = { a: Int, b: Int -> a + b }

// Lambda Expression
var diff = { a: Int, b: Int -> a - b }

// Higher order function - taking a function as argument
fun operation (a: Int, b: Int, operate: (Int, Int) -> Int ) : Int {
    return operate(a, b)
}

// Lambda Expression that takes a function as parameter
var operation0 = { a: Int, b: Int, operate: (Int, Int) -> Int -> operate(a, b) }

// Function literal with inline lambda definition
var operation1: (Int, Int, (Int, Int) -> Int) -> Int = { a, b, operate -> operate(a, b) }

fun main(args: Array<String>) {
    println(operation(2, 3, sum))
    println(operation(8, 5, diff))
    println(operation(5, 8) { x, y -> x * y })
    println(operation0(5, 8, sum))
    println(operation1(5, 8, sum))

}

