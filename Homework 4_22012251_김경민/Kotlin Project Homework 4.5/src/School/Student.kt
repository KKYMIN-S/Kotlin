package School

class Student(var name : String, var stID : Int, var gpa : Double ) : Comparable<Student> {
    override fun compareTo(other: Student): Int {
        var result = this.stID - other.stID
        return result
    }

    fun compareStudents(other : Student, attr:String, sortingDir:String) : Int {
        var result : Int = 0
        var sortingIncDec =
            if (sortingDir == "Decreasing") -1 else 1
        if (attr == "name") {
            result = this.name.compareTo(other.name) // 이름 비교 구현
        } else if (attr == "stID") {
            result = this.stID - other.stID
        } else if (attr == "GPA") {
            result = this.gpa.compareTo(other.gpa) // GPA 비교 구현
        }
        return result * sortingIncDec
    }

    override fun toString() : String {
        return "(%s, %d, %.2f)".format(name, stID, gpa)
    }
}