// Class Pillar
package MyKotlinClasses

class Pillar(color: String?, nm: String?, cx: Int, cy: Int, ang: Int, width: Int, length: Int, val height: Int ) :
    Rectangle(color, nm, cx, cy, ang, width, length) { // 상위 클래스 호출(Rectangle)
    override fun toString() : String {
        var str = "Pillar ("
        str += super.toString()
        str += ", height = %d)".format(height)
        return str
    }
    override fun draw() {
        println("Drawing a %s %s at (%d, %d) angle(%d), width(%d), length(%d), height(%d)".format(color, name, cx, cy, angle, width, length, height))
    }
}