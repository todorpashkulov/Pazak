data class Table(
    val playerOne: Player,
    val playerTwo: Player
)

data class Player(
    val name:String,
    var score: Int = 0,
    var field: Int = 0,
    var cards: MutableList<Card>,
    var standing: Boolean = false
)

data class Card(var points: Int)