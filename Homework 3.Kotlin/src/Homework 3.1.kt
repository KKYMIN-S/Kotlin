/* Homework 3.1.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 3.
 * BigRandIntArray 생성, 퀵정렬, 샘플 출력
 */

import java.util.Scanner

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
fun printBigArraySample(bigArray: IntArray, per_line: Int, sample_lines: Int) {
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
    print("\n . . . . . \n")
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

fun main(args: Array<String>) {
    val cin = Scanner(System.`in`)
    var big_size: Int
    val offset = 0
    var bigRandIntArray: IntArray
    while (true) {
        print("Input big_size (> 32767) to generate non-duplicated random big integer array (0 to terminate) : ")
        big_size = cin.nextInt()
        if (big_size == 0)
            break
        bigRandIntArray = genBigRandIntArray(big_size, offset)
        System.out.printf("Before sorting, size = %d, offset = %d\n", big_size, offset)
        printBigArraySample(bigRandIntArray, 10, 2)
        quickSort(bigRandIntArray)
        System.out.printf("After sorting, size = %d, offset = %d\n", big_size, offset)
        printBigArraySample(bigRandIntArray, 10, 2)
    }
}