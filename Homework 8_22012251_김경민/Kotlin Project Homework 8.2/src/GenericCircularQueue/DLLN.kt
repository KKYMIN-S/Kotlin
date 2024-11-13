package GenericCircularQueue

class DLLN<T> {
    var data: T? = null // 노드가 저장하는 데이터
    var next: DLLN<T>? = null // 다음 노드를 가리키는 포인터
    var prev: DLLN<T>? = null // 이전 노드를 가리키는 포인터
    constructor() {
        this.data = null
        this.next = null
        this.prev = null
    }
    constructor(data: T) {
        this.data = data // 전달받은 데이터를 노드에 저장
        this.next = null
        this.prev = null
    }
    fun getDLLNData(): T? {
        return data as T // 데이터 값을 반환
    }
    fun setDLLNData(data: T?) {
        this.data = data // 전달받은 데이터로 설정
    }
    fun getDLLNNext(): DLLN<T>? {
        return this.next // 다음 노드를 반환
    }
    fun getDLLNPrev(): DLLN<T>? {
        return this.prev // 이전 노드를 반환
    }
    fun setDLLNNext(next: DLLN<T>?) {
        this.next = next!! // 전달받은 다음 노드로 설정 (non-null 강제 적용)
    }
    fun setDLLNPrev(prev: DLLN<T>?) {
        this.prev = prev!! // 전달받은 이전 노드로 설정 (non-null 강제 적용)
    }
}