package MyKotlinClasses

class Student (nm: String, rgID: Int, var stID : Int, var stMajor: String): Person(nm, rgID) {
    val dmmy_student = println("class Student:: primary constructor declaration of stID and stMajor")

    init { // 생성자 초기화
        println("class Student:: init{}")
    }

    constructor(arg: Unit = println("class Student:: secondary constructor with no argument")) : this("", 0, 0, "") {
        this.stID = 0
        this.stMajor = ""
        println ("class Student:: initialization of st_id and major with default values in secondary constructor")
    }

    override fun toString(): String {
        return "Student(name=%s, reg_id=%d, st_id=%d, major=%s)".format(this.name, this.regID, this.stID, this.stMajor)
    }
}