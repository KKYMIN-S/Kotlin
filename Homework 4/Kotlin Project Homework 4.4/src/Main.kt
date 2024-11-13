/* Kotlin Project Homework 4.4
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 20.
 * class Time (var hh: Int, var mm: Int, var ss: Int): Comparable<Time>
 */

import Class_Time.*
import java.util.*
import kotlin.text.Typography.times

fun main() {
    val times : Array<Time> =
        arrayOf(Time(12, 59, 59), Time(1, 2, 3), Time(0, 0, 0),
            Time(23, 59, 59), Time(15, 30, 59), Time(3, 0, 0))
    println("Before sorting :")
    printTimes(times)
    Arrays.sort(times)
    println("After sorting :")
    printTimes(times)
}