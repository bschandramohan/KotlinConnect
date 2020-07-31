package com.bschandramohan.learn.kotlinconnect

import java.lang.Math.ceil

fun String.caesarCipherText(numLetters: Int = 1) : String {
    val cipheredText = StringBuilder()
    this.forEach { cipheredText.append( if (it in ('A'..'Z')) { it.plus(numLetters) } else { it }) }
    println("$this CipherText=$cipheredText")
    return cipheredText.toString()
}

fun String.caesarDecipherText(numLetters: Int = 1) : String {
    val decipheredText = StringBuilder()
    this.forEach { decipheredText.append(if (it in ('A'..'Z')) { it.minus(numLetters) } else { it }) }
    println("$this DecipherText=$decipheredText")
    return decipheredText.toString()
}

fun String.caesarShiftCipherText(shiftWord: String) : String {
    val cipheredText = StringBuilder()
    val cipherMap = getCipherMap(shiftWord)
    this.forEach { cipheredText.append(cipherMap[it] ?: it) }
    println("$this ShiftCipherText=$cipheredText")
    return cipheredText.toString()
}

fun String.caesarShiftDecipherText(shiftWord: String) : String {
    val decipheredText = StringBuilder()
    val cipherMap = reverseMap(getCipherMap(shiftWord))
    this.forEach { decipheredText.append(cipherMap[it] ?: it) }
    println("$this ShiftDecipherText=$decipheredText")
    return decipheredText.toString()
}

fun String.railCipher(numRails : Int = 2) : String {
    val list1 = mutableListOf<Char>()
    val list2 = mutableListOf<Char>()
    val list3 = mutableListOf<Char>()

    if (numRails == 2) {
        this.forEachIndexed { index, value ->
            when {
                index %4 == 0 -> {
                    list1.add(value)
                }
                index % 2 == 0 -> {
                    list3.add((value))
                }
                else -> {
                    list2.add(value)
                }
            }
        }
//        println(list1)
//        println(list2)
//        println(list3)
        list1.addAll(list2)
        list1.addAll(list3)

        val cipherText = StringBuilder()
        list1.forEach { cipherText.append(it) }
        println("$this RailCipherText=$cipherText")

        return cipherText.toString()
    }

    return this
}

fun String.railDecipher(numRails: Int = 2) : String {
    if (numRails == 2) {
        val list1Size = kotlin.math.ceil(this.length / 4.0).toInt()
        val list3Size = (this.length / 4.0).toInt()
        val list2Size = this.length - list1Size - list3Size

        val list1 = this.substring(0, list1Size).toList()
        val list2 = this.substring(list1Size, list1Size + list2Size).toList()
        val list3 = this.substring(list1Size + list2Size, list1Size + list2Size + list3Size).toList()

//        println(list1)
//        println(list2)
//        println(list3)
        val decipherText = StringBuilder()
        var listCurrentCount = 0
        for (i in 0..this.length step 4) {
            try {
                decipherText.append(list1[listCurrentCount])
                decipherText.append(list2[listCurrentCount * 2])
                decipherText.append(list3[listCurrentCount])
                decipherText.append(list2[listCurrentCount * 2 + 1])
                listCurrentCount++
            } catch (e: IndexOutOfBoundsException) {
                // Ignore
            }
        }

        println("$this RailDecipherText=$decipherText")
        return decipherText.toString()
    }

    return this
}

private fun getCipherMap(shiftWord: String): MutableMap<Char, Char> {
    val cipherMap = mutableMapOf<Char, Char>()
    var startChar = 'A'
    shiftWord.forEach {
        if (!cipherMap.containsKey(it)) {
            cipherMap[it] = startChar++
        }
    }
    for (remainingChar in 'A'..'Z') {
        if (!cipherMap.containsKey(remainingChar)) {
            cipherMap[remainingChar] = startChar++
        }
    }

    // println(cipherMap)
    return cipherMap
}

private fun reverseMap(map: MutableMap<Char, Char>) : Map<Char, Char> {
    return map.entries.associateBy({it.value}, {it.key})

//    val numbersMap = mutableMapOf<Any, Any>("key1" to "1", "key2" to "2", "key3" to "3", "key11" to "11")
//    // numbersMap.map { it.key, it.value }
//    println(numbersMap.entries.groupBy({it.value}, {it.key}))
//    println(numbersMap.entries
//        .groupingBy { it.value }
//        .aggregate { key, accumulator: String?, element, first ->
//            element.key as String?
//        })
//    println(numbersMap.entries.associateBy({ it.value }) { it.key })
}

fun main() {
    "Chandra Mohan Sireesha Keerthi".toUpperCase().caesarCipherText().caesarDecipherText()
    "Chandra Mohan Sireesha Keerthi".toUpperCase().caesarShiftCipherText("RHYTHM").caesarShiftDecipherText("RHYTHM")
    "Chandra Mohan Sireesha Keerthi".toUpperCase().railCipher().railDecipher()
    "YOURTERMSAREACCEPTABLE".railCipher().railDecipher()
    "YUTRSRACPALOREMAECETBE".railDecipher()
}
