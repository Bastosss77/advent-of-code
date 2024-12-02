package advent_2024.puzzles.Day1

import advent_2024.puzzles.input
import utils.printResult
import kotlin.math.absoluteValue

object Day1Optimized {

    operator fun invoke() {
        val list = parseInput(input)

        val resultPart1 = (list.map { it.first }.sorted() zip list.map { it.second }.sorted())
            .sumOf { (it.first - it.second).absoluteValue }

        val count = list.map { it.second }.groupingBy { it }.eachCount()
        val resultPart2 = list.map { it.first }.sumOf { it * (count[it] ?: 0) }

        check(resultPart1 == 2375403)
        check(resultPart2 == 23082277)

        printResult(1, listOf(resultPart1, resultPart2))
    }

    private fun parseInput(input: String) =
        input.lines()
            .map { line -> line.split("   ").map { it.toInt() }}
            .map { it.first() to it.last() }
}