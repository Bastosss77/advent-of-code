package advent_2022.puzzles

typealias Cargo = Array<Array<String>>

fun day5() {
    val moves = testInputMoves.reader().readLines()
    var cargo = testInputCargo

    parseMoves(moves).forEach {
        cargo = makeMove(it, cargo)
    }

    val test = cargo.map { it.last() }

    println("December 4th (Part 1) : $test")
    println("December 4th (Part 2) : 0")
}

private fun makeMove(move: Move, into: Cargo) : Cargo {
    var newCargo = into.clone()

    for(i in 0 until move.count) {
        var toIndex = firstRowIndexInColumn(newCargo, move.to) - 1

        if (toIndex == 0) {
            toIndex += 1
            newCargo = arrayOf(Array(newCargo[0].size) { "" }, *newCargo)
        }

        val fromIndex = firstRowIndexInColumn(newCargo, move.from)
        val currentValue = newCargo[fromIndex][move.from - 1]

        newCargo[fromIndex][move.from - 1] = ""
        newCargo[toIndex][move.to - 1] = currentValue

        newCargo = newCargo.filterNot { array -> array.all { it == "" } }.toTypedArray()
    }

    return newCargo
}

private fun firstRowIndexInColumn(from: Cargo, column: Int) : Int {
    for(i in from.indices) {
        if(from[i][column - 1].isNotEmpty()) {
            return i
        }
    }

    return from.size - 1
}

private fun parseMoves(moves: List<String>) : List<Move> = moves.map { move ->
    move.split(" ").let {
        Move(it[1].toInt(), it[3].toInt(), it[5].toInt())
    }
}

private data class Move(
    val count: Int,
    val from: Int,
    val to: Int
)

private val testInputCargo =
    arrayOf(
        arrayOf("", "D", ""),
        arrayOf("N", "C", ""),
        arrayOf("Z", "M", "P")
    )

private val testInputMoves = """
move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
""".trimIndent()

private val inputCargo =
    arrayOf(
        arrayOf("", "", "S", "C", "", "", "Z", "", ""),
        arrayOf("F", "J", "P", "", "", "T", "", "N", ""),
        arrayOf("G", "H", "G", "Q", "", "G", "", "D", ""),
        arrayOf("V", "V", "D", "G", "F", "D", "", "V", ""),
        arrayOf("R", "B", "F", "N", "N", "Q", "L", "S", ""),
        arrayOf("J", "M", "M", "P", "H", "V", "B", "B", "D"),
        arrayOf("L", "P", "H", "D", "L", "F", "D", "J", "L"),
        arrayOf("D", "T", "V", "M", "J", "N", "F", "M", "G"),
        )

