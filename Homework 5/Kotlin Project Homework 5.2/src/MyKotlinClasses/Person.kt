package MyKotlinClasses

open class Person (var name : String, var regID : Int){
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
}