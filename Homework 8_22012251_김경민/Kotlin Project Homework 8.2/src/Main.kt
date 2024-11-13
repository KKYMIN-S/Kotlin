/* Kotlin Project Homework 8.2
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 18.
 * Generic Linked List 기반의 FIFO Queue 구현
 */

import GenericCircularQueue.*

fun main(args: Array<String>) {
    var num_data_per_round = 3
    val num_rounds = 5
    val queue_capacity = 10
    val int_queue = GenericCirQueue<Int>("IntCirQueue_DLL", queue_capacity)
    var input_data: Int = 0
    var output_data: Int

    for (k in 0..num_rounds) {
        //println("Testing enque() operation of integer queue (queue_size = %d) ....".format(queue_size))
        for (i in 0 until num_data_per_round) {
            int_queue.enque(input_data)
            print("IntCirQ after enqueue (%3d) : ".format(input_data))
            print(" IntCirQueue_DLL = ")
            print(int_queue)
            input_data++
        }
        //println("\nTesting deque() operation of integer queue (queue_size = %d) ....".format(queue_size))
        for (i in 0 until num_data_per_round) {
            output_data = int_queue.deque()!!
            if (output_data != null)
                print("IntCirQ after dequeue (%3d) : ".format(output_data))
            print(" IntCirQueue_DLL = ")
            print(int_queue)
        }
    }
    println()
    var str_data: String
    val str_queue = GenericCirQueue<String>("StrCirQueue_DLL",queue_capacity)
    var count : Int = 0
    num_data_per_round = 4

    for (k in 0..num_rounds) {
        //println("Testing enque() operation of integer queue (queue_size = %d) ....".format(queue_size))
        for (i in 0 until num_data_per_round) {
            //str_data = 'A' + (Math.random() * queue_size).toInt()
            str_data = ('A' + count).toString()
            str_queue.enque(str_data)
            print("StrCirQ after enqueue (%3s) : ".format(str_data))
            print(" StrCirQueue_DLL = ")
            print(str_queue)
            count++
        }
        //println("\nTesting deque() operation of integer queue (queue_size = %d) ....".format(queue_size))
        for (i in 0 until num_data_per_round) {
            str_data = str_queue.deque()!!
            if (str_data != null)
                print("StrCirQ after dequeue (%3s) : ".format(str_data))
            print(" StrCirQueue_DLL = ")
            print(str_queue)
        }
    }
}