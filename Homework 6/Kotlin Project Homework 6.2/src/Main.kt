/* Kotlin Project Homework 6.2
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 3.
 * TreeMap<String, Person> 기반 주소록 (address book) 구현
 */

import java.util.*

fun main(args: Array<String>) {
    val setA: MutableSet<String> = mutableSetOf("A", "B", "C", "D", "E", "F") // set A 정의
    val setB: MutableSet<String> = mutableSetOf("B", "C", "F", "G") // set B 정의

    println("set_A = $setA") // Set A 결과 출력
    println("set_B = $setB") // Set B 결과 출력

    // 합집합 (union) 연산
    val union: MutableSet<String> = HashSet(setA) // set A의 모든 요소를 복사하여 새로운 HashSet 생성
    union.addAll(setB)
    println("union(set_A, set_B) = $union")

    // 차집합 (difference) 연산
    val difference: MutableSet<String> = HashSet(setA)
    difference.removeAll(setB)
    println("difference(set_A, set_B) = $difference")

    // 교집합 (intersection) 연산
    val intersection: MutableSet<String> = HashSet(setA)
    intersection.retainAll(setB)
    println("intersection(set_A, set_B) = $intersection")
}