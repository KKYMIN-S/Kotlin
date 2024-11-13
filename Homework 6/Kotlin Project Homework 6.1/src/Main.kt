/* Kotlin Project Homework 6.1
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 3.
 * GenericArray<E> 기반의 Circular FIFO Queue 구현
 */

import MyDataStructure.GenericCirQueue

fun main(args: Array<String>) {
    var num_data_per_round = 3
    val num_rounds = 5
    val queue_capacity = 10 // Queue의 용량
    val int_queue = GenericCirQueue<Int>("IntCirQueue", queue_capacity)
    var input_data: Int = 0
    var output_data: Int
    for (k in 0..num_rounds) {
        for (i in 0 until num_data_per_round) {
            int_queue.enque(input_data)
            print("IntCirQ after enqueue (%3d) : ".format(input_data))
            println(int_queue)
            input_data++
        }
        for (i in 0 until num_data_per_round) {
            output_data = int_queue.deque()!!
            if (output_data != null)
                print("IntCirQ after dequeue (%3d) : ".format(output_data))
            println(int_queue)
        }
    }
    println()

    var str_data: String
    val str_queue = GenericCirQueue<String>("StrCirQueue", queue_capacity)
    var count : Int = 0
    num_data_per_round = 4
    for (k in 0..num_rounds) {
        for (i in 0 until num_data_per_round) {
            //str_data = 'A' + (Math.random() * queue_size).toInt()
            str_data = ('A' + count).toString()
            str_queue.enque(str_data)
            print("StrCirQ after enqueue (%3s) : ".format(str_data))
            println(str_queue)
            count++
        }
        for (i in 0 until num_data_per_round) {
            str_data = str_queue.deque()!!
            if (str_data != null)
                print("StrCirQ after dequeue (%3s) : ".format(str_data))
            println(str_queue)
        }
    }
}
