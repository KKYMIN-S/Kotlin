/* Homework 1.5.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 10.
 * Fibonacci 수열 계산 프로그램 작성
 */

import java.util.*

fun Fibo(n: Int): Int {
    if (n <= 1)
        return n
    else
        return Fibo(n-1) + Fibo(n-2)
}

fun main(args: Array<String>) {
    val cin = Scanner(System.`in`)
    print("Input n to generate Fibonacci series (0 ~ n) : ")
    val n = cin.nextInt()
    var fibo_i : Int
    for (i in 0..n) {
        fibo_i = Fibo(i)
        print("Fibo(%3d) = %20d\n".format(i, fibo_i))
    }
    cin.close()
}