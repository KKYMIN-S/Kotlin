/* Kotlin Project Homework 8.4
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 18.
 * Generic Red Black Tree
 */

import GenericRedBlackTree.*

fun main() {
    val rbIntTree = RedBlackTree<Int>()
    val intDataArray = intArrayOf(5, 4, 3, 2, 1, 0, 6, 7, 8, 9)
    print("intDataArray = [");
    for (i in 0 until intDataArray.size) {
        if (i == intDataArray.size - 1)
            print("%3s".format(intDataArray[i].toString()))
        else
            print("%3s,".format(intDataArray[i].toString()))
    }
    println("]")
    var data: Int
    for (i in 0 until intDataArray.size) {
        data = intDataArray[i]
        println("round (%2d) :: inserting (%s) --------".format(i, data.toString()))
        rbIntTree.insert(data)
        // rbTree.printRBTree_withDepth()
        println()
    }
    // Displaying the tree
    println("Red-Black Tree (In-Order Traversal):")
    // rbTree.printTree()
    rbIntTree.printRBTree_withDepth()
    println()
    // Searching for a entry
    println("Test searching entry in Red-Black Tree")
    val searchNode = rbIntTree.search(7)
    if (searchNode != null) {
        println("Found node: ${searchNode.entry} (${searchNode.color})")
    } else {
        println("Node not found.")
    }
    val rbStrTree = RedBlackTree<String>()
    val strDataArray = arrayOf("E", "D", "C", "B", "A", "F", "G", "H")
    print("strDataArray = [");
    for (i in 0 until strDataArray.size) {
        if (i == strDataArray.size - 1)
            print("%3s".format(strDataArray[i].toString()))
        else
            print("%3s,".format(strDataArray[i].toString()))
    }
    println("]")
    var strData: String
    for (i in 0 until strDataArray.size) {
        strData = strDataArray[i]
        println("round (%2d) :: inserting (%s) --------".format(i, strData.toString()))
        rbStrTree.insert(strData)
        // rbTree.printRBTree_withDepth()
        println()
    }
    // Displaying the tree
    println("Red-Black Tree (In-Order Traversal):")
    // rbTree.printTree()
    rbStrTree.printRBTree_withDepth()
    println()
    // Searching for a entry
    println("Test searching entry in Red-Black Tree")
    val strSearchNode = rbStrTree.search("F")
    if (strSearchNode != null) {
        println("Found node: ${strSearchNode.entry} (${strSearchNode.color})")
    } else {
        println("Node not found.")
    }
}