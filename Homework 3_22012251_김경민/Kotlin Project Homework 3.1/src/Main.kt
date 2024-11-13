/* Kotlin Project Homework 3.1
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 17.
 * BigRandIntArray 생성, 병합 정렬 (mergeSort), 샘플 출력
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
fun _mergeSort(array : IntArray, tmp_array : IntArray, left: Int, right: Int) {
    if (left < right) {
        val mid = (left + right) / 2
        _mergeSort(array, tmp_array, left, mid)        // 왼쪽 절반 정렬
        _mergeSort(array, tmp_array, mid + 1, right)   // 오른쪽 절반 정렬

        // 병합 과정 추가
        var i = left
        var j = mid + 1
        var k = left

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                tmp_array[k++] = array[i++]
            } else {
                tmp_array[k++] = array[j++]
            }
        }
        while (i <= mid) {
            tmp_array[k++] = array[i++]
        }
        while (j <= right) {
            tmp_array[k++] = array[j++]
        }
        for (i in left..right) {
            array[i] = tmp_array[i]
        }
    }
}
fun mergeSort(array : IntArray) {
    var tmp_array = array.copyOf()
    _mergeSort(array, tmp_array, 0, array.size-1);
}

fun main(args: Array<String>) {
    val cin = Scanner(System.`in`)
    var bigRandIntArray: IntArray
    var arraySize: Int
    val offset = 0
    val per_line = 10
    val sample_lines = 2
    while (true) {
        print("Input big array size (> 32767) to generate non-duplicated random big array (0 to terminate) : ")
        arraySize = cin.nextInt()
        if (arraySize == 0)
            break
        bigRandIntArray = genBigRandIntArray(arraySize, offset);
        println("Before mergeSort() :")
        printBigArraySample(bigRandIntArray, per_line, sample_lines)
        mergeSort(bigRandIntArray)
        println("After mergeSort() :")
        printBigArraySample(bigRandIntArray, per_line, sample_lines)
    }
}