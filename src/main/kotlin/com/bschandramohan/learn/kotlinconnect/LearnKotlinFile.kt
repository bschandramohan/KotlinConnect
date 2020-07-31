package com.bschandramohan.learn.kotlinconnect

import java.io.File

fun main() {
    val file = File("/tmp/test.tsv")
    file.writeText("firstname\tlastname\n")

    file.appendText("chandra\tmohan\n")
    file.appendText("sireesha\tchandra\n")

    Thread.sleep(100000)
}
