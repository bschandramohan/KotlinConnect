package com.bschandramohan.learn.kotlinconnect

data class ReflectionCheck(var name: String, var id: Long)

fun main() {
    ReflectionCheck::class.java.methods.filter { it.name.startsWith("get") || it.name.startsWith("set") }.forEach {
        print("\nMethod Name=${it.name} ReturnType=${it.returnType} Parameters=")
        it.parameters.forEach { parameter -> print("${parameter.type};") }
    }
}
