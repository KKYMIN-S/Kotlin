/* Kotlin Project Homework 2.2
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 11.
 * 직각 삼각형의 밑변 (base)와 높이 (height)를 입력받고, 대각선 길이 및 넓이의 계산, 출력
 */

import java.util.Scanner
import kotlin.math.*

fun main(args: Array<String>) {
    val cin = Scanner(System.`in`)
    var base: Double // 밑변
    var height: Double // 높이
    var hypotenus: Double // 대각선
    var area: Double // 넓이

    while (true) {
        print("Input base and height of right triangle : ")
        base = cin.nextDouble()
        height = cin.nextDouble()

        if (base == 0.0 && height == 0.0)
            break

        hypotenus = sqrt(base*base + height*height)
        area = base * height / 2
        println("base (%.2f), height (%.2f) => hypotenus (%.2f), area (%.2f)".format(base, height, hypotenus, area))
    }
}