/* Kotlin Project Homework 7.3
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 5.
 * class Nation(), File I/O, compareCountries(), class NationArray(), sort()
 */

import Nation.*
import java.io.File
import java.util.*

fun main() {
    val fin_name = "Nations.txt"
    val nations = NationArray()
    val fin = Scanner(File(fin_name))
    nations.fgetNations(fin)
    print("Nations after initialization: ")
    print(nations)
    nations.sort("name", "increasing")
    print("\nNations sorted by name in increasing order: ")
    print(nations)
    nations.sort("population", "decreasing")
    print("\nNations sorted by population in decreasing order: ")
    print(nations)
    fin.close()
}