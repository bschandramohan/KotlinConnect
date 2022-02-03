package com.bschandramohan.learn.kotlinconnect

import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class `Demo3N+1` {
    @ExperimentalTime
    fun loopsTo1(n: Int) : Pair<Int, Int> {
        var highestInlineNumber = n
        var loopCount = 0
        var inputNumber = n

        val timeTaken = measureTime {
            while (inputNumber != 1) {
                if (inputNumber % 2 == 0)
                    inputNumber /= 2
                else
                    inputNumber  = 3 * inputNumber + 1

                if (inputNumber > highestInlineNumber) highestInlineNumber = inputNumber
                loopCount++
            }
        }

        println("Input Number=$n TotalLoopCount=$loopCount HighestInlineNumber=$highestInlineNumber " +
                "timeTakenInMicroseconds=${timeTaken.inMicroseconds}")

        return Pair(loopCount, highestInlineNumber)
    }
}

@ExperimentalTime
fun main() {
    val inputNumbers = intArrayOf(1, 2, 7, 26, 27, 51, 1001)
    inputNumbers.forEach {
        `Demo3N+1`().loopsTo1(it)
    }
}
