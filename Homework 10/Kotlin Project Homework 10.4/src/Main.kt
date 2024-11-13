/* Kotlin Project Homework 10.4
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 11. 11.
 * TornadoFX 기반 Simple Calculator
 */

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

fun main() {
    launch<CalculatorApp>()
}

class CalculatorApp : App(MainView::class)
class MainView : View("Simple Calculator") {
    private var number1Field: TextField by singleAssign()
    private var number2Field: TextField by singleAssign()
    private var resultLabel: Label by singleAssign() // 결과 표시
    override val root = vbox(10) {
        paddingAll = 20.0
        prefWidth = 600.0

        label("Enter two numbers") {
            style {
                fontSize = 20.px
                fontWeight = FontWeight.BOLD
                fontFamily = "Tahoma" }
        }
        hbox(10) {
            label("Number 1:") { style {
                fontSize = 18.px
                fontFamily = "Tahoma" }
            }
            number1Field = textfield() {
                style {
                    fontSize = 18.px
                    fontFamily = "Tahoma" }
                alignment = Pos.CENTER_RIGHT
                prefWidth = 400.0
            }
        }
        hbox(10) {
            label("Number 2:") {
                style {
                    fontSize = 18.px
                    fontFamily = "Tahoma" }
            }
            number2Field = textfield() {
                style {
                    fontSize = 18.px
                    fontFamily = "Tahoma" }
                alignment = Pos.CENTER_RIGHT
                prefWidth = 400.0
            }
        }
        // 연산 버튼 (Add, Subtract, Multiply, Divide)
        hbox(10) {
            button("Add") { action { calculate("+") }
                style {
                    fontSize = 16.px
                    fontFamily = "Tahoma"
                    backgroundColor += Color.LIGHTGREEN
                    textFill = Color.DARKGREEN
                }
            }
            button("Subtract") {
                action { calculate("-") }
                style {
                    fontSize = 16.px
                    fontFamily = "Tahoma"
                    backgroundColor += Color.LIGHTCORAL
                    textFill = Color.DARKRED
                }
            }
            button("Multiply") {
                action { calculate("*") }
                style {
                    fontSize = 16.px
                    fontFamily = "Tahoma"
                    backgroundColor += Color.LIGHTBLUE
                    textFill = Color.DARKBLUE
                }
            }
            button("Divide") {
                action { calculate("/") }
                style {
                    fontSize = 16.px
                    fontFamily = "Tahoma"
                    backgroundColor += Color.LIGHTGOLDENRODYELLOW
                    textFill = Color.DARKGOLDENROD
                }
            }
        }
        resultLabel = label("Result: ") {
            style {
                fontSize = 20.px
                fontFamily = "Tahoma"
                fontWeight = FontWeight.BOLD
            }
        }
    }
    private fun calculate(operation: String) {
        val number1 = number1Field.text.toDoubleOrNull()
        val number2 = number2Field.text.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            // 연산 유형에 따라 계산 수행
            val result = when (operation) {
                "+" -> number1 + number2
                "-" -> number1 - number2
                "*" -> number1 * number2
                "/" -> if (number2 != 0.0) number1 / number2 else "Cannot divide by zero" else -> "Invalid operation"
            }
            resultLabel.text = when (result) {
                is String -> "Result: $result"
                else -> "Result: $number1 $operation $number2 => $result"
            }
        } else {
            resultLabel.text = "Please enter valid numbers."
        }
    }
}