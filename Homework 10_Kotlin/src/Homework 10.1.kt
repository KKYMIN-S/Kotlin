/* Homework 10.1.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 5.
 * Kotlin Multi-thread Programming with Critical Section
 */

class ThreadRunnable(var mark: Char, var mutex: Any): Runnable {
    override fun run() {
        for (i in 1..50) {
            synchronized(mutex) {
                for (k in 0..50) {
                    print("%c".format(mark))
                    Thread.sleep(10)
                }
                println()
            }
            Thread.sleep(100)
        }
    }
}

fun main(args: Array<String>) {
    val mutex = Any()
    val thread_A = Thread(ThreadRunnable('A', mutex))
    val thread_B = Thread(ThreadRunnable('B', mutex))
    val thread_C = Thread(ThreadRunnable('C', mutex))
    val thread_D = Thread(ThreadRunnable('D', mutex))
    thread_A.start(); thread_B.start(); thread_C.start(); thread_D.join();
    thread_A.join(); thread_B.join(); thread_C.join(); thread_D.join();
}