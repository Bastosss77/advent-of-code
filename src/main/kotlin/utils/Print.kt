package utils

fun printResult(dayCount: Int, partNumber: Int = 1, result: Any) {
    println("    Day $dayCount part $partNumber")
    println("--------------------------------------------------")
    println("")
    println("Result is : $result")
    println("")
    println("--------------------------------------------------")
    println("")
    println("")
    println("")
}

fun printResult(dayCount: Int, results: List<Any>) {
    results.forEachIndexed { index, result ->
        printResult(dayCount, index + 1, result)
    }
}