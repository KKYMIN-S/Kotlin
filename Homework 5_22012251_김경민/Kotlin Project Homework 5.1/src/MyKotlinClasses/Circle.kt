// Class Circle
package MyKotlinClasses

open class Circle(color: String?, nm: String?, cx: Int, cy: Int, ang: Int, val radius: Int) :
    Shape(color!!, nm!!, cx, cy, ang) { // 상위 클래스 호출(Shape)
    override fun toString() : String {
        var str = "Circle ("
        str += super.toString()
        str += ", radius = %d)".format(radius)
        return str
    }
    override fun draw() {
        println("Drawing a %s %s at (%d, %d) angle(%d), radius(%d)".
        format(color, name, cx, cy, angle, radius))
    }
}
