package HeapPriQ

class Task(private val priority: Int, private val title: String) {

    // 출력 연산자
    override fun toString(): String {
        return "Task($priority, $title)"
    }
}