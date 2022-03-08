fun main() {
    val table = Table(playerOne = generatePlayer("Todor"), playerTwo = generatePlayer("Danio"))
    while (true) {
        if (checkForGameWinner(table)) {
            break
        }
        playRoundForPlayer(table.playerOne)
        playRoundForPlayer(table.playerTwo)
        println("--------------------------------------------------------------------------------------------")
    }

}