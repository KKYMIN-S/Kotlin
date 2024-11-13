package MyKotlinClasses

class Student : Person {
    private var st_id = 0 // 학번
    private var dept: String? = null // 전공
    private var school: String? = null // 학교
    private var GPA = 0.0 // 학점

    constructor() // default constructor
    constructor(nm: String?, reg_ID: Int, st_id: Int, dept: String?, school: String?, gpa: Double) : super( nm, reg_ID) {
        this.st_id = st_id
        this.dept = dept
        this.school = school
        GPA = gpa
    }

    override fun toString(): String {
        var str = "Student("
        str += java.lang.String.format("name: %8s, reg_id : %7d", get_name(), get_regID())
        str += ", school: %5s, dept: %5s, st_id:%7d, GPA:%8.2f)\n".format(school, dept, st_id, GPA)
        return str
    }

    fun compareStudent(attrName: String, other: Student): Int {
        var result = 0
        if (attrName == "name") {
            result = this.get_name()?.compareTo(other.get_name() ?: "") ?: 0
        }
        else if (attrName == "st_id") {
            result = (st_id - other.st_id)
        }
        else if (attrName == "GPA") {
            result = if (GPA < other.GPA) -1
            else if (GPA > other.GPA) 1 else 0
        }
        return result
    }
}