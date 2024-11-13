/* Homework 1.4.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 10.
 * 각도 (degree) 0° ~ 360°의 라디안 (radian), sin(), cos(), tan() 값 계산 및 출력
 */

import kotlin.math.*

fun main(args: Array<String>) {
    println("%6s %11s %11s %11s %11s".format("Deg", "Rad", "sin()", "cos()", "tan()"))
    for (degree in 0..360 step 30) {
        val degreeDouble = degree.toDouble() // Double 자료형으로 변환
        val radian = Math.toRadians(degree.toDouble())
        val sinValue = sin(radian)
        val cosValue = cos(radian)
        val tanValue = tan(radian)

        println("%6.2f  %10.2f  %10.2f  %10.2f  %10.2f".format(degreeDouble, radian, sinValue, cosValue, tanValue))
    }
}