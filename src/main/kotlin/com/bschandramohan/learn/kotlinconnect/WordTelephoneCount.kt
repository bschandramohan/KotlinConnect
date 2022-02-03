package com.bschandramohan.learn.kotlinconnect

fun wordTelephoneCount(word: String) {
    println(word.toLowerCase())
    var sum = 0
    word.toLowerCase().forEachIndexed { index, it ->
        val value = when(it) {
            in "abc" -> 2
            in "def" -> 3
            in "ghi" -> 4
            in "jkl" -> 5
            in "mno" -> 6
            in "pqrs" -> 7
            in "tuv" -> 8
            in "wxyz" -> 9
            else -> 0
        }
        sum += value
        print("$it($value)")
        if (index < word.length - 1)
            print(" + ")
        else
            print(" = ")
    }
    println(sum)
}

fun main() {
listOf("Declaration",
        "Independence",
        "Self-evident",
        "Endowed",
        "Unalienable",
        "Liberty",
        "Pursuit",
        "Charters",
        "Congress",
        "America").forEach { wordTelephoneCount(it) }
}