/* Homework 7.2.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 8.
 * class Person, class Student, class StudentArray, Sorting, File I/O
 */

import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.*
open class Person {
    var name: String? = null
    var regID = 0
    constructor() // default constructor
    constructor(nm: String?, reg_ID: Int) {
        name = nm
        regID = reg_ID
    }
    fun print() {
        System.out.printf("Person(name: %s, reg_id: %d)", name, regID)
    }
    fun get_name() : String? {
        return this.name
    }
    fun get_regID() : Int {
        return this.regID
    }
}

class Student : Person {
    private var st_id = 0
    private var dept: String? = null
    private var school: String? = null
    private var GPA = 0.0

    constructor() // default constructor
    constructor(nm: String?, reg_ID: Int, st_id: Int, dept: String?, school: String?, gpa: Double) : super( nm, reg_ID) // invoke constructor of parent class Person
    {
        this.st_id = st_id
        this.dept = dept
        this.school = school
        GPA = gpa
    }
    override fun toString(): String {
        var str = "Student("
        str += java.lang.String.format("name: %8s, reg_id : %7d", get_name(), get_regID())
        str += String.format(
            ", school: %5s, dept: %5s, st_id:%7d, GPA:%8.2f)\n", school, dept, st_id, GPA
        )
        return str
    }
    fun compareStudent(attrName: String, other: Student): Int {
        var result = 0
        if (attrName === "name") {
            result = other.get_name()?.let { this.get_name()?.compareTo(it) }!!
        } else if (attrName === "st_id") {
            result = (st_id - other.st_id)
        } else if (attrName === "GPA") {
            result = if (GPA < other.GPA) -1 else if (GPA > other.GPA) 1 else 0
        }
        return result
    }
}

class StudentArray {
    lateinit var students: Array<Student?>
    private var num_students = 0
    fun fget_students(fin: Scanner) {
        num_students = fin.nextInt()
        students = arrayOfNulls(num_students)
        for (i in 0 until num_students) {
            val nm: String = fin.next()
            val reg_ID: Int = fin.nextInt()
            val st_id: Int = fin.nextInt()
            val dept: String = fin.next()
            val school: String = fin.next()
            val gpa: Double = fin.nextDouble()
            students[i] = Student(nm, reg_ID, st_id, dept, school, gpa)
        }
    }
    fun print_students() {
        System.out.printf("Total %d students :\n", num_students)
        for (i in 0 until num_students) {
            print(students[i])
        }
    }
    @Throws(IOException::class)
    fun fprint_students(fout: FileWriter) {
        fout.write(String.format("Total %d students :\n", num_students))
        for (i in 0 until num_students) {
            fout.write(students[i].toString())
        }
    }

    fun sort(key_attr: String?, sorting_order: String) {
        /* sort given array with insertion_sort algorithm */
        var i: Int
        var j: Int
        var st_temp: Student?
        j = 1
        while (j <= num_students - 1) {
            st_temp = students[j]
            i = j
            if (sorting_order === "increasing") {
                while (i > 0 && students[i - 1]!!.compareStudent(key_attr!!, st_temp!!) > 0) {
                    students[i] = students[i - 1] // shift right to make a room
                    --i
                }
            }
            else { // "decreasing" while (i > 0 && students[i - 1]!!.compareStudent(key_attr!!, st_temp!!) < 0) {
                students[i] = students[i - 1] // shift right to make a room
                --i
            }
            students[i] = st_temp
            j++
        }
    }
}

fun main(args: Array<String>) {
    val fin_name = "Student_records.txt"
    val fout_name = "Processed_students.txt"
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