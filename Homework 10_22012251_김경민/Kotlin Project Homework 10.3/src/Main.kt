/* Kotlin Project Homework 10.3
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 11. 11.
 * Bouncing Ball Animation 구현
 */

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Container
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.ItemEvent
import java.awt.event.ItemListener
import javax.swing.*
import javax.swing.border.LineBorder

class JPanel_BouncingBall(val ball_diameter:Int, val fr_width: Int, val fr_height:Int, val border_thickness:Int, val panel_x_margin:Int, val panel_y_margin:Int)
    : JPanel(), ActionListener {
    var timer = Timer(5, this) // this as ActionListener
    var ballSpeed : Double = 1.0 // controlled by JSlider (0.0 ~ 10.0)
    var ball_color : Color = Color.red
    var pos_x : Int = border_thickness
    var pos_y : Int = border_thickness
    var dx : Int = 1
    var dy : Int = 1
    var panel_width = fr_width
    val panel_height = fr_height

    override fun paintComponent(g : Graphics ) {
        super.paintComponent(g)
        g.setColor(this.ball_color)
        g.fillOval(pos_x, pos_y, ball_diameter, ball_diameter)
        timer.start()
    }
    // 공의 이동 Logic 처리
    override fun actionPerformed(e: ActionEvent?) {
        pos_x += (dx * ballSpeed).toInt() // x 방향으로 공 이동
        pos_y += (dy * ballSpeed).toInt() // y 방향으로 공 이동

        // 경계와 충돌 시 방향을 반대로 변경
        if (pos_x <= 0 && (dx < 0)) {
            println("Ball touched left boundary at (%d, %d), so chainging direction of x".format(pos_x, pos_y))
            pos_x = 0
            dx = -dx
        }
        if (pos_x >= (panel_width - ball_diameter - border_thickness - panel_x_margin) && (dx > 0)) {
            println("Ball touched right boundary at (%d, %d), so chainging direction of x".format( pos_x, pos_y))
            pos_x = panel_width - ball_diameter - border_thickness - panel_x_margin
            dx = -dx
        }
        if (pos_y <= 0 && (dy < 0)) {
            println("Ball touched top boundary at (%d, %d), so chainging direction of y".format(pos_x, pos_y))
            pos_y = 0
            dy = -dy
        }
        if (pos_y >= (panel_height - ball_diameter - border_thickness - panel_y_margin) && (dy > 0)) {
            println("Ball touched bottom boundary at (%d, %d), so chainging direction of y".format(pos_x, pos_y))
            pos_y = panel_height - ball_diameter - border_thickness - panel_y_margin
            dy = -dy
        }
        println("Ball speed(%5.2f), position(%3d, %3d)".format(ballSpeed, pos_x, pos_y))
        repaint() // repaint JPanel - 화명 갱신을 위해 다시 그리기 요청
    }
}
// JSlider를 이용해 공의 속도 조절
class JSlider_SpeedControl(var jp_bouncingBall : JPanel_BouncingBall, val fr_width : Int, val fr_height : Int, val border_thickness: Int) : JPanel() {
    val contentPane : Container? = null
    var ballSpeed: Double = 0.0
    val JSlider_MAX = 100
    val jslider_SpeedControl = JSlider(JSlider.HORIZONTAL, 0, JSlider_MAX, 50)

    init { // constructor
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jslider_SpeedControl.addChangeListener {
            var updated_ballSpeed = jslider_SpeedControl.getValue() * 10.0 / this.JSlider_MAX
            jp_bouncingBall.ballSpeed = updated_ballSpeed
            println("BallSpeed changed : %.2f".format(updated_ballSpeed))
        }
        add(jslider_SpeedControl)
        this.jp_bouncingBall.ballSpeed = jslider_SpeedControl.getValue() * 10.0 / JSlider_MAX
        setVisible(true)
    }
}
class JPanel_RadioButton_Color internal constructor(var jp_bouncingBall: JPanel_BouncingBall) : JPanel() {
    var rb_color = arrayOfNulls<JRadioButton>(3)
    init {
        val color_str = arrayOf("red", "green", "blue")
        val bgrp_color = ButtonGroup()
        for (i in rb_color.indices) {
            rb_color[i] = JRadioButton(color_str[i])
            rb_color[i]!!.addItemListener(RB_Color_ItemListener())
            bgrp_color.add(rb_color[i])
            add(rb_color[i])
        }
        rb_color[0]!!.setSelected(true)
    }

