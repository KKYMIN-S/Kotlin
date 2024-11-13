/* Homework 1.1.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 4.
 * Double 자료형의 데이터의 입력, 사칙 연신 및 결과 출력
 */

import java.util.Scanner

fun main(args: Array<String>) {
    val cin = Scanner(System.`in`) // 불변 변수
    print("Input two double data : ")
    val x = cin.nextDouble() // 가변 변수
    val y = cin.nextDouble() // 가변 변수

    // 결과 출력
    println("x = %7.6f, y = %7.6f".format(x, y))
    println("x + y = %7.6f".format(x + y))
    println("x - y = %7.6f".format(x - y))
    println("x * y = %7.6f".format(x * y))
    println("x / y = %7.6f".format(x / y))
}