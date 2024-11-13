// Class Prism
package MyKotlinClasses

class Prism(color: String?, nm: String?, cx: Int, cy: Int, ang: Int, base: Int, tri_height: Int, val pr_height: Int ) :
    Triangle(color, nm, cx, cy, ang, base, tri_height) { // 상위 클래스 호출(Triangle)
    override fun toString() : String {
        var str = "Prism ("
        str += super.toString()
        str += ", pr_height = %d)".format(pr_height)
        return str
    }
    override fun draw() {
        println("Drawing a %s %s at (%d, %d) angle(%d), base(%d), tri_height(%d), pr_height(%d)".format(color, name, cx, cy, angle, base,
        tri_height, pr_height))
    }
}
