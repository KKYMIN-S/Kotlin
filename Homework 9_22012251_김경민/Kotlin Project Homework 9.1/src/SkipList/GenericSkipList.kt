package SkipList

import java.util.*

class GenericSkipList<E : Comparable<E>> : MutableIterator<E> {
    var _name: String? = null
    private var _head: QuadNode<E>?
    private var size = 0

    constructor (name: String?) {
        _name = name
        _head = QuadNode<E>()
        size = 0
    }
    fun getName(): String? {
        return _name
    }
    operator fun get(key: E): E? {
        checkValidity(key)
        val node = findNode(key)
        return if (node!!.getEntity()!!.compareTo(key) === 0)
            node!!.getEntity() else null
    }

    operator fun contains(key: E): Boolean {
        return this[key] != null
    }
    fun size(): Int {
        return size
    }
    fun empty(): Boolean {
        return size == 0
    }

    protected fun findNode(key: E): QuadNode<E>? {
        var node = _head
        var next: QuadNode<E>? = null
        var down: QuadNode<E>? = null
        var nodeKey: E? = null
        while (true) {
            // Searching nearest (less than or equal) node with special key
            next = node!!.getNext()
            while (next != null && lessThanOrEqual(next.getEntity(), key)) {
                node = next
                next = node.getNext()
            }
            nodeKey = node!!.getEntity()
            if (nodeKey != null && nodeKey.compareTo(key) == 0) break
            // Descend to the bottom for continue search
            down = node.getDown()
            node = down ?: break
        }
        return node
    }

    protected fun checkValidity(ent: E?) {
        requireNotNull(ent) { "Key must not be null!" }
    }
    protected fun lessThanOrEqual(a: E?, b: E): Boolean {
        return a!!.compareTo(b) <= 0
    }
    protected fun insertNext(x: QuadNode<E>?, y: QuadNode<E>?) {
        y!!.setPrev(x)
        y!!.setNext(x!!.getNext())
        if (x.getNext() != null) x.getNext()!!.setPrev(y)
        x.setNext(y)
    }
    protected fun insertAbove(x: QuadNode<E>, z: QuadNode<E>) {
        x!!.setUp(z)
        z.setDown(x)
    }
    override fun hasNext(): Boolean {
        TODO("Not yet implemented")
    }
    override fun next(): E {
        TODO("Not yet implemented")
    }
    override fun remove() {
        TODO("Not yet implemented")
    }

    fun add(key: E) {
        checkValidity(key)
        var node = findNode(key)
        if (node!!.getEntity() != null && node.getEntity()!!.compareTo(key) === 0) {
            node.setEntity(key)
            return
        }
        var newNode : QuadNode<E> = QuadNode<E>(key, node.getLevel())
        insertNext(node, newNode)
        // Decide level according to the probability function
        var currentLevel = node.getLevel()
        var headLevel = _head!!.getLevel()
        val rand = Random()
        while (rand.nextDouble() < 0.5) { //
            // building a new level
            if (currentLevel >= headLevel) {
                val newHead = QuadNode<E>(headLevel + 1)
                insertAbove(this._head!!, newHead)
                _head = newHead
                headLevel = _head!!.getLevel()
            }
            // copy node and newNode to the upper level
            while (node!!.getUp() == null) {
                node = node.getPrev()
            }
            node = node.getUp()
            val tmp = QuadNode<E>(key, node!!.getLevel())
            insertNext(node, tmp)
            insertAbove(newNode, tmp)
            newNode = tmp
            currentLevel++
        }
        size++
    }

    fun remove(key: E) {
        checkValidity(key)
        var node = findNode(key)
        if (node == null || node.getEntity()!! .compareTo(key) !== 0
        ) throw NoSuchElementException("The key is not exist!")
        // Move to the bottom
        while (node!!.getDown() != null) node = node.getDown()
        // Because node is on the lowest level,
        // so we need remove by down-top
        var prev : QuadNode<E>? = null
        var next : QuadNode<E>? = null
        while (node != null) {
            prev = node.getPrev()
            next = node.getNext()
            prev?.setNext(next)
            next?.setPrev(prev)
            node = node.getUp()
        }
        // Adjust head
        while (_head!!.getNext() == null && _head!!.getDown() != null) {
            _head = _head!!.getDown()
            _head!!.setUp(null)
        }
        size--
    }

    override fun toString(): String {
        var str : String = _name as String
        str += String.format("(size = %3d) : ", size)
        if (size == 0) {
            str += "Empty"
            return str
        } else {
            str += "["
        }
        var node: QuadNode<E>? = _head
        // Move into the lower left bottom
        while (node!!.getDown() != null) node = node.getDown()
        while (node!!.getPrev() != null) node = node.getPrev()
        // Head node with each level the key is null
        // so need to move into the next node
        if (node.getNext() != null) node = node.getNext()
        while (node != null) {
            str += java.lang.String.format("%3s", node)
            node = node.getNext()
        }
        str += " ]"
        return str
    }
}




