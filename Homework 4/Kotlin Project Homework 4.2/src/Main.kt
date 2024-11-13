/* Kotlin Project Homework 4.2
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 20.
 * class IntStack
 */

import MyDataStructure.IntStack

fun main(args: Array<String>) {
    val stack_size = 20
    var int_data: Int
    val int_stack = IntStack(stack_size)
    println("Testing push() operation of integer stack (stack_size = %d) ....".format(stack_size))
    for (i in 0 until stack_size) {
        int_data = (Math.random() * stack_size).toInt()
        int_stack.push(int_data)
        print("After push (%3d) : ".format(int_data))
        println(int_stack)
    }
    println("\nTesting pop() operation of integer stack (stack_size = %d) ....".format(stack_size))
    for (i in 0 until stack_size) {
        int_data = int_stack.pop()
        print("After pop (%3d) : ".format(int_data))
        println(int_stack)
    }
}