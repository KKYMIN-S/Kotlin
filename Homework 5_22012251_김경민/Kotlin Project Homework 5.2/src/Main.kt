/* Kotlin Project Homework 5.2
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 30.
 * Inheritance(상속)
 */

import MyKotlinClasses.Teacher
import MyKotlinClasses.Student

fun main(args: Array<String>) {
    var st1 = Student("Kim", 20245678, 22401234, "ICT")
    println("st1 = " + st1); println()
    var st2 = Student()
    println("st2 = " + st2); println()
    var tchr1 = Teacher("Choi", 12000, 7654,"Hankook", "Computer")
    println("tchr1 = " + tchr1); println()
    var tchr2 = Teacher()
    println("tchr2 = " + tchr2); println()
}