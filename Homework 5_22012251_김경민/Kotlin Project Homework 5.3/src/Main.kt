/* Kotlin Project Homework 5.3
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 30.
 * Interface를 포함하는 상속
 */

import MyKotlinClasses.Teacher
import MyKotlinClasses.Student

fun main(args: Array<String>) {
    var st1 = Student("Kim", 20245678, 22401234, "ICT")
    println("st1 = " + st1);
    st1.talk()
    st1.study()
    println()
    var tchr1 = Teacher("Choi", 12000, 7654,"Hankook", "Computer")
    println("tchr1 = " + tchr1);
    tchr1.move()
    tchr1.teach()
}
