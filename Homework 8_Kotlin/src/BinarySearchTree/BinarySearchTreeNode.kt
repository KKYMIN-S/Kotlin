package BinarySearchTree

class BinarySearchTreeNode<E> {
    private var _entry: E? = null
    private var _parent: BinarySearchTreeNode<E>? = null
    private var _leftChild: BinarySearchTreeNode<E>? = null
    private var _rightChild: BinarySearchTreeNode<E>? = null

    constructor() {
        _parent = null
        _leftChild =  null
        _rightChild = null
    }

    constructor(entry: E?) {
        _entry = entry
        _parent = null
        _leftChild = null
        _rightChild = null
    }

    fun getEntry(): E? {
        return _entry
    }
    fun setEntry(newEntry: E) {
        _entry = newEntry
    }
    fun getParent(): BinarySearchTreeNode<E>? {
        return _parent
    }
    fun getLeftChild(): BinarySearchTreeNode<E>? {
        return _leftChild
    }
    fun getRightChild(): BinarySearchTreeNode<E>? {
        return _rightChild
    }
    fun setParent(prBSTN: BinarySearchTreeNode<E>?) {
        _parent = prBSTN
    }
    fun setLeftChild(leftChildBSTN: BinarySearchTreeNode<E>?) {
        _leftChild = leftChildBSTN
    }
    fun setRightChild(rightChildBSTN: BinarySearchTreeNode<E>?) {
        _rightChild = rightChildBSTN
    }
    fun compareKey(otherEntry: E): Int {
        var cmpResult = 0
        if (_entry is Int) {
            cmpResult = _entry as Int - otherEntry as Int
        } else if (_entry is Double) {
            cmpResult =
                if (_entry as Double > otherEntry as Double)
                    1
                else if ((_entry as Double) < otherEntry as Double)
                    -1
                else
                    0
        } else if (_entry is String) {
            cmpResult = (_entry as String).compareTo((otherEntry as String)!!)
        } else {
            println("compareKey() is not defined for entry_type E !!")
        }
        return cmpResult
    }
}

