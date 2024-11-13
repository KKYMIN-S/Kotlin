/* Kotlin Project Homework 5.1
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 30.
 * Polymorphism(다형성)
 */

import MyKotlinClasses .*

fun main(args: Array<String>) {
    val shapes : Array<Shape> = arrayOf(
        Shape("Red", "Shape", 0, 0, 0),
        Circle("Blue", "Circle", 1, 1, 1, 1),
        Triangle("Green", "Triangle", 2, 2, 2, 2, 2),
        Rectangle("Brown", "Rectangle", 3, 3, 3, 3, 3),
        Cylinder("Black", "Cylinder", 1, 1, 1, 1, 10),
        Prism("Purple", "Prism", 2, 2, 2, 2, 2, 20),
        Pillar("Magenta", "Pillar", 3, 3, 3, 3, 3, 30)
    )
    for (shp in shapes) {
        println(shp)
    }
    for (shp in shapes) { // 하나의 반복문으로 다양한 도형 관리
        shp.draw()
    }
}