    internal inner class RB_Color_ItemListener : ItemListener {
        override fun itemStateChanged(e: ItemEvent) {
            if (e.stateChange == ItemEvent.DESELECTED) return
            if (rb_color[0]!!.isSelected) {
                jp_bouncingBall.ball_color = Color.red
                println("BallColor changed to RED")
            } else if (rb_color[1]!!.isSelected) {
                jp_bouncingBall.ball_color = Color.GREEN
                println("BallColor changed to GREEN")
            } else if (rb_color[2]!!.isSelected) {
                jp_bouncingBall.ball_color = Color.BLUE
                println("BallColor changed to BLUE")
            }
        }
    }
}
class Animation_BouncingBall : JPanel(), ActionListener {
    var timer = Timer(5, this) // this as ActionListener
    var pos_x = 0
    var pos_y = 0
    var dx = 1
    var dy = 1
    var ball_diameter = 100
    var fr_width = 400
    var fr_height = 300
    var fr_x_margin = 15
    var fr_y_margin = 40
    var border_thickness = 5
    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.color = Color.RED
        g.fillOval(pos_x, pos_y, ball_diameter, ball_diameter)
        timer.start()
    }
    override fun actionPerformed(e: ActionEvent) {
        if (pos_x < 0 || pos_x > fr_width - ball_diameter - border_thickness) dx = -dx
        if (pos_y < 0 || pos_y > fr_height - ball_diameter - border_thickness) dy = -dy
        pos_x += dx
        pos_y += dy
        repaint() // repaint JPanel
    }
}
fun main(args: Array<String>) {
    val jf = JFrame()
    val ball_diameter = 100
    val panel_width = 400
    val panel_ball_height = 300
    val panel_color_height = 60
    val panel_control_height = 60
    val panel_y_colorControl = 60
    val panel_y_speedControl = 60
    val border_thickness = 3
    val panel_x_margin = 20
    val panel_y_margin = 30
    val fr_width = 400
    val fr_height = 300
    val fr_x_margin = 15
    val fr_y_margin = 40
    val control_panel_height = 100

    jf.setTitle("Bouncing Ball with Color Setting and Speed Control")
    jf.setSize(
        fr_width + fr_x_margin + border_thickness,
        fr_height + fr_y_margin + border_thickness + control_panel_height
    )
    jf.isVisible = true
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    var jp_BouncingBall = JPanel_BouncingBall(ball_diameter, fr_width, fr_height, border_thickness, panel_x_margin, panel_y_margin)
    jp_BouncingBall.border = LineBorder(Color.BLUE, 2)
    jp_BouncingBall.setBorder(BorderFactory.createTitledBorder("Animation of Bouncing Ball"))
    jf.add(jp_BouncingBall)

    var jp_RB_Color : JPanel_RadioButton_Color = JPanel_RadioButton_Color(jp_BouncingBall)
    jp_RB_Color.setVisible(true)
    jp_RB_Color.setSize(panel_width, panel_color_height)
    jp_RB_Color.setBorder(BorderFactory.createTitledBorder("Color Control"))
    jf.add(jp_RB_Color, BorderLayout.NORTH)

    var jslider_SpeedControl : JSlider_SpeedControl = JSlider_SpeedControl(jp_BouncingBall, panel_width, panel_control_height, border_thickness)
    jslider_SpeedControl.setBorder(BorderFactory.createTitledBorder("Speed Control"))
    jslider_SpeedControl.setVisible(true)
    jf.add(jslider_SpeedControl, BorderLayout.SOUTH)
    jf.setSize(panel_width+border_thickness, panel_y_colorControl + panel_ball_height + panel_y_speedControl)
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    jf.getRootPane().setBorder(BorderFactory.createMatteBorder(border_thickness, border_thickness, border_thickness, border_thickness, Color.BLUE))

    jf.setVisible(true)
}