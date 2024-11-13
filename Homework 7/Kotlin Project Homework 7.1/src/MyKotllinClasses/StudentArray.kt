package MyKotlinClasses

import java.io.FileWriter
import java.io.IOException
import java.util.Scanner

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

    @Throws(IOException::class) // 예외 처리
    fun fprint_students(fout: FileWriter) {
        fout.write(String.format("Total %d students :\n", num_students))
        for (i in 0 until num_students) {
            fout.write(students[i].toString())
        }
    }

    fun sort(key_attr: String?, sorting_order: String) { // 삽입 정렬
        var i: Int
        var j: Int
        var st_temp: Student?
        j = 1
        while (j <= num_students - 1) {
            st_temp = students[j]
            i = j
            if (sorting_order === "increasing") {
                while (i > 0 && students[i - 1]!!.compareStudent(key_attr!!, st_temp!!) > 0) {
                    students[i] = students[i - 1] // 오른쪽 이동 후 공간 생성
                    --i
                }
            } else { // "decreasing"
                while (i > 0 && students[i - 1]!!.compareStudent(key_attr!!, st_temp!!) < 0) {
                    students[i] = students[i - 1] // 오른쪽 이동 후 공간 생성
                    --i
                }
            }
            students[i] = st_temp
            j++
        }
    }
}