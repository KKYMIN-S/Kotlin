package MyKotlinClasses

class Sedan(color: String, type: String, driver: Driver?, var numSeats: Int) : Vehicle(color, type, driver) {
    override fun toString(): String {
        return "Sedan(Vehicle(color=$color, type=$type, driven by Driver(name=${driver?.name}, regID=${driver?.regID})), numSeats=$numSeats)"
    }
}
