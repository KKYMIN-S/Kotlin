package MyKotlinClasses

interface I_Drive { // 인터페이스 구현
    fun drive() // 멤버 함수
    fun forward()
    fun turn()
    fun stop()
}

open class Driver(p: Person) : Person(p.name, p.regID), I_Drive { // 상속
    lateinit var vehicle: Vehicle // Vehicle를 상속받는 데이터 멤버

    init {
        println("class Driver: primary constructor(Person(name(${p.name}, regID=${p.regID}))")
    }

    override fun drive() {
        println("${this.toString()} is driving ${vehicle.toString()}")
    }

    override fun forward() {
        println("Moving forward")
    }

    override fun turn() {
        println("Turning the vehicle")
    }

    override fun stop() {
        println("Stopping the vehicle")
    }

    override fun toString(): String {
        return "Driver(name=$name, regID=$regID)"
    }
}
