package MyKotlinClasses

class Bus(color: String, type: String, driver: Driver?, var numSeats: Int) : Vehicle(color, type, driver) {
    override fun toString(): String {
        return "Bus(Vehicle(color=$color, type=$type, driven by Driver (name=${driver?.name}, regID=${driver?.regID})), numSeats=$numSeats)"
    }
}
