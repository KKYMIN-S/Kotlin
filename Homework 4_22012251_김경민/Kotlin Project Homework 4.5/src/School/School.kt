package School

class School {
    var students: MutableList<Student> = mutableListOf()
    fun addStudent(st: Student) {
        students.add(st)
    }

    fun sortStudents(attrName: String, sortingDir: String) {
        var i: Int
        var j: Int
        var temp: Student
        j = 1
        while (j <= students.size - 1) {
            i = 0
            while (i <= students.size - j - 1) {
                // compareStudents 사용, 학생 비교
                if (students[i].compareStudents(students[i + 1], attrName, sortingDir) > 0) {
                    // Swap
                    temp = students[i]
                    students[i] = students[i + 1]
                    students[i + 1] = temp
                }
                i++
            }
            j++
        }
    }

    fun printStudents() { // Students 출력문
        print("Students : [")
        for (i in 0 until students.size) {
            if (i != students.size - 1)
                print("%s, ".format(students[i]))
            else
                print("%s".format(students[i]))
        }
        println("]")
    }
}