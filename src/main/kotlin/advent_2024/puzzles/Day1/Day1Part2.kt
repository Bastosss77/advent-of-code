package advent_2024.puzzles.Day1

import advent_2024.puzzles.input
import utils.printResult

object Day1Part2 {

    operator fun invoke() {
        val lists = parseInput(input)

        val result = lists.first.fold(0) { acc, next ->
            acc + lists.second.count { it == next } * next
        }

        printResult(1, 2, result)
    }

    private fun parseInput(input: String) : Pair<List<Int>, List<Int>> {
        val firstList = mutableListOf<Int>()
        val secondList = mutableListOf<Int>()

        input.lines().forEach { line ->
            val values = line.split("\\s{3}".toRegex())

            firstList.add(values[0].toInt())
            secondList.add(values[1].toInt())
        }

        return firstList to secondList
    }
}