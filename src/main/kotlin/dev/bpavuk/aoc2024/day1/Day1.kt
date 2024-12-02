package dev.bpavuk.aoc2024.day1

import kotlin.math.abs

fun day1stage1(): Int = inputs.readLines().map { str ->
    str.split("   ").run { get(0).toInt() to get(1).toInt() }
}.run {
    map { pair -> pair.first }.sorted().zip(map { pair -> pair.second }.sorted())
        .map { pair -> abs(pair.first - pair.second) }
}.reduce { acc, i -> acc + i }

fun day1stage2(): Long = inputs.readLines().map { str ->
    str.split("   ").run { get(0).toLong() to get(1).toLong() }
}.unzip().let { (left, right) ->
    left.sumOf { n -> right.count { i -> n == i } * n }
}
