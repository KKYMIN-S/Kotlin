/* Kotlin Project Homework 2.4
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 11.
 * 곱셈 표 출력
 */

import java.util.Scanner

fun main(args: Array<String>) {
    for (i in 1..9) {
        for (j in 1..9) {
            print("%2d x %2d = %2d ".format(i, j, i*j))
        }
        println()
    }
}