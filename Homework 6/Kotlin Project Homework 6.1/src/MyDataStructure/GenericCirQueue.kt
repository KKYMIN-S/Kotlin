package MyDataStructure

class GenericCirQueue<E : Comparable<E>>(val name: String, var Q_capa: Int) {
    private var array: Array<Any?> = arrayOfNulls(Q_capa) // Generic 배열 생성
    private var front = 0 // initial position
    private var back = 0
    private var num_data = 0 // Queue에 저장된 데이터 수

    fun enque(entry: E): Int? { // enque, 데이터 삽입
        if (isFull()) {
            println("$name is full. Cannot enqueue.")
            return null // null값 반환
        }
        array[back] = entry  // 현재 back 위치에 데이터 저장
        back = (back + 1) % Q_capa  // back을 다음 위치로 이동
        num_data++  // 저장된 데이터 수 증가
        return 1 // 성공적으로 추가된 경우 1 반환
    }

    fun deque(): E? { // deque, 데이터 추출
        if (isEmpty()) {
            println("$name is empty. Cannot dequeue.")
            return null // null값 반환
        }
        val entry = array[front] as E  // 현재 front 위치의 데이터를 가져옴
        front = (front + 1) % Q_capa  // front를 다음 위치로 이동
        num_data--  // 저장된 데이터 수 감소
        return entry // entry 반환
    }

    fun isEmpty(): Boolean { // Queue가 비워져 있는지 확인
        return num_data == 0 // num_data가 0이면 True 반환
    }

    fun isFull(): Boolean { // Queue가 가득 차 있는지 확인
        return num_data >= Q_capa // num_data가 Queue의 용량보다 크거나 같은 경우 True 반환
    }

    override fun toString(): String { // Queue 상태 문자열로 반환
        var str = ""
        if (num_data == 0) {
            str = "$name is Empty !!"
        } else {
            var idx = front
            for (i in 0 until num_data) {
                str += " ${array[idx]} "
                idx = (idx + 1) % Q_capa
            }
        }
        return str
    }
}