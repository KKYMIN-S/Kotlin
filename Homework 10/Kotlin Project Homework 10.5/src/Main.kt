/* Kotlin Project Homework 10.5
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 11. 11.
 * TornadoFX Animation
 */

// TornadoFX 사용을 위한 관련 Module
import javafx.animation.AnimationTimer
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.* // TornadoFX
import javafx.application.Platform
import javafx.scene.control.Label

fun main() {
    launch<MyApp>()
}

class MyApp : App(MyView::class) { // TornadoFX App 기본 설정 담당 class
    override fun start(stage: javafx.stage.Stage) {
        super.start(stage)
        stage.titleProperty().unbind() // 기존 바인딩 해제
        stage.title = "TornadoFX-based Kotlin StopWatch"
    }
}

class MyView : View() { // view를 정의하는 class
    private var startTime: Long = 0L
    private var elapsedTime: Long = 0L
    private var running = false // 타이머 실행 여부 확인
    private val timerLabel = Label("00:00:00:000")
    private lateinit var animationTimer: AnimationTimer

    init {
        // animationTimer 객체를 생성하여 타이머의 시간을 업데이트하는 기능 정의
        animationTimer = object : AnimationTimer() {
            override fun handle(now: Long) {
                val nowTime = System.currentTimeMillis()
                val updatedTime = nowTime - startTime + elapsedTime // 경과 시간 계산
                Platform.runLater { // UI 업데이트를 위해 JavaFX Thread에서 실행
                    timerLabel.text = formatTime(updatedTime)
                }
            }
        }
    }

    override val root = vbox(20) {
        paddingAll = 20.0
        alignment = Pos.CENTER
        timerLabel.apply { // 타이머 라벨
            font = Font.font("Tahoma", 40.0) // 타이머 라벨의 폰트 설정
            textFill = Color.RED // 타이머 라벨의 텍스트 색상 설정
        }
        add(timerLabel)

        hbox(20) { alignment = Pos.CENTER

            button("Start") { // Start 버튼
                prefWidth = 100.0
                prefHeight = 50.0
                style {
                    fontSize = 20.px
                    backgroundColor += Color.GREEN
                    textFill = Color.WHITE
                }
                action { startTimer() }
            }

            button("Stop") { // Stop 버튼
                prefWidth = 100.0
                prefHeight = 50.0
                style {
                    fontSize = 20.px
                    backgroundColor += Color.ORANGE
                    textFill = Color.WHITE
                }
                action { stopTimer() }
            }

            button("Reset") { // Reset 버튼
                prefWidth = 100.0
                prefHeight = 50.0
                style {
                    fontSize = 20.px
                    backgroundColor += Color.RED
                    textFill = Color.WHITE
                }
                action { resetTimer() }
            }
        }
    }

    // 타이머 시작
    private fun startTimer() {
        if (!running) {
            startTime = System.currentTimeMillis()
            animationTimer.start() // animationTimer 시작
            running = true
        }
    }
    // 타이머 중지
    private fun stopTimer() {
        if (running) {
            animationTimer.stop() // animationTimer 중지
            elapsedTime += System.currentTimeMillis() - startTime // 경과 시간 누적
            running = false
        }
    }
    // 타이머 리셋
    private fun resetTimer() {
        animationTimer.stop() // animationTimer 중지
        startTime = 0L
        elapsedTime = 0L
        running = false
        timerLabel.text = "00:00:00:000"
    }
    // 시간을 Hour:Min:Sec:MilliSec 형식으로 변환하는 함수
    private fun formatTime(timeInMillis: Long): String {
        val hours = timeInMillis / 3_600_000
        val minutes = (timeInMillis % 3_600_000) / 60_000
        val seconds = (timeInMillis % 60_000) / 1_000
        val millis = timeInMillis % 1_000
        return String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, millis)
    }
}