private val inputMoves = """
move 3 from 4 to 6
move 1 from 5 to 8
move 3 from 7 to 3
move 4 from 5 to 7
move 1 from 7 to 8
move 3 from 9 to 4
move 2 from 8 to 2
move 4 from 4 to 5
move 2 from 5 to 1
move 2 from 5 to 6
move 7 from 8 to 1
move 9 from 3 to 9
move 11 from 6 to 5
move 2 from 6 to 7
move 12 from 1 to 4
move 10 from 2 to 9
move 2 from 3 to 9
move 1 from 7 to 5
move 4 from 7 to 6
move 2 from 6 to 1
move 5 from 1 to 6
move 10 from 9 to 1
move 9 from 9 to 8
move 13 from 4 to 3
move 7 from 6 to 2
move 2 from 8 to 5
move 9 from 3 to 9
move 8 from 9 to 8
move 4 from 8 to 4
move 1 from 7 to 5
move 3 from 9 to 1
move 7 from 2 to 1
move 1 from 3 to 1
move 1 from 3 to 6
move 1 from 6 to 1
move 2 from 3 to 6
move 5 from 4 to 1
move 1 from 6 to 1
move 3 from 8 to 7
move 8 from 8 to 4
move 3 from 5 to 4
move 1 from 6 to 7
move 1 from 5 to 8
move 4 from 5 to 2
move 7 from 5 to 8
move 3 from 2 to 7
move 7 from 4 to 8
move 11 from 8 to 4
move 15 from 4 to 1
move 25 from 1 to 6
move 4 from 8 to 7
move 1 from 2 to 4
move 11 from 6 to 4
move 12 from 6 to 3
move 1 from 1 to 9
move 1 from 9 to 8
move 16 from 1 to 3
move 1 from 8 to 7
move 12 from 4 to 6
move 9 from 6 to 5
move 3 from 6 to 5
move 6 from 7 to 5
move 3 from 3 to 5
move 2 from 6 to 3
move 11 from 5 to 8
move 2 from 8 to 3
move 2 from 1 to 4
move 7 from 3 to 1
move 2 from 4 to 6
move 2 from 6 to 2
move 5 from 7 to 3
move 1 from 1 to 6
move 1 from 1 to 8
move 2 from 2 to 5
move 1 from 7 to 4
move 1 from 1 to 2
move 10 from 3 to 5
move 11 from 3 to 6
move 1 from 4 to 9
move 1 from 9 to 4
move 1 from 4 to 2
move 2 from 5 to 9
move 2 from 2 to 8
move 2 from 1 to 6
move 2 from 1 to 2
move 2 from 3 to 6
move 3 from 8 to 1
move 3 from 1 to 4
move 7 from 8 to 3
move 2 from 9 to 5
move 2 from 4 to 9
move 7 from 5 to 6
move 2 from 8 to 6
move 1 from 4 to 8
move 2 from 2 to 4
move 21 from 6 to 3
move 10 from 5 to 7
move 7 from 7 to 6
move 1 from 9 to 3
move 1 from 4 to 9
move 1 from 9 to 4
move 1 from 8 to 4
move 8 from 6 to 4
move 1 from 4 to 5
move 1 from 5 to 8
move 4 from 3 to 6
move 1 from 8 to 2
move 1 from 4 to 2
move 2 from 7 to 3
move 2 from 2 to 7
move 22 from 3 to 5
move 4 from 6 to 2
move 2 from 6 to 9
move 7 from 3 to 9
move 6 from 9 to 1
move 18 from 5 to 3
move 2 from 5 to 4
move 20 from 3 to 5
move 3 from 7 to 3
move 5 from 1 to 2
move 11 from 5 to 7
move 1 from 1 to 7
move 3 from 9 to 3
move 16 from 5 to 8
move 7 from 8 to 7
move 1 from 9 to 2
move 8 from 2 to 3
move 2 from 2 to 4
move 3 from 3 to 1
move 9 from 3 to 8
move 1 from 6 to 3
move 9 from 7 to 3
move 3 from 1 to 8
move 1 from 7 to 9
move 1 from 9 to 4
move 1 from 7 to 5
move 10 from 4 to 5
move 2 from 4 to 2
move 19 from 8 to 5
move 1 from 8 to 3
move 4 from 3 to 5
move 2 from 4 to 8
move 4 from 7 to 8
move 4 from 3 to 9
move 4 from 7 to 6
move 2 from 2 to 5
move 2 from 3 to 2
move 6 from 8 to 7
move 1 from 8 to 4
move 2 from 6 to 4
move 3 from 4 to 8
move 3 from 9 to 2
move 4 from 7 to 8
move 28 from 5 to 8
move 16 from 8 to 4
move 11 from 8 to 4
move 3 from 3 to 4
move 7 from 5 to 8
move 13 from 8 to 7
move 1 from 5 to 6
move 1 from 6 to 7
move 1 from 9 to 2
move 2 from 6 to 2
move 12 from 4 to 9
move 4 from 4 to 1
move 2 from 9 to 8
move 4 from 8 to 3
move 3 from 4 to 5
move 4 from 4 to 1
move 4 from 4 to 7
move 3 from 7 to 9
move 5 from 9 to 7
move 7 from 2 to 3
move 1 from 5 to 7
move 8 from 1 to 5
move 1 from 2 to 4
move 11 from 3 to 1
move 10 from 5 to 3
move 3 from 9 to 1
move 3 from 9 to 6
move 5 from 1 to 6
move 7 from 6 to 9
move 8 from 9 to 7
move 9 from 3 to 4
move 1 from 6 to 9
move 8 from 7 to 1
move 9 from 4 to 2
move 2 from 1 to 6
move 3 from 2 to 6
move 4 from 4 to 6
move 2 from 9 to 8
move 2 from 1 to 2
move 1 from 3 to 8
move 2 from 8 to 4
move 1 from 6 to 8
move 11 from 1 to 6
move 1 from 1 to 5
move 3 from 2 to 9
move 2 from 9 to 3
move 1 from 1 to 7
move 2 from 4 to 9
move 4 from 2 to 9
move 2 from 8 to 5
move 10 from 6 to 1
move 2 from 5 to 6
move 5 from 9 to 8
move 5 from 8 to 7
move 1 from 2 to 1
move 7 from 1 to 2
move 2 from 9 to 4
move 1 from 3 to 5
move 15 from 7 to 2
move 8 from 6 to 3
move 2 from 4 to 3
move 2 from 6 to 4
move 4 from 7 to 1
move 4 from 7 to 5
move 1 from 6 to 4
move 3 from 1 to 7
move 5 from 7 to 6
move 4 from 7 to 5
move 18 from 2 to 4
move 5 from 6 to 4
move 4 from 1 to 2
move 8 from 3 to 8
move 2 from 8 to 4
move 2 from 3 to 7
move 1 from 5 to 7
move 3 from 8 to 4
move 2 from 7 to 2
move 1 from 3 to 8
move 9 from 2 to 6
move 2 from 8 to 6
move 1 from 7 to 3
move 1 from 3 to 5
move 3 from 6 to 8
move 1 from 8 to 5
move 1 from 5 to 9
move 1 from 1 to 2
move 5 from 4 to 6
move 10 from 6 to 2
move 5 from 2 to 6
move 5 from 6 to 4
move 1 from 6 to 3
move 6 from 4 to 6
move 3 from 2 to 6
move 2 from 2 to 3
move 11 from 4 to 6
move 1 from 9 to 5
move 4 from 6 to 7
move 1 from 4 to 3
move 12 from 4 to 3
move 1 from 8 to 6
move 9 from 5 to 7
move 1 from 5 to 2
move 1 from 8 to 5
move 1 from 4 to 9
move 9 from 7 to 9
move 1 from 3 to 4
move 2 from 3 to 6
move 2 from 5 to 6
move 2 from 8 to 5
move 11 from 3 to 4
move 2 from 3 to 1
move 1 from 2 to 3
move 1 from 3 to 8
move 3 from 7 to 9
move 5 from 4 to 2
move 2 from 5 to 8
move 6 from 4 to 2
move 1 from 1 to 3
move 12 from 9 to 1
move 6 from 1 to 6
move 1 from 8 to 4
move 1 from 8 to 3
move 5 from 2 to 7
move 2 from 3 to 9
move 5 from 7 to 1
move 1 from 7 to 5
move 2 from 9 to 1
move 14 from 1 to 7
move 2 from 4 to 7
move 7 from 2 to 4
move 1 from 2 to 1
move 1 from 1 to 3
move 1 from 5 to 4
move 1 from 9 to 6
move 16 from 6 to 5
move 2 from 5 to 4
move 12 from 6 to 8
move 10 from 4 to 8
move 9 from 7 to 3
move 4 from 7 to 6
move 11 from 5 to 8
move 2 from 5 to 2
move 14 from 8 to 9
move 1 from 5 to 1
move 3 from 9 to 4
move 2 from 2 to 1
move 7 from 8 to 3
move 6 from 3 to 5
move 8 from 9 to 8
move 1 from 6 to 1
move 1 from 4 to 2
move 4 from 3 to 8
move 1 from 7 to 2
move 3 from 1 to 5
move 2 from 5 to 7
move 3 from 9 to 2
move 1 from 1 to 8
move 5 from 5 to 4
move 2 from 7 to 8
move 4 from 2 to 5
move 1 from 2 to 4
move 2 from 7 to 8
move 4 from 6 to 2
move 6 from 5 to 3
move 1 from 6 to 5
move 1 from 5 to 3
move 1 from 3 to 8
move 8 from 8 to 3
move 9 from 8 to 5
move 9 from 8 to 2
move 2 from 8 to 9
move 2 from 3 to 8
move 5 from 5 to 8
move 1 from 3 to 7
move 2 from 9 to 5
move 7 from 2 to 4
move 14 from 4 to 6
move 2 from 2 to 7
move 1 from 7 to 3
move 1 from 7 to 9
move 3 from 5 to 2
move 1 from 7 to 1
move 3 from 2 to 4
move 7 from 8 to 2
move 3 from 6 to 1
move 17 from 3 to 1
move 2 from 8 to 3
move 6 from 2 to 7
move 2 from 7 to 9
move 3 from 6 to 8
move 2 from 8 to 6
move 4 from 2 to 1
move 3 from 4 to 7
move 1 from 8 to 7
move 1 from 8 to 9
move 1 from 4 to 2
move 3 from 5 to 7
move 2 from 3 to 1
move 2 from 3 to 5
move 5 from 7 to 4
move 5 from 7 to 3
move 1 from 4 to 8
move 3 from 3 to 1
move 6 from 1 to 3
move 1 from 7 to 5
move 2 from 9 to 2
move 3 from 5 to 8
move 1 from 8 to 1
move 8 from 3 to 5
move 1 from 4 to 9
move 3 from 6 to 5
move 3 from 6 to 3
move 2 from 3 to 7
move 1 from 4 to 7
move 3 from 6 to 4
move 2 from 7 to 2
move 1 from 7 to 8
move 2 from 5 to 4
move 1 from 6 to 1
move 7 from 4 to 7
move 7 from 5 to 2
move 10 from 2 to 3
move 3 from 2 to 6
move 3 from 8 to 1
move 1 from 8 to 7
move 2 from 6 to 3
move 1 from 6 to 9
move 4 from 7 to 5
move 16 from 1 to 5
move 1 from 9 to 7
move 3 from 7 to 6
move 11 from 5 to 6
move 2 from 7 to 9
move 12 from 6 to 4
move 2 from 6 to 9
move 6 from 3 to 2
move 1 from 5 to 7
move 5 from 9 to 5
move 1 from 9 to 6
move 4 from 3 to 7
move 1 from 4 to 2
move 7 from 2 to 5
move 3 from 5 to 2
move 6 from 5 to 6
move 3 from 2 to 6
move 9 from 6 to 8
move 5 from 5 to 9
move 5 from 7 to 1
move 4 from 1 to 9
move 2 from 9 to 4
move 1 from 6 to 7
move 9 from 4 to 1
move 7 from 5 to 9
move 18 from 1 to 3
move 9 from 9 to 5
move 8 from 8 to 2
move 1 from 2 to 5
move 4 from 2 to 3
move 4 from 9 to 6
move 1 from 4 to 8
move 2 from 5 to 7
move 2 from 9 to 2
move 10 from 3 to 9
move 5 from 5 to 9
move 1 from 7 to 2
move 2 from 8 to 7
move 2 from 3 to 5
move 2 from 9 to 1
move 2 from 7 to 3
move 1 from 2 to 1
move 5 from 5 to 8
move 1 from 2 to 1
move 15 from 3 to 6
move 1 from 7 to 6
move 10 from 6 to 5
move 1 from 7 to 8
move 4 from 1 to 6
move 1 from 8 to 3
move 2 from 1 to 5
move 3 from 8 to 1
move 1 from 4 to 6
move 1 from 4 to 2
move 4 from 9 to 7
move 6 from 5 to 7
move 3 from 1 to 9
move 10 from 6 to 8
move 2 from 1 to 3
move 8 from 7 to 9
move 1 from 9 to 6
move 2 from 7 to 9
move 3 from 3 to 5
move 1 from 2 to 6
move 2 from 6 to 5
move 5 from 9 to 4
move 4 from 8 to 2
move 1 from 1 to 3
move 4 from 5 to 9
move 3 from 6 to 1
move 2 from 1 to 5
move 3 from 5 to 2
move 8 from 8 to 3
move 11 from 9 to 4
move 13 from 4 to 8
move 2 from 9 to 2
move 2 from 3 to 1
move 1 from 4 to 1
move 1 from 3 to 8
move 2 from 6 to 9
move 7 from 8 to 1
move 3 from 2 to 5
move 7 from 2 to 5
move 3 from 4 to 6
move 4 from 9 to 2
move 2 from 3 to 5
move 9 from 5 to 6
move 5 from 2 to 7
move 2 from 9 to 2
move 2 from 9 to 7
move 12 from 6 to 8
move 5 from 5 to 7
move 1 from 9 to 8
move 3 from 1 to 6
move 5 from 5 to 8
move 6 from 1 to 9
move 2 from 1 to 5
move 1 from 6 to 9
move 5 from 9 to 7
move 2 from 5 to 8
move 11 from 7 to 6
move 20 from 8 to 1
move 2 from 9 to 8
move 4 from 7 to 6
move 6 from 8 to 3
move 13 from 6 to 9
move 4 from 3 to 2
move 4 from 6 to 3
move 2 from 3 to 6
move 5 from 9 to 8
move 2 from 7 to 1
move 2 from 6 to 9
move 6 from 8 to 3
move 6 from 3 to 6
move 5 from 2 to 9
move 22 from 1 to 3
move 3 from 2 to 1
move 5 from 9 to 3
move 1 from 1 to 6
move 3 from 6 to 2
move 1 from 2 to 4
move 33 from 3 to 5
move 1 from 8 to 7
""".trimIndent()