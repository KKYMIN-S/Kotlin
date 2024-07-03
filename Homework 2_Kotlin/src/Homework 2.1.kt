/* Homework 2.1.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 2.
 * 정수 데이터 입력, 정렬, 역순 출력
 */

import kotlin.math.sqrt
import java.util.Scanner

fun printArray(arr: IntArray) {
    val size = arr.size
    for (i in 0..size-1) {
        print("%3d".format(arr[i]))
    }
    println()
}

fun insertionSort(arr: IntArray) {
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

fun reverseSort(arr: IntArray) {
   val size = arr.size
    for (i in 0..size-1) {
        val key = arr[i]
        var j = i
        while (j > 0) {
            if (arr[j-1] > key)
                break
            arr[j] = arr[j-1]
            j--
        }
        arr[j] = key
    }
}

fun Statistics(arr: IntArray) {
    val size = arr.size
    var sum: Double = 0.0
    var min: Int = 0
    var max: Int = 0
    var avg: Double = 0.0
    var variance: Double = 0.0
    var std: Double = 0.0

    for (i in 0..size-1) { // min (최소)
        min = arr[0]
        if (arr[i] < min)
            min = arr[i]
    }

    for (i in 0..size-1) { // max (최대)
        max = arr[0]
        if (arr[i] > max)
            max = arr[i]
    }

    for (i in 0..size-1) { // avg (평균)
        sum += arr[i]
    }
    avg = sum / size

    for (i in 0..size-1) { // var (분산)
        variance += (arr[i] - avg) * (arr[i] - avg)
    }
    variance = variance / size

    std = sqrt(variance) // std (표준편차)

    print("Statistics : Statistics of the array : min(%d), max(%d), avg(%.3f), var(%.3f), std(%.3f)".format(min, max, avg, variance, std))
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
    reverseSort(intArray)
    print("In reverse order : ")
    printArray(intArray)
    Statistics(intArray)
}