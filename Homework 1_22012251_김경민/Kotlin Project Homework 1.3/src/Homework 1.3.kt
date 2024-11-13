/* Homework 1.3.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 4.
 * 정수 배열의 입력, 최대, 최소, 출력
 */

import java.util.Scanner

fun printArray(arr: IntArray) {
    val size = arr.size
    for (i in 0..size-1) {
        print("%3d".format(arr[i]))
    }
    println()
}

fun max(arr: IntArray): Int { // 최대
    var max = arr[0]
    for (i in 0..arr.size - 1) {
        if (max < arr[i])
            max = arr[i]
    }
    return max
}

fun min(arr: IntArray): Int { // 최소
    var min = arr[0]
    for (i in 0..arr.size - 1) {
        if (min > arr[i])
            min = arr[i]
    }
    return min
}

fun main(args: Array<String>) {
    val intArray = IntArray(10)
    val cin = Scanner(System.`in`)
    var data: Int
    print("Enter 10 integers : ")
    for (i in 0..9) {
        data = cin.nextInt()
        intArray[i] =  data
    }
    print("Input data array : ")
    printArray(intArray)
    print("Max = %d, Min = %d".format(intArray.max(), intArray.min()))
}