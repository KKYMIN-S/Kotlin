/* Kotlin Project Homework 4.3
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 20.
 * class CirQueue
 */

import MyDataStructure.CirQueue

fun main(args: Array<String>) {
    val queue_capacity = 10
    var int_data: Int
    val cirQueue = CirQueue(queue_capacity)
    val num_data_per_round = 3
    val num_rounds = 5
    var data = 0

    for (k in 0..num_rounds) {
        // testing enqueue operations
        for (i in 0 until num_data_per_round) {
            //int_data = (Math.random() * queue_size).toInt()
            cirQueue.enque(data)
            print("CirQ after enqueue (%3d) : ".format(data))
            println(cirQueue)
            data++
        }
        // testing dequeue operations
        for (i in 0 until num_data_per_round) {
            int_data = cirQueue.deque()!!
            if (int_data != null)
                print("CirQ after dequeue (%3d) : ".format(int_data))
            println(cirQueue)
        }
    }
}