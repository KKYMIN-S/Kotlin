package MyKotlinClasses

open class Vehicle(var color: String, var type: String, var driver: Driver?) {
    init {
        println("class Vehicle: primary constructor (color=$color, type=$type, driver=Driver(name=${driver?.name}, regID=${driver?.regID}))")
    }
    override fun toString(): String {
        return "Vehicle(color=$color, type=$type, driven by Driver (${driver?.name}, ${driver?.regID}))"
    }
}

