package advent_2022

import advent_2022.puzzles.day1
import advent_2022.puzzles.day2
import advent_2022.puzzles.day3
import advent_2022.puzzles.day4
import advent_2022.puzzles.day6
import advent_2022.puzzles.day7

fun main(args: Array<String>) {
    puzzle { day1() }
    puzzle { day2() }
    puzzle { day3() }
    puzzle { day4() }
   //puzzle { day5() }
    puzzle { day6() }
    puzzle { day7() }
}

private fun puzzle(puzzle: () -> Unit) {
    puzzle()
    println()
}