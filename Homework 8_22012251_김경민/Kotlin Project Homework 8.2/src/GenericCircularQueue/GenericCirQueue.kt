package GenericCircularQueue

class GenericCirQueue<E : Comparable<E>>(val name: String, var Q_capa: Int) {
    private var DLL_E: LinkedList<E> = LinkedList()  // 연결 리스트로 Queue 구현
    private var size = 0  // 현재 Queue의 크기

    // Queue에 데이터를 추가 (enque)
    fun enque(entry: E) {
        if (size >= Q_capa) {
            println("Queue is full")
            return
        }
        DLL_E.insertBack(entry)
        size++
    }

    // Queue에서 데이터를 제거 (deque)
    fun deque(): E? {
        if (size == 0) {
            println("Queue is empty")
            return null
        }
        val removedElement = DLL_E.removeFront()
        size--
        return removedElement
    }

    // Queue의 현재 상태를 문자열로 반환 (toString)
    override fun toString(): String {
        return if (size == 0) {
            "Queue is Empty!!\n"
        } else {
            DLL_E.print()
            ""
        }
    }
}
