package GenericCircularQueue

class LinkedList<T> {
    private var head: DLLN<T> = DLLN<T>()
    private var tail: DLLN<T> = DLLN<T>()
    private var count = 0
    constructor() {
        this.head.next = this.tail
        this.tail.prev = this.head
        this.count = 0
    }
    /** Add new DLLN with value to the list */
    fun insertFront(data: T) { // List의 앞쪽에 새로운 노드를 삽입하는 함수
        val newDLLN = DLLN<T>(data)
        val nextDLLN = head!!.getDLLNNext()
        newDLLN.setDLLNNext(nextDLLN)
        head!!.setDLLNNext(newDLLN)
        nextDLLN!!.setDLLNPrev(newDLLN)
        count++
    }
    fun insertBack(data: T) { // List의 뒤쪽에 새로운 노드를 삽입하는 함수
        val newDLLN = DLLN<T>(data)
        val prevDLLN = this.tail!!.getDLLNPrev()
        prevDLLN!!.setDLLNNext(newDLLN)
        newDLLN.setDLLNPrev(prevDLLN)
        this.tail!!.setDLLNPrev(newDLLN)
        newDLLN.setDLLNNext(tail!!)
        count++
    }
    fun removeFront(): T? { // List의 앞쪽에서 노드를 제거하는 함수
        val tobeRemovedDLLN = head!!.getDLLNNext()
        val nextDLLN = tobeRemovedDLLN!!.getDLLNNext()
        head!!.setDLLNNext(nextDLLN)
        nextDLLN!!.setDLLNPrev(this.head)
        count--
        return tobeRemovedDLLN.getDLLNData()
    }
    fun getCount(): Int { // List의 현재 노드 개수를 반환하는 함수
        return count
    }
    /** Remove values from the list */
    fun remove(data: T) {
        var current: DLLN<T>? = this.head.getDLLNNext()
        while (!current!!.equals(this.tail) ) {
            if (current.getDLLNData()!!.equals(data)) {
                var currentPrev: DLLN<T>? = current.getDLLNPrev()
                var currentNext: DLLN<T>? = current.getDLLNNext()
                currentPrev!!.setDLLNNext(currentNext)
                currentNext!!.setDLLNPrev(currentPrev)
                count--
                return
            }
            else
                current = current.getDLLNNext()
        } // if LinkedList is empty, just return
        println(" => Given data (to be deleted) was not found in the LinkedList")
    }
    fun print() {
        if (count <= 0) {
            print("DLL is empty !!")
            return
        }
        var crrDLLN: DLLN<T>? = head!!.getDLLNNext()
        print("[")
        while (crrDLLN != tail) {
            if (crrDLLN!!.getDLLNData() != null) {
                if (crrDLLN.getDLLNNext() != tail) {
                    print(crrDLLN.getDLLNData().toString() + ",")
                } else {
                    print(crrDLLN.getDLLNData().toString() + "]")
                }
                crrDLLN = crrDLLN.getDLLNNext()
            }
        }
        println()
    }
}