/* Homework 1.2.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 4.
 * 정수의 배열의 입력, Insertion Sort(삽입 정렬), 출력
 */

import java.util.Scanner

fun printArray(arr: IntArray) {
    val size = arr.size
    for (i in 0..size-1) {
        print("%3d".format(arr[i]))
    }
    println()
}

fun insertionSort(arr: IntArray) { // 삽입 정렬
    val size = arr.size
    for (i in 0..size-1) {
        val key = arr[i]
        var j = i
        while (j > 0) {
            if (arr[j-1] < key)
                break
            arr[j] = arr[j-1]
            j--
        }
        arr[j] = key
    }
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
    print("Input data before sorting : ")
    printArray(intArray)
    insertionSort(intArray)
    print("Input data after sorting : ")
    printArray(intArray)
}