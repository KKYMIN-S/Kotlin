package MyKotlinClasses

class Truck(color: String, type: String, driver: Driver?, var cargoCapacity: Int) : Vehicle(color, type, driver) {
    override fun toString(): String {
        return "Truck(Vehicle(color=$color, type=$type, driven by Driver (name=${driver?.name}, regID=${driver?.regID})), cargoCapacity=$cargoCapacity)"
    }
}
