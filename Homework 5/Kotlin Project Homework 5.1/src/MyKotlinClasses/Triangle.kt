// Class Triangle
package MyKotlinClasses

open class Triangle(color: String?, nm: String?, cx: Int, cy: Int, ang: Int, val base: Int, val tri_height: Int) :
    Shape(color!!, nm!!, cx, cy, ang) { // 상위 클래스 호출(Shape)
    override fun toString() : String {
        var str = "Triangle ("
        str += super.toString()
        str += ", base = %d, height = %d)".format(base, tri_height)
        return str
    }
    override fun draw() {
        println("Drawing a %s %s at (%d, %d) angle(%d), base(%d), height(%d)".format(color, name, cx, cy, angle, base, tri_height))
    }
}