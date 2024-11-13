// Class Rectangle
package MyKotlinClasses

open class Rectangle(color: String?, nm: String?, cx: Int, cy: Int, ang: Int, val width: Int, val length: Int ) :
    Shape(color!!, nm!!, cx, cy, ang) { // 상위 클래스 호출(Shape)
    override fun toString() : String {
        var str = "Rectangle ("
        str += super.toString()
        str += ", width = %d, length = %d)".format(width, length)
        return str
    }
    override fun draw() {
        println("Drawing a %s %s at (%d, %d) angle(%d), width(%d), length(%d)".format(color, name, cx, cy, angle, width, length))
    }
}