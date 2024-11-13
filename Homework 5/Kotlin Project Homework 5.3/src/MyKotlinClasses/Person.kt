package MyKotlinClasses

open class Person (var name : String, var regID : Int) : I_PersonActivity { // I_PersonActivity 상속
    val dmmy_person = println("class Person:: primary constructor declaration of name and regID")
    init { // 생성자 초기화
        println("class Person:: init{}")
    }

    constructor(arg: Unit=println("class Person :: secondary constructor with no argument")) : this("", 0) { // 생성자
        println("class Person:: secondary constructor, initializing name and reg_id with defalut values")
    }

    override fun toString() : String {
        return "Person(name=%s, regID=%d)".format(this.name, this.regID)
    }
    // I_PersonActivity 인터페이스 구현
    override fun talk() {
        println("$name is talking")
    }

    override fun listen() {
        println("$name is listening")
    }

    override fun move() {
        println("$name is moving")
    }
}