/* Kotlin Project Homework 8.3
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 18.
 * 재균형 기능을 가지는 이진탐색트리 구현
 */

import BinarySearchTree.*
import java.util.*

fun main(args: Array<String>) {
    val intArray = intArrayOf(3, 5, 7, 9, 1, 2, 4, 6, 8, 0)
    var BST_name = "BST_IntegerEntry"
    val cin = Scanner(System.`in`) // Scanner 객체 생성
    val bstIntEntry: BinarySearchTree<Int>? = BinarySearchTree<Int>(BST_name)
    for (int_entry in intArray) {
        bstIntEntry!!.insert_withRebalancing(int_entry)
        println(bstIntEntry)
    }
    var bstn_int: BinarySearchTreeNode<Int>?
    var int_searchKey = 1
    var searchResult: Int
    while (true) {
        print("Input an int_searchKey (-1 to quit) : ")
        int_searchKey = cin.nextInt()
        if (int_searchKey == -1)
            break
        bstn_int = bstIntEntry!!.search(int_searchKey)
        if (bstn_int != null && int_searchKey == bstn_int.getEntry()) {
            println("int_searchKey(%d) is found in the %s".format(int_searchKey, BST_name))
        } else {
            println("int_searchKey(%d) is not found in the %s".format(int_searchKey, BST_name))
        }
    }
    for (i in intArray.indices) {
        var bstn: BinarySearchTreeNode<Int>?
        val BST_root: BinarySearchTreeNode<Int>? = bstIntEntry?.getRoot()
        bstn = bstIntEntry?._deleteBSTN(BST_root)
        bstIntEntry?.setRoot(bstn)
        bstIntEntry?.printBST_withDepth()
    }
    val strArray = arrayOf("G", "H", "I", "C", "D", "A", "B", "E", "F", "J")
    BST_name = "BST_StringEntry"
    val bstStringEntry: BinarySearchTree<String> = BinarySearchTree<String>(BST_name)
    for (str_entry in strArray) {
        bstStringEntry.insert_withRebalancing(str_entry)
        println(bstStringEntry) }
    bstStringEntry.printBST_withDepth()
    var bstn_str: BinarySearchTreeNode<String>?
    var str_searchKey: String
    var str_searchResult: String
    while (true) {
        print("Input a strt_searchKey (. to quit) : ")
        str_searchKey = cin.next()
        if (str_searchKey == ".") break
        bstn_str = bstStringEntry.search(str_searchKey)
        if (bstn_str != null && str_searchKey == bstn_str.getEntry()) {
            println("str_searchKey(%s) is found in the %s".format(str_searchKey, BST_name))
        } else {
            println("str_searchKey(%s) is not found in the %s".format(str_searchKey, BST_name)) } }
    var BST_str_root: BinarySearchTreeNode<String>?
    for (i in strArray.indices) {
        BST_str_root = bstStringEntry.getRoot()
        bstn_str = bstStringEntry._deleteBSTN(BST_str_root)
        bstStringEntry.setRoot(bstn_str)
    }
}