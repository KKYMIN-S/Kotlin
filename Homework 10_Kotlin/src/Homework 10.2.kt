/* Homework 10.2.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 5.
 * Kotlin Multithreading with pipe()
 */

import java.io.*
import java.util.concurrent.Executors
fun main() {
    val pipeMAOutput = PipedOutputStream()
    val pipeMAInput = PipedInputStream(pipeMAOutput)
    val pipeABOutput = PipedOutputStream()
    val pipeABInput = PipedInputStream(pipeABOutput)
    val pipeBCOutput = PipedOutputStream()
    val pipeBCInput = PipedInputStream(pipeBCOutput)
    val pipeCMOutput = PipedOutputStream()
    val pipeCMInput = PipedInputStream(pipeCMOutput)

    val executor = Executors.newFixedThreadPool(3)
    executor.submit { threadA(pipeMAInput, pipeABOutput) }
    executor.submit { threadB(pipeABInput, pipeBCOutput) }
    executor.submit { threadC(pipeBCInput, pipeCMOutput) }

    val reader = BufferedReader(InputStreamReader(pipeCMInput))
    val writer = PrintWriter(pipeMAOutput)
    println("main():: Starting circulation of message delivery M->A->B->C->M")
    writer.println("Message from M")
    writer.flush()
    println("main():: Sent message to A")
    val messageFromC = reader.readLine()
    println("main():: Received from C: $messageFromC")
    pipeMAInput.close()
    pipeMAOutput.close()
    executor.shutdown()
}

fun threadA(pipeIn: PipedInputStream, pipeOut: PipedOutputStream) {
    val reader = BufferedReader(InputStreamReader(pipeIn))
    val writer = PrintWriter(pipeOut)
    val messageFromM = reader.readLine()
    println("Thread A: Received from M: $messageFromM")
    writer.println("Thread A Processed [$messageFromM]")
    writer.flush()
    pipeIn.close()
    pipeOut.close()
    println("Thread A: Message sent to B")
}

fun threadB(pipeIn: PipedInputStream, pipeOut: PipedOutputStream) {
    val reader = BufferedReader(InputStreamReader(pipeIn))
    val writer = PrintWriter(pipeOut)
    val messageFromA = reader.readLine()
    println("Thread B: Received from A: $messageFromA")
    writer.println("Thread B Processed [$messageFromA]")
    writer.flush()
    pipeIn.close()
    pipeOut.close()
    println("Thread B: Message sent to C")
}

fun threadC(pipeIn: PipedInputStream, pipeOut: PipedOutputStream) {
    val reader = BufferedReader(InputStreamReader(pipeIn))
    val writer = PrintWriter(pipeOut)
    val messageFromB = reader.readLine()
    println("Thread C: Received from B: $messageFromB")
    writer.println("Thread C Processed [$messageFromB]")
    writer.flush()
    pipeIn.close()
    pipeOut.close()
}
