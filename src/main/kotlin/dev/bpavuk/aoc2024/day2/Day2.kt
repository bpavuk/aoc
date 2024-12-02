package dev.bpavuk.dev.bpavuk.aoc2024.day2

fun day2stage1(): Int = inputs.readLines().count { string ->
    val numReport = string.split(" ").map { s -> s.toInt() }
    checkSafe(numReport)
}

fun day2stage2(): Int = inputs.readLines().count { string ->
    val numReport = string.split(" ").map { s -> s.toInt() }
    val safe = checkSafe(numReport)
    if (!safe) {
        debuggable(numReport)
    } else true
}

fun checkSafe(report: List<Int>): Boolean {
    val diffs = mutableListOf<Int>()
    for (i in 0..report.lastIndex - 1) {
        diffs.add(report[i] - report[i + 1])
    }
    return diffs.all { i -> (i in -3..-1) } || diffs.all { i -> (i in 1..3) }
}

fun debuggable(report: List<Int>): Boolean {
    val diffs = mutableListOf<Int>()
    for (i in 0..report.lastIndex - 1) {
        diffs.add(report[i] - report[i + 1])
    }
    val netPositive = diffs.count { it > 0 } > diffs.size / 2
    val strays = if (netPositive) {
        // find stray negative, or out-of-bounds value
        report.filter { i -> i < 0 || i !in -3..3 || i == 0 }
    } else {
        // find stray positive, or out-of-bounds value
        report.filter { i -> i > 0 || i !in -3..3 || i == 0 }
    }
    val strayIds = strays.map { i -> report.indexOf(i) }
    val attemptStray = strayIds.any { strayIdx ->
        checkSafe(report.slice(0 until strayIdx) + report.slice(strayIdx + 1..report.lastIndex)) || checkSafe(
            report.slice(
                0..strayIdx
            ) + report.slice(strayIdx + 2..report.lastIndex) // there were some rare cases where this was actually helpful
        )
    }
    return attemptStray
}
