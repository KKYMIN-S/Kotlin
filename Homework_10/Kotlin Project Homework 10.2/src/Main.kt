/* Kotlin Project Homework 10.2
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 11. 11.
 * Drawing Shapes with Kotlin/Java Graphics2D
 */

import java.awt.*
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.math.cos
import kotlin.math.sin

// 원을 그리는 JPanel class 정의
class JPanel_Circle(var panel_width: Int, var panel_height: Int, var radius: Int, var color: Color) : JPanel() {
    var shape_name: String? = null
    public override fun paintComponent(g: Graphics) {
        val g2 = g as Graphics2D
        g2.color = color
        g2.stroke = stroke_10
        val cx = panel_width / 2
        val cy = panel_height / 2
        val p1x = cx - radius
        val p1y = cy - radius
        val p2x = cx + radius
        val p2y = cy + radius
        g2.drawOval(p1x, p1y, radius * 2, radius * 2)
    }
    companion object {
        val stroke_10 = BasicStroke(10.0f)
    }
}
// 다각형을 그리는 JPanel class 정의
class JPanel_Polygon( var panel_width: Int, var panel_height: Int, var radius: Int, var num_vertex: Int, var color: Color) : JPanel() {
    public override fun paintComponent(g: Graphics) {
        val g2 = g as Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2.color = color
        g2.stroke = stroke_10
        // 중심 좌표 및 각도 초기화
        var x: Double
        var y: Double
        val cx = (panel_width / 2).toDouble()
        val cy = (panel_height / 2).toDouble()
        var theta_rad = 0.0
        val PI = 3.141592
        val px = IntArray(num_vertex)
        val py = IntArray(num_vertex)
        for (i in 0 until num_vertex) {
            // 각 꼭짓점 좌표를 계산하여 배열에 저장
            theta_rad = 3.0 * PI / 2.0 + i * (2.0 * PI) / num_vertex
            px[i] = (cx + radius * cos(theta_rad)).toInt()
            py[i] = (cy + radius * sin(theta_rad)).toInt()
        }
        g2.drawPolygon(px, py, num_vertex)
    }
    companion object {
        val stroke_10 = BasicStroke(10.0f)
    }
}
// 직사각형을 그리는 JPanel class 정의
class JPanel_Rectangle( var panel_width: Int, var panel_height: Int, var rect_width: Int, var rect_length: Int, var color: Color ) : JPanel() {
    var shape_name: String? = null
    public override fun paintComponent(g: Graphics) {
        val g2 = g as Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2.color = color
        g2.stroke = stroke_10
        val cx = panel_width / 2
        val cy = panel_height / 2
        val p1x = cx - rect_width / 2
        val p1y = cy - rect_length / 2
        val p2x = cx + rect_width / 2
        val p2y = cy + rect_length / 2
        g2.drawRect(p1x, p1y, rect_width, rect_length)
    }
    companion object {
        val stroke_10 = BasicStroke(10.0f)
    }
}
// 별 모양을 그리는 JPanel class 정의
class JPanel_Star(var panel_width: Int, var panel_height: Int, var radius: Int, var color: Color) : JPanel() {
    var shape_name: String? = null
    public override fun paintComponent(g: Graphics)
    {
        val g2 = g as Graphics2D
        var x: Double
        var y: Double
        val cx = (panel_width / 2).toDouble()
        val cy = (panel_height / 2).toDouble()
        var theta_rad = 0.0
        val PI = 3.141592
        val px = IntArray(5)
        val py = IntArray(5)
        for (i in 0..4) {
            // 별 모양의 꼭짓점 좌표를 계산하여 배열에 저장
            theta_rad = 3.0 * PI / 2.0 + i * (2.0 * PI) / 5.0
            px[i] = (cx + radius * cos(theta_rad)).toInt()
            py[i] = (cy + radius * sin(theta_rad)).toInt()
        }
        g2.stroke = BasicStroke(5f)
        g2.color = color
        g2.drawLine(px[0], py[0], px[2], py[2])
        g2.drawLine(px[2], py[2], px[4], py[4])
        g2.drawLine(px[4], py[4], px[1], py[1])
        g2.drawLine(px[1], py[1], px[3], py[3])
        g2.drawLine(px[3], py[3], px[0], py[0])
    }
    companion object {
        val stroke_10 = BasicStroke(10.0f)
    }
}

fun main() {
    val jframe = JFrame("Drawing_Shapes") // JFrame 생성
    jframe.setSize(600, 600)
    val contentPane = jframe.contentPane
    contentPane.setLayout(GridLayout(2, 2))

    val jpanel_circle = JPanel_Circle(300, 300, 100, Color.BLACK)
    jpanel_circle.setSize(300, 300)
    contentPane.add(jpanel_circle)

    val jpanel_rect = JPanel_Rectangle(300, 300, 200, 150, Color.RED)
    jpanel_rect.setSize(300, 300)
    contentPane.add(jpanel_rect)

    val jpanel_star = JPanel_Star(300, 300, 100, Color.GREEN)
    jpanel_star.setSize(300, 300)
    contentPane.add(jpanel_star)

    val jpanel_polygon = JPanel_Polygon(300, 300, 80, 7, Color.BLUE)
    jpanel_polygon.setSize(300, 300)
    contentPane.add(jpanel_polygon)

    jframe.isVisible = true // 화면 표시
}