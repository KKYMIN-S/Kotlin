/* Homework 3.2.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 3.
 * 정렬 알고리즘 성능 비교
 */

import java.util.Scanner
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun shuffleBigIntArray(array: IntArray) {
    val size = array.size
    var j: Int
    var temp: Int
    // shuffle
    for (i in 0 until size) {
        j = (Math.random() * size).toInt()
        if (j == i)
            continue
        temp = array[i]
        array[i] = array[j]
        array[j] = temp
    }
}
fun insertion_sort(array: IntArray) {
    var temp: Int
    var i: Int
    var k: Int = 1
    val size = array.size

    while (k < size) {
        temp = array[k]
        i = k
        while (i > 0 && array[i-1] >= temp) {
            array[i] = array[i-1]
            --i
        }
        array[i] = temp
        k++
    }
}
fun selection_sort(array: IntArray) {
    var temp: Int
    var min_index: Int
    val size = array.size
    for (i in 0 until size-1) {
        min_index = i
        for (j in i+1 until size) {
            if (array[min_index] > array[j]) {
                min_index = j
            }
        }
        if (min_index != i) {
            temp = array[i]
            array[i] = array[min_index]
            array[min_index] = temp
        }
    }
}
fun main() {
    val cin = Scanner(System.`in`)
    var big_size: Int
    val test_sizes = intArrayOf(10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000)
    val offset = 0
    var bigRandIntArray: IntArray
    var tns_quick : Long
    var tns_insert : Long
    var tns_select : Long
    System.out.printf("Comparisons of Sorting Algorithms (measured time in milli-seconds)\n")
    System.out.printf("%10s %10s %10s %10s\n", "test_size", "quick_sort", "insert_sort", "select_sort")
    for (test_size in test_sizes) {
        bigRandIntArray = genBigRandIntArray(test_size, offset);
        tns_quick = measureTimeMillis { quickSort(bigRandIntArray) }
        shuffleBigIntArray(bigRandIntArray)
        tns_insert = measureTimeMillis { insertion_sort(bigRandIntArray) }
        shuffleBigIntArray(bigRandIntArray)
        tns_select = measureTimeMillis { selection_sort(bigRandIntArray) }
        System.out.printf("%10d %10d %10d %10d\n", test_size, tns_quick, tns_insert, tns_select)
    }
}