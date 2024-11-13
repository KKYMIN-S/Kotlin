/* Kotlin Project Homework 3.4
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 17.
 * 순차 탐색과 이진 탐색의 성능 비교
 */

import java.util.Scanner
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun genBigRandIntArray(size: Int, offset: Int): IntArray {
    val bigIntArray = IntArray(size)
    var j: Int
    var temp: Int
    for (i in 0 until size)
        bigIntArray[i] = i + offset
    // shuffle
    for (i in 0 until size) {
        j = (Math.random() * size).toInt()
        if (j == i)
            continue
        temp = bigIntArray[i]
        bigIntArray[i] = bigIntArray[j]
        bigIntArray[j] = temp
    }
    return bigIntArray
}
fun printArraySample(bigArray: IntArray, per_line: Int, sample_lines: Int) {
    var last_block_start: Int
    val size = bigArray.size
    var count = 0
    for (i in 0 until sample_lines) {
        for (j in 0 until per_line) {
            if (count > size) {
                println()
                return
            }
            print("%8d ".format(bigArray[count]))
            count++
        }
        println()
    }
    if (count < size - per_line * sample_lines) {
        count = size - per_line * sample_lines
    }
    print(" . . . . . \n")
    for (i in 0 until sample_lines) {
        for (j in 0 until per_line) {
            if (count > size) {
                println()
                return
            }
            print("%8d ".format(bigArray[count]))
            count++
        }
        println()
    }
    println()
}
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
fun _partition(array: IntArray, left: Int, right: Int, pivotIndex: Int): Int {
    val pivotValue: Int // pivot value
    var newPI: Int // new pivot value
    var temp: Int
    var i: Int
    pivotValue = array[pivotIndex]
    temp = array[pivotIndex]
    array[pivotIndex] = array[right]
    array[right] = temp // Move pivot to end
    newPI = left
    i = left
    while (i <= right - 1) {
        if (array[i] <= pivotValue) {
            temp = array[i]
            array[i] = array[newPI]
            array[newPI] = temp
            newPI = newPI + 1
        }
        i++
    }
    // swap
    temp = array[newPI]
    array[newPI] = array[right]
    array[right] = temp
    return newPI
}
fun _quickSort(array: IntArray, left: Int, right: Int) {
    var pI : Int
    var newPI: Int // pivot index

    if (left >= right)
        return
    pI = (left + right) / 2
    newPI = _partition(array, left, right, pI)

    if (left < (newPI - 1)) {
        _quickSort(array, left, newPI - 1)
    }
    if ((newPI + 1) < right) {
        _quickSort(array, newPI + 1, right)
    }
}
fun quickSort(array: IntArray) {
    var size = array.size
    _quickSort(array, 0, size-1)
}
fun sequential_search(array: IntArray, key_to_search : Int) : Int { // 순차 탐색
    val size = array.size
    for (i in 0..< size) {
        if (array[i] == key_to_search)
            return i
    }
    return -1
}
fun binary_search(array: IntArray, key_to_search: Int): Int { // 이진 탐색
    val size = array.size
    var low: Int
    var high: Int
    var mid: Int
    var loop = 0

    if (key_to_search > array[size - 1]) {
        println("Binary_Search :: given key (%d) is beyond the maximum value of the array (%d)".format(key_to_search, array[size - 1] ))
        return -1
    }
    low = 0
    high = size - 1
    loop++
    while (low <= high) {
        // println("%2d-th loop: Search range: [%2d ~ %2d]".format(loop, low, high))
        mid = (low + high) / 2
        if (key_to_search === array[mid])
            return mid
        else if (key_to_search < array[mid]) high = mid - 1
        else
            low = mid + 1
        loop++
    }
    return -1
}

fun main(args: Array<String>) {
    val array_size : Int = 1000000
    val offset : Int = 0
    var bigRandIntArray: IntArray
    var per_line : Int = 10
    var num_lines : Int = 2
    var pos : Int
    var tms_sequentialSearch : Long
    var total_tms_sequentialSearch : Long = 0

    bigRandIntArray = genBigRandIntArray(array_size, offset);
    println("Random array to be searched (size = %d)".format(array_size))
    printArraySample(bigRandIntArray, per_line, num_lines);
    for (key_to_search in 0 until array_size) {
        tms_sequentialSearch = measureTimeMillis {
            pos = sequential_search(bigRandIntArray, key_to_search);
        }
        if (pos == -1) {
            println("Error in sequential search !!!")
            break
        }
        total_tms_sequentialSearch += tms_sequentialSearch
    }
    println("Total elapsed time for quential search of array (size = %d) = %d milli_sec".
    format(array_size, total_tms_sequentialSearch))
    println("Average time for sequential search of array (size = %d) = %d nano_sec".
    format(array_size, total_tms_sequentialSearch*1000000/array_size))

    var tms_quickSort : Long
    tms_quickSort = measureTimeMillis { quickSort(bigRandIntArray) }
    println("Sorted Array (size = %d)".format(array_size))
    printArraySample(bigRandIntArray, per_line, num_lines)
    println("Elapsed time for quickSort of array (size = %d) = %d milli_sec".
    format(array_size, tms_quickSort))
    var tms_binarySearch : Long
    var total_tms_binarySearch : Long = 0
    for (key_to_search in 0 until array_size) {
        tms_binarySearch = measureTimeMillis {
            pos = binary_search(bigRandIntArray, key_to_search);
        }
        if (pos == -1) {
            println("Error in binary search !!!")
            break
        }
        total_tms_binarySearch += tms_binarySearch
    }
    println("Total elapsed time for binary search of array (size = %d) = %d milli_sec".
    format(array_size, total_tms_binarySearch))
    println("Average time for binary search of array (size = %d) = %d nano_sec".
    format(array_size, total_tms_binarySearch*1000000/array_size))
}

