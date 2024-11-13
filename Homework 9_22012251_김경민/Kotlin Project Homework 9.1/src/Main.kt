/* Kotlin Project Homework 9.1
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 25.
 * Generic Skip List
 */

import SkipList.*

fun main(args: Array<String>) {
    val skipList: GenericSkipList<Int> = GenericSkipList("SkipList_Int")
    val dataArray = arrayOf(3, 1, 5, 4, 2)
    for (data in dataArray) {
        print("adding (%s) => ".format(data))
        skipList.add (data)
        println ("%s".format(skipList))
    }
    val testArray = arrayOf(7, 2)
    for (t in testArray) {
        if (skipList.contains(t)) {
            println("Searching %s from %s => contained !!".format(t, skipList._name))
        } else {
            println("Searching %s from %s => is not contained !!".format(t, skipList._name))
        }
    }
    for (data in dataArray) {
        print("removing (%s) from SkipList<String> => ".format(data))
        skipList.remove (data)
        println ("%s".format(
            skipList
        ))
    }
}