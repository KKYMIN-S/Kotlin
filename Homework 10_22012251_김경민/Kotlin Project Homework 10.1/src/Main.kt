/* Kotlin Project Homework 10.1
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 11. 11.
 * Kotlin Swing 기반 사람 (person)의 기본 속성 정보 (이름, 주민등록번호, 연락처) 입력 기능 GUI 프로그램 구현
 */

import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.border.EmptyBorder

class JFrame_withGridLayout : JFrame() {
    private val nameField = JTextField() // 이름 입력 Field
    private val regIDField = JTextField() // 주민등록번호 입력 Field
    private val telNoField = JTextField() // 전화번호 입력 Field
    private val resultLabel = JLabel("Entered: ") // 결과 출력

    init {
        title = "Person Information Input"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(400, 200)

        val mainPanel = JPanel(GridLayout(4, 2, 5, 5)) // GridLayout

        mainPanel.border = EmptyBorder(10, 10, 10, 10) // 상하좌우 여백 추가
        mainPanel.add(JLabel("Name"))
        mainPanel.add(nameField)
        mainPanel.add(JLabel("RegID"))
        mainPanel.add(regIDField)
        mainPanel.add(JLabel("Tel No"))
        mainPanel.add(telNoField)

        val inputButton = JButton("Input")
        inputButton.addActionListener(InputButtonListener())
        mainPanel.add(inputButton)

        layout = BorderLayout()
        add(mainPanel, BorderLayout.CENTER)

        val resultPanel = JPanel()
        resultPanel.add(resultLabel)
        add(resultPanel, BorderLayout.SOUTH)

        isVisible = true
    }

    inner class InputButtonListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            val name = nameField.text
            val regID = regIDField.text
            val telNo = telNoField.text
            resultLabel.text = "Entered: Name=$name, RegID=$regID, Tel=$telNo"
        }
    }
}

fun main() {
    JFrame_withGridLayout()
}
