/* Kotlin Project Homework 4.5
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 20.
 * class Student(var name : String, var stID : Int, var gpa : Double): Comparable<Student>, class School
 */

import School.*

fun main() {
    var school : School = School()
    school.addStudent(Student("Kim", 3333, 85.2))
    school.addStudent(Student("Lee", 1111, 95.7))
    school.addStudent(Student("Choi", 2222, 90.0))
    school.addStudent(Student("Park", 5555, 78.8))
    school.addStudent(Student("Yoon", 4444, 93.5))
    println("Students at initialization state :")
    school.printStudents()
    school.sortStudents("name", "Increasing")
    println("After sorting students by name :")
    school.printStudents()
    school.sortStudents("stID", "Increasing")
    println("After sorting students by StID :")
    school.printStudents()
    school.sortStudents("GPA", "Decreasing")
    println("After sorting students by GPA (decreasing order) :")
    school.printStudents()
}