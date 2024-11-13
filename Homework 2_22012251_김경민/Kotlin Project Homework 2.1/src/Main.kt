/* Kotlin Project Homework 2.1
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 11.
 * 원의 반지름 입력, 원 둘레 및 원 면적 계산 및 출력
 */

import java.util.Scanner

fun main(args: Array<String>) {
    val cin = Scanner(System.`in`)
    val PI = 3.141592
    var radius: Double // 반지름
    var area: Double // 원 면적
    var circumference: Double // 원 둘레

    while (true) {
        print("Input radius of a circle  (0 to quit) : ")
        radius = cin.nextDouble()

        if (radius == 0.0)
            break
        area = PI * radius * radius
        circumference = 2 * PI * radius
        println("radius = %.1f : area = %.2f, circumference = %.4f".format(radius, area, circumference))
    }
}