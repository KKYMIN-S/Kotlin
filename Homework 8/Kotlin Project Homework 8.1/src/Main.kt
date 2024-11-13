/* Kotlin Project Homework 8.1
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 18.
 * 동적프로그래밍 기반 배낭문제, 파일 입출력
 */

import Knapsack01.*
import java.io.File
import java.util.Scanner

fun main(args: Array<String>) {
    var sol: KSP_Solution? // 배낭 문제의 해답을 저장할 변수
    val knapsack_capacity = 20 // 배낭의 용량 정의
    val f_items = "KSP_items.txt"
    val fin = Scanner(File(f_items))
    var kp = Knapsack01(knapsack_capacity,fin)
    println("Knapsack01 (capacity = %d) is initialized for %d items".format(knapsack_capacity, kp.num_items))
    fin.close()

    // 브루트 포스 방법으로 배낭 문제 해결
    println("\nProcessing to obtain best solution by bruteforce() : ")
    sol = kp.BruteForce_KP01()
    //해답 출력
    println("Solution obtained by bruteforce() : ")
    kp.printSolution(sol)

    // 동적 프로그래밍 Bottom-up 접근 방식으로 배낭 문제 해결
    println("\nProcessing to obtain best solution by DynKSP_BottomUp() : ")
    sol = kp.DynKSP_BottomUp()
    println("Solution obtained by DynKSP_BottomUp() : ")
    kp.printSolution(sol)

    // 동적 프로그래밍 Top-down 접근 방식으로 배낭 문제 해결
    println("\nProcessing to obtain best solution by DynKSP_TopDown() : ")
    sol = kp.DynKSP_TopDown()
    println("Solution obtained by DynKSP_TopDown() : ")
    kp.printSolution(sol)
}