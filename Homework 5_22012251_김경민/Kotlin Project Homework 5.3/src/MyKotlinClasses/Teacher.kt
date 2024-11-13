package MyKotlinClasses

class Teacher(name: String, regID: Int, var staffID: Int, var school: String, var department: String) : Person(name, regID), I_TeacherActivity {
    val dmmy_teacher = println("class Teacher:: primary constructor declaration of staffID, school, and department")
    init { // 생성자 초기화
        println("class Teacher:: init{}")
    }


    // 매개변수가 없는 보조 생성자 정의
    constructor(arg: Unit = println("class Teacher:: secondary constructor with no argument")) : this("", 0, 0, "", "") {
        println("class Teacher:: initialization of st_id and major with default values in secondary constructor")
    }

    // Overriding toString method to print teacher's details
    override fun toString(): String {
        return String.format("Teacher(name=%s, regID=%d, staffID=%d, school=%s, department=%s)", name, regID, staffID, school, department)

    }
    // I_TeacherActivity 인터페이스 구현
    override fun move() {
        println("Teacher ($name) is moving")
    }

    override fun teach() {
        println("Teacher ($name) is teaching")
    }
}