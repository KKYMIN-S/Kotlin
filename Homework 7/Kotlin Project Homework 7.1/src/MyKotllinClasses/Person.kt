package MyKotlinClasses

open class Person {
    var name: String? = null // 이름
    var regID = 0 // regID

    constructor() // default constructor
    constructor(nm: String?, reg_ID: Int) {
        name = nm
        regID = reg_ID
    }
    fun print() {
        print("Person(name: %s, reg_id: %d) ".format(name, regID))
    }
    fun get_name() : String? { // 이름 받기
        return this.name
    }
    fun get_regID() : Int { // regID 받기
        return this.regID
    }
}