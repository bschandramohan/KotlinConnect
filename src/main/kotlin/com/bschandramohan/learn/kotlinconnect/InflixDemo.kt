package com.bschandramohan.learn.kotlinconnect

class InflixDemo {
    var data = 10L
    infix fun printValue(description: String) = println("$description $data")
}

fun main() {
    InflixDemo() printValue "AwesomeValue"
}
