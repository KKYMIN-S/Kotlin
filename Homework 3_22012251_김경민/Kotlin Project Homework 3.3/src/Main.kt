/* Kotlin Project Homework 3.3
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 17.
 * 정수 배열 크기가 10 ~ 1000인 경우에 대한 정렬 알고리즘 성능 비교
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

fun main() {
    val cin = Scanner(System.`in`)
    var big_size: Int
    val test_sizes = intArrayOf(10, 20, 30, 40, 50, 70, 100, 500, 1000, 5000, 10000, 50000, 100000)
    val offset = 0
    var bigRandIntArray: IntArray
    var tns_quick : Long
    var tns_merge : Long
    var tns_insert : Long
    var tns_select : Long

    println("Comparisons of Sorting Algorithms (measured time in nano-seconds)")
    println("%10s %10s %10s %10s %10s".format("test_size", "quick_sort", "merge_sort", "insert_sort", "select_sort"))
    for (test_size in test_sizes) {
        bigRandIntArray = genBigRandIntArray(test_size, offset);
        tns_quick = measureNanoTime { quickSort(bigRandIntArray) }
        shuffleBigIntArray(bigRandIntArray)
        tns_merge = measureNanoTime { mergeSort(bigRandIntArray) }
        shuffleBigIntArray(bigRandIntArray)
        tns_insert = measureNanoTime { insertion_sort(bigRandIntArray) }
        shuffleBigIntArray(bigRandIntArray)
        tns_select = measureNanoTime { selection_sort(bigRandIntArray) }
        println("%10d %10d %10d %10d %10d".format(test_size, tns_quick,
            tns_merge, tns_insert, tns_select))
    }
}