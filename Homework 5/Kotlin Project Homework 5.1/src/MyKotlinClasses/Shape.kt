// Class Shape
package MyKotlinClasses

open class Shape(var color: String, var name : String, var cx : Int, var cy : Int, var angle : Int)
{
    init { // 주 생성자 초기화
        println("Primary constructor of Shape(color=%s, name=%s, cx=%d, cy=%d, ang=%d)".format(color, name, cx, cy, angle))
    }
    override fun toString() : String {
        return "Shape(color=%s, name=%s, cx=%d, cy=%d, ang=%d)".format(color, name, cx, cy, angle)
    }
    open fun draw() {
        println("Drawing a %s %s at (%d, %d) angle(%d)".
        format(color, name, cx, cy, angle))
    }
}
