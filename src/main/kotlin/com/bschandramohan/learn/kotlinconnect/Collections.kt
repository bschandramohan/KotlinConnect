package com.bschandramohan.learn.kotlinconnect

fun arrayList() {
    val myList : List<String> = ArrayList()
    // myList.add("Chandra") // DOESN'T WORK
    myList.forEach(::println)

    val mutableList : MutableList<String> = ArrayList()
    mutableList.add("Chandra")
    mutableList.forEach(::println)
}

fun main() {
    arrayList()
}
