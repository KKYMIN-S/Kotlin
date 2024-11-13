/* Homework 6.1.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 5.
 * TreeMap<String, Person> 기반 주소록 (address book) 구현
 */

import java.util.*

class Person(var name: String, telNo: TelNum) {
    private var telNo: TelNum = telNo
    fun get_name(): String {
            return name
    }
    override fun toString(): String {
        return String.format("Person(Name = %5s, TelNo = %16s", name, telNo)
    }
}

class TelNum(var nation_code: Int, var reg_no: Int, var sw_no: Int, var line_no: Int) {
    override fun toString(): String {
        return String.format("%03d-%02d-%04d-%04d", nation_code, reg_no, sw_no, line_no)
    }
}

fun main(args: Array<String>) {
    val addrBook: TreeMap<String, Person> = TreeMap<String, Person>()
    val persons: Array<Person> = arrayOf<Person>(
        Person("Kim", TelNum(82, 53, 810, 1000)),
        Person("Yoon", TelNum(82, 2, 1234, 5678)),
        Person("Lee", TelNum(82, 51, 2579, 1234)),
        Person("Park", TelNum(82, 53, 1000, 5678)),
        Person("Choi", TelNum(82, 31, 2345, 6789))
    )
    for (p in persons) {
        addrBook[p.get_name()] = p
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
