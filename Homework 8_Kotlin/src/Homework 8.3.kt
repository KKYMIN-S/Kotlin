/* Homework 8.3.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 8.
 * 완전이진트리 구조의 HeapPriorityQueue_Task 구현
 */

import HeapPriQ.*

fun main(args: Array<String>) {
    val events = arrayOf(
        Event(9, "Ev_9"), Event(8, "Ev_8"), Event(7, "Ev_7"), Event(6, "Ev_6"), Event(5, "Ev_5"),
        Event(4, "Ev_4"), Event(3, "Ev_3"), Event(2, "Ev_2"), Event(1, "Ev_1"), Event(0, "Ev_0")
    )
    val heapPriQ_Ev = HeapPriQ<Event>("HeapPriQ_Event", 20)
    println("Inserting events to heapPriQ_Ev :")
    for (ev in events) {
        print("%s ".format(ev))
       heapPriQ_Ev.insert(ev)
    }
    System.out.printf("\nSequence of events by RemoveHeapMin() :\n")
    for (i in events.indices) {
        val ev: Event? = heapPriQ_Ev.removeHeapMin()
        print("%s ".format(ev))
    }
}