package com.bschandramohan.learn.kotlinconnect

fun arrayList() {
    val myList : List<String> = ArrayList()
    // myList.add("Chandra") // DOESN'T WORK
    myList.forEach(::println)

    val mutableList : MutableList<String> = ArrayList()
    mutableList.add("Chandra")
    mutableList.add("Mohan")
    mutableList.forEach(::println)
}

fun map() {
    val map = mutableMapOf<String, String> ()
    map["First Name"] = "Chandra"
    map["Second Name"] = "Mohan"

    println(map.getOrDefault("First Name", "DEFAULT"))
}

fun main() {
   // arrayList()
    map()
}
