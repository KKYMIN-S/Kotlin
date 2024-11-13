/* Kotlin Project Homework 5.4
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 30.
 * Interface를 포함하는 상속
 */

import MyKotlinClasses.*

fun main() {
    val drvr_Kim = Driver(Person("Kim", 1111))
    val num_seats_sedan = 5
    val white_sedan = Sedan("white", "sedan", drvr_Kim, num_seats_sedan )
    drvr_Kim.vehicle = white_sedan
    println(white_sedan)
    drvr_Kim.drive(); println()

    val drvr_Lee = Driver(Person("Lee", 2222))
    val num_seats_bus = 30
    val yellow_bus = Bus("yellow", "busn", drvr_Lee, num_seats_bus )
    drvr_Lee.vehicle = yellow_bus
    println(white_sedan);
    drvr_Lee.drive(); println()

    val drvr_Choi = Driver(Person("Choi", 3333))
    val cargo_capa_truck = 5000 //Kg
    val black_truck = Truck("black", "truck", drvr_Choi, cargo_capa_truck )
    drvr_Choi.vehicle = black_truck
    println(black_truck)
    drvr_Choi.drive(); println()
}