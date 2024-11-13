/* Kotlin Project Homework 7.1
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 5.
 * class Person, class Student, class StudentArray, sorting, file I/O
 */

import MyKotlinClasses.*
import java.io.FileWriter
import java.util.Scanner
import java.io.File

fun main(args: Array<String>) {
    val fin_name = "student_records.txt"
    val fout_name = "processed_students.txt"
    val students = StudentArray()
    val fin = Scanner(File(fin_name))
    val fout = FileWriter(fout_name)
    students.fget_students(fin)

    print("Before sorting : ")
    students.print_students()
    fout.write("Initial state of students :")
    students.fprint_students(fout)
    students.sort("name", "increasing")
    print("\nAfter sorting by name : ")
    students.print_students()
    fout.write("\nAfter sorting by name : ")
    students.fprint_students(fout)

    students.sort("st_id", "increasing")
    print("\nAfter sorting by reg_id : ")
    students.print_students()
    fout.write("\nAfter sorting by reg_id : ")
    students.fprint_students(fout)

    students.sort("GPA", "decreasing")
    print("\nAfter sorting by GPA : ")
    students.print_students()
    fout.write("\nAfter sorting by GPA : ")
    students.fprint_students(fout)
    fout.close()
}