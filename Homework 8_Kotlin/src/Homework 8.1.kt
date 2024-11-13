/* Homework 8.1.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 8.
 * 동적프로그래밍 기반 배낭문제, 파일 입출력
 */

import Knapsack01.*
import java.io.File
import java.util.Scanner

fun main(args: Array<String>) {
    var sol : KSP_Solution?
    val knapsack_capacity = 20
    val f_items = "KSP_items.txt"
    val fin = Scanner(File(f_items))
    var kp = Knapsack01(knapsack_capacity, fin)
    println("Knapsack01 (capacity = %d) is initialized for %d items".format(knapsack_capacity, kp.num_items))
    fin.close()
    println("\nProcessing to obtain best solution by bruteforce() : ")
    sol = kp.BruteForce_KP01();
    println("Solution obtained by bruteforce() : ")
    kp.printSolution(sol)
    println("\nProcessing to obtain best solution by DynKSP_BottomUp() : ")
    sol = kp.DynKSP_BottomUp()
    println("Solution obtained by DynKSP_BottomUp() : ")
    kp.printSolution(sol)
    println("\nProcessing to obtain best solution by DynKSP_TopDown() : ")
    sol = kp.DynKSP_TopDown();
    println("Solution obtained by DynKSP_TopDown() : ")
    kp.printSolution(sol);
}
