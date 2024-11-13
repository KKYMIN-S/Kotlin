package MyKotlinClasses

open class Person(var name: String, var regID: Int) {
    init {
        println("class Person: primary constructor declaration of name($name) and regID($regID)")
    }

    override fun toString() = "class Person:: primary constructor declaration of name($name) and regID($regID)"
}