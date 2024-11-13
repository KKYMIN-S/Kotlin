// Class Cylinder
package MyKotlinClasses

class Cylinder(color: String?, nm: String?, cx: Int, cy: Int, ang: Int, radius: Int, val height: Int) :
    Circle(color, nm, cx, cy, ang, radius) { // 상위 클래스 호출(Circle)
    override fun toString() : String {
        var str = "Cylinder ("
        str += super.toString()
        str += ", height = %d)".format(height)
        return str
    }
    override fun draw() {
        println("Drawing a %s %s at (%d, %d) angle(%d), radius(%d), height(%d)".format(color, name, cx, cy, angle, radius, height))
    }
}