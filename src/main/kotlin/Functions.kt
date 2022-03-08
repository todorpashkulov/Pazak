fun generatePlayer(name: String): Player {
    return Player(name = name, cards = generateCards())
}

fun generateCards(): MutableList<Card> {
    val cards = mutableListOf<Card>()
    for (i in 1..4) {
        var num = (-5..5).random()
        if (num == 0) {
            num++
        }
        cards.add(Card(num))
    }
    return cards
}

fun playRoundForPlayer(player: Player): Unit {
    if (!player.standing) {
        val num = (1..10).random()
        player.apply { field += num }
        println("Adding $num points to ${player.name}'s field.")

    }
    println("Player ${player.name} field is ${player.field}")


    if (!player.standing) {
        println("Player ${player.name} cards:")
        for (i in player.cards.indices) {
            println("Card $i is ${player.cards[i]}")
        }

        println("Write end,stand or play")
        when (readLine()) {
            "p" -> {
                println("Select which card to play")
                val cardToPlay = readLine()?.toInt()
                player.apply { field += cards[cardToPlay!!].points }
                println("Added ${player.cards[cardToPlay!!].points} points to ${player.name} his field is now ${player.field}")
                println("//////////////////////////////////////////NEXT PLAYER//////////////////////////////////////////")
                player.cards.removeAt(cardToPlay)
            }
            "s" -> {
                println("${player.name} is now standing.")
                player.standing = true
            }
            "e" -> println("//////////////////////////////////////////NEXT PLAYER//////////////////////////////////////////")
        }
    } else {
        println("${player.name} is standing.")
    }

}

fun checkForRoundWinner(table: Table): Player? {
    if (table.playerOne.standing && table.playerTwo.standing) {
        return if (table.playerOne.field == table.playerTwo.field) {
            println("ITS A TIE BOIS SORRY!!!")
            return Player(name = "TIE", cards = mutableListOf())
        } else if (table.playerOne.field > table.playerTwo.field) {
            println("${table.playerOne.name} WON THE ROUND!!")
            table.playerOne
        } else {
            println("${table.playerTwo.name} WON THE ROUND!!")
            table.playerTwo
        }
    } else if (table.playerOne.field == 20 && table.playerTwo.field == 20) {
        println("ITS A TIE BOIS SORRY!!!")
        return Player(name = "TIE", cards = mutableListOf())
    } else if (table.playerOne.field == 20) {
        println("${table.playerOne.name} WINS THE ROUND!!!!")
        return table.playerOne
    } else if (table.playerTwo.field == 20) {
        println("${table.playerTwo.name} WINS THE ROUND!!!!")
        return table.playerTwo
    } else if (table.playerOne.field > 20) {
        println("${table.playerOne.name} went over 20 ${table.playerTwo.name} WINS THE ROUND!!!!")
        return table.playerTwo
    } else if (table.playerTwo.field > 20) {
        println("${table.playerTwo.name} went over 20 ${table.playerOne.name} WINS THE ROUND!!!!")
        return table.playerOne
    }
    return null
}

fun checkForGameWinner(table: Table): Boolean {
    val player = checkForRoundWinner(table)
    if (player != null && player.name != "TIE") {
        player.apply { score++ }
        printScore(table)
        if (player.score == 3) {
            println("${player.name} WON THE GAME")
            return true
        }
        table.resetPlayers()
    }
    if (player != null && player.name == "TIE"){
        printScore(table)
        table.resetPlayers()
    }
    return false
}

fun printScore(table: Table){
    println("Current score is :")
    println("${table.playerOne.name} : ${table.playerOne.score}")
    println("${table.playerTwo.name} : ${table.playerTwo.score}")
}

fun Table.resetPlayers() {
    println("Resetting Players and Starting new Round")
    println("#################################################################################################")
    this.playerOne.field = 0
    this.playerTwo.field = 0
    this.playerOne.standing = false
    this.playerTwo.standing = false
    this.playerOne.cards = generateCards()
    this.playerTwo.cards = generateCards()
}