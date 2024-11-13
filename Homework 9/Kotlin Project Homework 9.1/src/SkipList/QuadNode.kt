package SkipList

class QuadNode<E : Comparable<E>> {
    private var _entity: E? = null // 노드가 저장하는 실제 데이터
    private var level = 0 // SkipList의 level
    private var up: QuadNode<E>? = null
    private var down:QuadNode<E>? = null
    private var next:QuadNode<E>? = null
    private var prev:QuadNode<E>? = null

    constructor() { _entity = null
        this.level = 0
    }
    constructor(level: Int) { _entity = null
        this.level = level
    }
    constructor(ent: E, level: Int) { _entity = ent as E
        this.level = level
    }
    constructor(ent: Any?, level: Int) { // Any?형 생성자
        _entity = ent as E
        this.level = level
    }
    override fun toString(): String {
        var str: String
        str = ""
        str += if (_entity == null) "None" else _entity.toString()
        return str
    }
    fun getEntity(): E? {
        return _entity
    }
    fun setEntity(ent: E) { _entity = ent
    }
    fun getLevel(): Int {
        return level
    }
    fun setLevel(level: Int) {
        this.level = level
    }
    fun getUp(): QuadNode<E>? {
        return up
    }
    fun setUp(up: QuadNode<E>?) {
        this.up = up
    }
    fun getDown(): QuadNode<E>? {
        return this.down }
    fun setDown(down: QuadNode<E>?) {
        this.down = down }
    fun getNext(): QuadNode<E>? {
        return next }
    fun setNext(next: QuadNode<E>?) {
        this.next = next }
    fun getPrev(): QuadNode<E>? {
        return prev }
    fun setPrev(prev: QuadNode<E>?) {
        this.prev = prev
    }
}
