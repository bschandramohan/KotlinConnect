package com.bschandramohan.learn.kotlinconnect.gamesamples

import java.util.*

class TikTocToe(var playerA: Player, var playerB: Player) {
//    private var gameState: Array<IntArray> = Array(3) { IntArray(3)}
    private var gameState = IntArray(9)

    fun play() {
        (0..8).forEach {
            if (setPlayerPlay(it) != null) return
        }
    }

    private fun setPlayerPlay(index: Int) : Int? {
        val currentPlayer = if (index %2 == 0) playerA else playerB
        do {
            try {
                println("Enter ${currentPlayer.name}'s index:")
                setCurrentPlay(currentPlayer, getPlayerIndex())
                break
            } catch (e: Exception) {
            }
        }
        while (true)

        displayState()
        return checkWinner()
    }

    private fun setCurrentPlay(player: Player, index: Int) {
        if (gameState[index] != 0)
            throw Exception("ERROR! It's already set")

        gameState[index] = if (player == playerA) 1 else 2
    }

    private fun displayState() {
        println("Current Tic Tac Toe State")
        gameState.toList().chunked(3).forEach {
            it.forEach { entry ->
                when (entry) {
                    1 -> print("X ")
                    2 -> print("O ")
                    else -> print("_ ")
                }
            }
            println()
        }
        println()
    }

    private fun getPlayerIndex(): Int {
        var playerIndex: Int?
        do {
            playerIndex = readLine()?.toInt()
            if (playerIndex == null || (playerIndex < 0 || playerIndex > 8)) {
                println("Invalid index. Please try again!")
            } else {
                break
            }
        } while (true)
        return playerIndex!!
    }

    private fun checkWinner(): Int? = (checkHorizontal() ?: checkVertical() ?: checkDiagonal()).also {
        if (it != null) println("Player ${if (it == 1) playerA.name else playerB.name} WON!")
    }

    private fun checkHorizontal(): Int? {
        if (gameState[0] != 0 && gameState[0] == gameState[1] && gameState[1] == gameState[2])
            return gameState[0]
        if (gameState[3] != 0 && gameState[3] == gameState[4] && gameState[4] == gameState[5])
            return gameState[3]
        if (gameState[6] != 0 && gameState[6] == gameState[7] && gameState[7] == gameState[8])
            return gameState[6]

        return null
    }

    private fun checkVertical(): Int? {
        if (gameState[0] != 0 && gameState[0] == gameState[3] && gameState[3] == gameState[6])
            return gameState[0]
        if (gameState[1] != 0 && gameState[1] == gameState[4] && gameState[4] == gameState[7])
            return gameState[1]
        if (gameState[2] != 0 && gameState[2] == gameState[5] && gameState[5] == gameState[8])
            return gameState[2]

        return null
    }

    private fun checkDiagonal(): Int? {
        if (gameState[0] != 0 && gameState[0] == gameState[4] && gameState[4] == gameState[8])
            return gameState[0]
        if (gameState[3] != 0 && gameState[3] == gameState[4] && gameState[4] == gameState[6])
            return gameState[3]

        return null
    }
}

data class Player(val id: UUID = UUID.randomUUID(), val name: String, val email: String = "")

fun main() {
    println("Welcome to Tic Tac Toe Game!\nEnter the names of the gamers\n")
    println("Player 1:")
    val playerA = Player(name = getPlayerName())
    println("Player 2:")
    val playerB = Player(name = getPlayerName())

    val game = TikTocToe(playerA, playerB)
    game.play()
}

private fun getPlayerName(): String {
    var playerName: String
    do {
        playerName = readLine().toString()
        if (playerName.isBlank()) {
            println("Invalid name. Please try again!")
        } else {
            break
        }
    } while (true)
    return playerName
}
