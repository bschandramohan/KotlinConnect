package com.bschandramohan.learn.kotlinconnect.roulette

import kotlin.random.Random

class Roulette {
    private val maxNumberUntil = 38
    private val random = Random(System.currentTimeMillis())

    private fun getPocket() = wheel[random.nextInt(maxNumberUntil)]

    // Outside bets only
    enum class RouletteEnums(val winValue: Int = 2) {
        E,
        O,
        R,
        B,
        HB,
        LB,
        D1(3),
        D2(3),
        D3(3),
        C1(3),
        C2(3),
        C3(3)
    }

    private val singleNumberWinValue = 35

    private val wheel = arrayOf(
        0, 28, 9, 26, 30, 11, 7, 20, 32, 17, 5,
        22, 34, 15, 3, 24, 36, 13, 1, 37, 27,
        10, 25, 29, 12, 8, 19, 31, 18, 6, 21,
        33, 16, 4, 23, 35, 14, 2
    )

    private val redBet = arrayOf(
        1, 3, 5, 7, 9, 12,
        14, 16, 18, 19, 21, 23,
        25, 27, 30, 32, 34, 36
    )

    private val blackBet = arrayOf(
        2, 4, 6, 8, 10, 11,
        13, 15, 17, 20, 22, 24,
        26, 28, 29, 31, 33, 35
    )

    private val lowBet = (1..18)
    private val highBet = (19..36)

    private val dozen1Bet = (1..12)
    private val dozen2Bet = (13..24)
    private val dozen3Bet = (25..36)

    private val column1Bet = (1..34 step 3)
//    arrayOf(
//        1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34
//    )

    private val column2Bet = (2..35 step 3)
//        arrayOf(
//            2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35
//        )

    private val column3Bet = (3..36 step 3)
//    arrayOf(
//        3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36
//    )

    data class SpinResult(val win: Int, val pocket: Int)

    fun playSpin(bets: List<String>): SpinResult {
        val pocket = getPocket()

        var results = 0
        for (bet in bets) {
            results += when (bet.toUpperCase()) {
                RouletteEnums.E.name -> {
                    if (pocket.rem(2) == 0) RouletteEnums.E.winValue else 0
                }
                RouletteEnums.O.name -> {
                    if (pocket.rem(2) != 0) RouletteEnums.O.winValue else 0
                }
                RouletteEnums.LB.name -> {
                    if (lowBet.contains(pocket)) RouletteEnums.LB.winValue else 0
                }
                RouletteEnums.HB.name -> {
                    if (highBet.contains(pocket)) RouletteEnums.HB.winValue else 0
                }
                RouletteEnums.R.name -> {
                    if (redBet.contains(pocket)) RouletteEnums.R.winValue else 0
                }
                RouletteEnums.B.name -> {
                    if (blackBet.contains(pocket)) RouletteEnums.B.winValue else 0
                }
                RouletteEnums.D1.name -> {
                    if (dozen1Bet.contains(pocket)) RouletteEnums.D1.winValue else 0
                }
                RouletteEnums.D2.name -> {
                    if (dozen2Bet.contains(pocket)) RouletteEnums.D2.winValue else 0
                }
                RouletteEnums.D3.name -> {
                    if (dozen3Bet.contains(pocket)) RouletteEnums.D3.winValue else 0
                }
                RouletteEnums.C1.name -> {
                    if (column1Bet.contains(pocket)) RouletteEnums.C1.winValue else 0
                }
                RouletteEnums.C2.name -> {
                    if (column2Bet.contains(pocket)) RouletteEnums.C2.winValue else 0
                }
                RouletteEnums.C3.name -> {
                    if (column3Bet.contains(pocket)) RouletteEnums.C3.winValue else 0
                }
                else -> {
                    try {
                        if (bet.toInt() == pocket) singleNumberWinValue else 0
                    } catch (e: NumberFormatException) {
                        println("Invalid option=$bet entered")
                        0
                    }
                }
            }
        }

        return SpinResult(results, pocket)
    }

    fun aggregateRandomNumbers() {
        val counts = IntArray(maxNumberUntil) { 0 }
        (1..1000).forEach { _ ->
            counts[getPocket()]++
        }

        counts.forEachIndexed { index, it -> if (it > 0) println("[$index] = $it") }
    }

    companion object {
        @JvmStatic
        fun printWelcomeMessage() {
            listOf(
                "Welcome to Sireesha's Roulette Game!\n",
                "You start with $20 and wish you all the best to quadruple it",
                "Rules of the Game: Enter one or more of the below separated by space or comma:",
                "\tE -> for placing bet on EVEN numbers (payout 2 to 1)",
                "\tO -> for placing bet on ODD numbers (payout 2 to 1)",
                "\tR -> for placing bet on RED numbers (payout 2 to 1)",
                "\tB -> for placing bet on BLACK numbers (payout 2 to 1)",
                "\tLB -> for placing bet on LOW BET (1 to 18) numbers (payout 2 to 1)",
                "\tHB -> for placing bet on HIGH BET (19 to 37) numbers (payout 2 to 1)",
                "\tD1 -> for placing bet on FIRST DOZEN numbers (payout 3 to 1)",
                "\tD2 -> for placing bet on SECOND DOZEN numbers (payout 3 to 1)",
                "\tD3 -> for placing bet on THIRD DOZEN numbers (payout 3 to 1)",
                "\tC1 -> for placing bet on FIRST COLUMN numbers (payout 3 to 1)",
                "\tC2 -> for placing bet on SECOND COLUMN numbers (payout 3 to 1)",
                "\tC3 -> for placing bet on THIRD COLUMN numbers (payout 3 to 1)",
                "\tANY NUMBER from 1 to 36 -> to place BET on that number (payout 35 to 1)",
                "\t0 or 37 -> to place bet on 0 or 00 as traditionally known (payout 35 to 1)",
                "END OR QUIT -> to quit the game\n"
            ).forEach { println(it) }
            print("> ")
        }
    }
}

fun main() {
    Roulette.printWelcomeMessage()
    var balanceAmount = 20

    do {
        val bet = readLine() ?: ""
        if (bet.toUpperCase() == "END" || bet.toUpperCase() == "QUIT" || balanceAmount <= 0) {
            println("Thanks for playing the game! Your balance is $$balanceAmount")
            return
        }

        val bets = bet.split(" ", ",").filter { it.isNotEmpty() }
        val result = Roulette().playSpin(bets)
        balanceAmount += (result.win - bets.size)
        println("You chose bet=${bets.joinToString(",")} Pocket=${result.pocket}; You made $${result.win} in this round; Balance=$$balanceAmount")
        print("> ")
    } while (true)
}
