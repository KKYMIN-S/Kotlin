package HeapPriQ

open class CompleteBinaryTree<E> : GenericArray<E> {
    var name: String? = null
    var CBT_capacity = 0
    var endIndex = -1
    constructor(capacity: Int) : super(capacity) {
    // TODO Auto-generated constructor stub
    }

    constructor(name: String?, capacity: Int) : super(capacity) {
        this.name = name
        CBT_capacity = capacity
        endIndex = -1
    }
    fun endIndex(): Int {
        return endIndex
    }
    fun addAtEnd(entry: E?): Int {
        if (endIndex >= CBT_capacity) {
            System.out.printf("%s is full now !!", name)
            return -1
        }
        endIndex++
        this.genArray?.set(endIndex, entry)
        return endIndex
    }
    fun getCBTRoot(): Any? {
        return this.genArray?.get(1)
    }
    fun removeCBTEnd() {
        endIndex--
        this.size-- // size is defined in GenericArray<E>
    }
    protected fun parentIndex(index: Int): Int {
        return ( index -1) / 2
    }
    protected fun leftChildIndex(index: Int): Int {
        return (index * 2 + 1)
    }
    protected fun rightChildIndex(index: Int): Int {
        return (index * 2 + 2)
    }
    protected fun hasLeftChild(index: Int): Boolean {
        return (index * 2 + 1) <= endIndex
    }
    protected fun hasRightChild(index: Int): Boolean {
        return (index * 2 + 2) <= endIndex
    }
}
