package advent_2024.puzzles.Day1

import advent_2024.puzzles.input
import utils.printResult
import kotlin.math.absoluteValue

object Day1Part1 {

    operator fun invoke() {
        val lists = parseInput(input)

        val result = lists.fold(0) { acc, next ->
            acc + (next.first - next.second).absoluteValue
        }

        printResult(1, 1, result)
    }

    private fun parseInput(input: String) : List<Pair<Int, Int>> {
        val firstList = mutableListOf<Int>()
        val secondList = mutableListOf<Int>()

        input.lines().forEach { line ->
            val values = line.split("\\s{3}".toRegex())

            firstList.add(values[0].toInt())
            secondList.add(values[1].toInt())
        }

        firstList.sort()
        secondList.sort()

        return firstList.zip(secondList)
    }
}