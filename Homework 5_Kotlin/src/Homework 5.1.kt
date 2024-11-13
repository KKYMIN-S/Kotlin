/* Homework 5.1.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 5.
 * class 간의 상속 관계
 */

// Interface I_Drive
interface I_Drive {
    fun forward()
    fun turn()
    fun stop()
}

// Class Person
open class Person(val name: String, val reg_id: String) {
    override fun toString(): String {
        return "Person($name($reg_id))"
    }
}

// Class Vehicle
open class Vehicle(val color: String, val v_type: String, val driver: Person) {
    override fun toString(): String {
        return "Vehicle($color, $v_type, $driver(${driver.reg_id}))"
    }
}

class Sedan(color: String, v_type: String, driver: Person, val num_seat: Int) : Vehicle(color, v_type, driver), I_Drive {
    override fun forward() {
        println("Vehicle($color, $v_type, ${driver.name}(${driver.reg_id}), $num_seat) is moving forward")
    }
    override fun turn() {
        println("Vehicle($color, $v_type, ${driver.name}(${driver.reg_id}), ${driver.reg_id}, $num_seat) is turning")
    }
    override fun stop() {
        println("Vehicle($color, $v_type, ${driver.name}(${driver.reg_id}), $num_seat) is stopped")
    }
}

class Bus(color: String, v_type: String, driver: Person, val num_seat: Int) : Vehicle(color, v_type, driver), I_Drive {
    override fun forward() {
        println("Vehicle($color, $v_type, ${driver.name}(${driver.reg_id}), $num_seat) is moving forward")
    }
    override fun turn() {
        println("Vehicle($color, $v_type, ${driver.name}(${driver.reg_id}), $num_seat) is turning")
    }
    override fun stop() {
        println("Vehicle($color, $v_type, ${driver.name}(${driver.reg_id}), $num_seat) is stopped")
    }
}

class Truck(color: String, v_type: String, driver: Person, val cargo_capa: Int) : Vehicle(color, v_type, driver), I_Drive {
    override fun forward() {
        println("Vehicle($color, $v_type, ${driver.name}(${driver.reg_id}), $cargo_capa kg) is moving forward")
    }
    override fun turn() {
        println("Vehicle($color, $v_type, ${driver.name}(${driver.reg_id}), $cargo_capa kg) is turning")
    }
    override fun stop() {
        println("Vehicle($color, $v_type, ${driver.name}(${driver.reg_id}), $cargo_capa kg) is stopped")
    }
}

fun main() {
    val driver = Person("Kim", "12345678")
    val sedan = Sedan("Black", "Sedan", driver, 4)
    val bus = Bus("Yellow", "Bus", driver, 40)
    val truck = Truck("Blue", "Truck", driver, 1000)

    sedan.forward()
    bus.turn()
    truck.stop()
}