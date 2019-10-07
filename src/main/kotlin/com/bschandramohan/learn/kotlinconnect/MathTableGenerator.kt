package com.bschandramohan.learn.kotlinconnect

fun mathSingleTableGenerator(number: Int, upto: Int = 10) {
    println("Printing number=$number table")
    for (i in 1..upto) {
        println("$number * $i = ${number * i}")
    }
    println()
}

fun mathMultiTableGenerator(vararg numbers: Int, upto: Int = 10) {
    for (number in numbers) {
        println("Printing number=$number table")
        for (i in 1..upto) {
            println("$number * $i = ${number * i}")
        }
        println()
    }
}

fun mathMultiTableTogetherGenerator(vararg numbers: Int, upto: Int = 10) {
    for (i in 1..upto) {
        for (number in numbers) {
            print("${number.padSpace()} * ${i.padSpace()} = ${(number * i).padSpace(3)}\t\t")
        }
        println()
    }
}


fun Int.padSpace(padCount : Int = 2) = this.toString().padStart(padCount, ' ')

fun main() {
//    mathSingleTableGenerator(19)
//    mathSingleTableGenerator(29)
//    mathSingleTableGenerator(39)

//    mathMultiTableGenerator(9, 19, 29, 39, 49, 59, 69, 79, 89, 99)
//    mathMultiTableTogetherGenerator(9, 19, 29, 39, 49, 59, 69, 79, 89, 99)
    val numbersList = intArrayOf(9, 19, 29, 39, 49, 59, 69, 79, 89, 99)
    mathMultiTableTogetherGenerator(*numbersList) // Spread operator required to indicate parameter as varargs
}
