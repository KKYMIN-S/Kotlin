/* Kotlin Project Homework 6.3
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 3.
 * TreeMap<String, Person> 기반 주소록 (address book) 구현
 */

import java.util.*
import MyKotlinClasses.*

fun main(args: Array<String>) {
    val addrBook: TreeMap<String, Person> = TreeMap<String, Person>() // TreeMap을 사용하여 이름을 기준으로 Person 객체 저장
    val persons: Array<Person> = arrayOf<Person>(
        Person("Kim", TelNum(82, 53, 810, 1000)),
        Person("Yoon", TelNum(82, 2, 1234, 5678)),
        Person("Lee", TelNum(82, 51, 2579, 1234)),
        Person("Park", TelNum(82, 53, 1000, 5678)),
        Person("Choi", TelNum(82, 31, 2345, 6789))
    )
    for (p in persons) {
        addrBook[p.get_name()] = p  // Person의 이름을 Key로 하여 TreeMap에 Person 객체 추가
    }

    print("Persons in addrBook = \n")
    for (p in persons) {
        val nm: String = p.get_name()
        print("%5s : %s\n".format(nm, addrBook[nm]))
    }
    val keySet_name: Set<String> = addrBook.keys
    print("keySet_name = %s\n".format(keySet_name))
    for (key in keySet_name) {
        print("%5s : %s\n".format(key, addrBook[key]))
    }
}
