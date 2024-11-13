package MyDataStructure

class CirQueue (var Q_capa : Int) { // FIFO
    private var int_array = IntArray(Q_capa)
    private var front = 0 // initial position
    private var back = 0
    private var num_data = 0 // Queue에 저장된 데이터 수

    fun enque(entry: Int): Int? {
        if (isFull()) {
            return null
        }
        int_array[back] = entry  // 현재 back 위치에 데이터 저장
        back = (back + 1) % Q_capa  // back을 다음 위치로 이동
        num_data++  // 저장된 데이터 수 증가
        return entry
    }

    fun deque(): Int? {
        if (isEmpty()) {
            return null
        }
        val entry = int_array[front]  // 현재 front 위치의 데이터를 가져옴
        front = (front + 1) % Q_capa  // front를 다음 위치로 이동
        num_data--  // 저장된 데이터 수 감소
        return entry
    }

    fun isEmpty(): Boolean { // Queue가 비어있는 경우
        return if (num_data <= 0) true else false
    }

    fun isFull(): Boolean { // Queue가 가득 찬 경우
        return if (num_data >= Q_capa) true else false
    }

    override fun toString(): String {
        var str = ""
        if (num_data == 0) {
            str = "Circular queue is Empty !!"
        } else {
            var idx = front
            for (i in 0 until num_data) {
                str += "%d ".format(int_array[idx])
                idx = (idx + 1) % Q_capa
            }
        }
        return str
    }
}