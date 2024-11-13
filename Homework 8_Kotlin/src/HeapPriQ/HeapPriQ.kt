package HeapPriQ

class HeapPriQ<E : Comparable<E>> : CompleteBinaryTree<E> {
    val CBT_ROOT = 0
    constructor(name: String?, capacity: Int) : super(name, capacity) { }

    fun isEmpty(): Boolean {
        return size == 0
    }
    fun isFull(): Boolean {
        return size >= capacity
    }
    fun size(): Int {
        return size
    }
    fun insert(entry: E?): Int {
        var index: Int
        var prIndex: Int
        if (isFull()) {
            print("%s is full !!\n".format(this.name))
            return size()
        }

        index = addAtEnd(entry)
        /* up-heap bubbling */
        while (index != CBT_ROOT) {
            prIndex = parentIndex(index)
            var curE: E
            var prE: E
            curE = genArray!![index] as E
            prE = genArray!![prIndex] as E
            var curKey: Int
            var prKey: Int
            // curKey = curEv.getPriority()
            // prKey = prEv.getPriority()
            if (curE >= prE) {
                break
            } else {
                genArray!![prIndex] = curE
                genArray!![index] = prE
                index = prIndex
            }
        }
        size++
        return size()
    }

    fun removeHeapMin(): E? {
        if (endIndex == -1) {
            return null
        }
        val minE: E = getCBTRoot() as E
        if (size == 1) {
            removeCBTEnd()
        } else {
            var curE: E
            var chE: E
            var rchE: E
            var curIndex: Int
            var chIndex: Int
            var rchIndex: Int
            var curPri: Int

            genArray!![CBT_ROOT] = genArray!![endIndex]
            curIndex = CBT_ROOT
            endIndex--

            /* down-heap bubbling */
            while (hasLeftChild(curIndex)) {
                curE = genArray!![curIndex] as E
                chIndex = leftChildIndex(curIndex)
                rchIndex = rightChildIndex(curIndex)
                chE = genArray!![chIndex] as E
                if (hasRightChild(curIndex)) {
                    rchE = genArray!![rchIndex] as E
                    if (chE > rchE) { // if right child has higher priority that left child
                        chE = genArray!![rchIndex] as E
                        chIndex = rchIndex
                    }
                }
                if (curE > chE) { // if child Ev's priority is higher than current Ev
                    /* swap curEv and chEv */
                    genArray!![chIndex] = curE
                    genArray!![curIndex] = chE
                }
                curIndex = chIndex
            }
        }
        size--
        return minE
    }
}
