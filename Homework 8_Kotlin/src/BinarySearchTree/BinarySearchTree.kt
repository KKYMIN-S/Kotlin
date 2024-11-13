package BinarySearchTree

import java.io.FileWriter
import java.io.IOException
import BinarySearchTree.BinarySearchTreeNode

class BinarySearchTree<E> {
    private var _name: String? = null
    private var _root: BinarySearchTreeNode<E>? = null
    private var _num_entry = 0

    constructor(bst_nm: String) {
        _name = bst_nm
        _root = null
        _num_entry = 0
    }

    fun size(): Int {
        return _num_entry
    }

    fun isEmpty(): Boolean {
        return _num_entry == 0
    }

    fun getRoot(): BinarySearchTreeNode<E>? {
        return _root
    }

    fun setRoot(newBSTroot: BinarySearchTreeNode<E>?): BinarySearchTreeNode<E>? {
        _root = newBSTroot
        return _root
    }

    private fun _insertInOrder(subRoot_bstn: BinarySearchTreeNode<E>?, pr_bstn: BinarySearchTreeNode<E>?, newEntry: E): BinarySearchTreeNode<E>? {
        val new_bstn: BinarySearchTreeNode<E>
        var bstn: BinarySearchTreeNode<E>? = null
        val leftChild: BinarySearchTreeNode<E>?
        val rightChild: BinarySearchTreeNode<E>?
        return if (subRoot_bstn == null) {
            new_bstn = BinarySearchTreeNode(newEntry)
            if (_num_entry == 0)
                _root = new_bstn
            new_bstn.setParent(null)
            new_bstn.setLeftChild(null)
            new_bstn.setRightChild(null)
            _num_entry++
            new_bstn
        } else { // subRoot_bstn != null
            if (subRoot_bstn.compareKey(newEntry) > 0) {
                leftChild = subRoot_bstn.getLeftChild()
                bstn = _insertInOrder(leftChild, subRoot_bstn, newEntry)
                if (bstn != null) {
                    subRoot_bstn.setLeftChild(bstn)
                    bstn.setParent(subRoot_bstn)
                }
                null
            } else if (subRoot_bstn.compareKey(newEntry) < 0) {
                rightChild = subRoot_bstn.getRightChild()
                bstn = _insertInOrder(rightChild, subRoot_bstn, newEntry)
                if (bstn != null) {
                    subRoot_bstn.setRightChild(bstn)
                    bstn.setParent(subRoot_bstn)
                }
                null
            } else {
                subRoot_bstn.setEntry(newEntry)
                bstn = subRoot_bstn
                null
            }
        }
    }

    fun insert(newEntry: E): BinarySearchTreeNode<E>? {
        val bstn: BinarySearchTreeNode<E>?
        bstn = _insertInOrder(_root, null, newEntry)
        return bstn
    }

    override fun toString(): String {
        val array_values = ArrayList<E?>()
        var str = String.format("%s : ", _name)
        _inOrderTraversal(_root, array_values)
        for (entry in array_values) {
            str += entry.toString() + " "
        }
        return str
    }

    fun _inOrderTraversal(curBSTN: BinarySearchTreeNode<E>?, array_values: ArrayList<E?>) {
        if (curBSTN!!.getLeftChild() != null) {
            _inOrderTraversal(curBSTN.getLeftChild(), array_values)
        }
        array_values.add(curBSTN.getEntry())
        if (curBSTN.getRightChild() != null) {
            _inOrderTraversal(curBSTN.getRightChild(), array_values)
        }
    }

    fun _findMin(bstn: BinarySearchTreeNode<E>?): BinarySearchTreeNode<E>? {
        var bstn = bstn
        if (bstn!!.getLeftChild() != null) {
            bstn = bstn.getLeftChild()
            while (bstn!!.getLeftChild() != null) {
                bstn = bstn.getLeftChild()
            }
        }
        return bstn
    }

    fun _findMax(bstn: BinarySearchTreeNode<E>?): BinarySearchTreeNode<E>? {
        var bstn = bstn
        if (bstn!!.getRightChild() != null) {
            bstn = bstn.getRightChild()
            while (bstn!!.getRightChild() != null) {
                bstn = bstn.getRightChild()
            }
        }
        return bstn
    }

    fun _search(subRoot: BinarySearchTreeNode<E>?, searchKey: E): BinarySearchTreeNode<E>? {
    // implement recursive function for search with in-order-traversal
        var bstn_result: BinarySearchTreeNode<E>? = null
        if (subRoot == null) return null
        return if (subRoot.compareKey(searchKey) === 0)
            subRoot
        else if (subRoot.compareKey(searchKey) > 0) {
            bstn_result = _search(subRoot.getLeftChild(), searchKey)
            bstn_result
        } else {
            bstn_result = _search(subRoot.getRightChild(), searchKey)
            bstn_result
        }
    }

    fun search(searchKey: E): BinarySearchTreeNode<E>? {
        var bstn_result: BinarySearchTreeNode<E>? = null
        bstn_result = _search(_root, searchKey)
        return bstn_result
    }

    fun _printBST_withDepth(curBSTN: BinarySearchTreeNode<E>?, level: Int) {
        if (curBSTN == null) return
        if (curBSTN.getRightChild() != null) {
            _printBST_withDepth(curBSTN.getRightChild(), level + 1)
        }
        var str: String? = ""
        for (i in 0 until level) {
            str += " "
        }
        str += curBSTN.getEntry()
        println(str)
        if (curBSTN.getLeftChild() != null) {
            _printBST_withDepth(curBSTN.getLeftChild(), level + 1)
        }
    }

    fun printBST_withDepth() {
        var bstn: BinarySearchTreeNode<E>
        if (_num_entry == 0) {
            println("BinarySearchTree(%s) is Empty !!\n")
            return
        }
        val str = String.format("BinarySearchTree(%s, num_entry = %d) :\n", _name, _num_entry)
        println(str)
        _printBST_withDepth(_root, 0)
    }
    fun _getHeight(bstn: BinarySearchTreeNode<E>?): Int {
        var height = 0
        val height_Lc: Int
        val height_Rc: Int
        if (bstn != null) {
            height_Lc = _getHeight(bstn.getLeftChild())
            height_Rc = _getHeight(bstn.getRightChild())
            height = if (height_Lc > height_Rc) 1 + height_Lc else 1 + height_Rc
        }
        return height
    }
    fun _getHeightDiff(bstn: BinarySearchTreeNode<E>?): Int {
        var heightDiff = 0
        if (bstn != null) {
            heightDiff = _getHeight(bstn.getLeftChild()) - _getHeight(bstn.getRightChild())
        }
        return heightDiff
    }
    fun _deleteBSTN(toBeDeleted: BinarySearchTreeNode<E>?): BinarySearchTreeNode<E>? {
        var newSubRoot: BinarySearchTreeNode<E>? = null
        var temp: BinarySearchTreeNode<E>
        var w: BinarySearchTreeNode<E>
        var wLc: BinarySearchTreeNode<E>
        if (toBeDeleted == null) return null
        if (toBeDeleted.getLeftChild() == null && toBeDeleted.getRightChild() == null) {
            newSubRoot = null
        } else if (toBeDeleted.getLeftChild() != null && toBeDeleted.getRightChild() == null) {
            newSubRoot = toBeDeleted.getLeftChild()
            newSubRoot!!.setParent(toBeDeleted.getParent())
        } else if (toBeDeleted.getLeftChild() == null && toBeDeleted.getRightChild() != null) {
            newSubRoot = toBeDeleted.getRightChild()
            newSubRoot!!.setParent(toBeDeleted.getParent())
        } else {
            val heightDiff = _getHeightDiff(toBeDeleted)
            val lChild = toBeDeleted.getLeftChild()
            val rChild = toBeDeleted.getRightChild()
            val parToBeDeleted = toBeDeleted.getParent()
            var ioPd: BinarySearchTreeNode<E>? = null
            val lcIoPd: BinarySearchTreeNode<E>?
            val parIoPd: BinarySearchTreeNode<E>?
            var ioSs: BinarySearchTreeNode<E>? = null
            val rcIoSs: BinarySearchTreeNode<E>?
            val parIoSs: BinarySearchTreeNode<E>?

            if (heightDiff > 0) {
                ioPd = _findMax(toBeDeleted.getLeftChild())
                lcIoPd = ioPd!!.getLeftChild()
                parIoPd = ioPd.getParent()
                if (ioPd.getParent() !== toBeDeleted) {
                    ioPd.setLeftChild(lChild)
                    parIoPd!!.setRightChild(lcIoPd)
                    lcIoPd?.setParent(parIoPd)
                }
                ioPd.setRightChild(rChild)
                ioPd.setParent(parToBeDeleted)
                newSubRoot = ioPd
            }
            else {
                ioSs = _findMin(toBeDeleted.getRightChild())
                rcIoSs = ioSs!!.getRightChild()
                parIoSs = ioSs.getParent()
                if (parIoSs !== toBeDeleted) {
                    ioSs.setRightChild(rChild)
                    parIoSs!!.setLeftChild(rcIoSs)
                    rcIoSs?.setParent(parIoSs)
                }
                ioSs.setLeftChild(lChild)
                ioSs.setParent(parIoSs)
                newSubRoot = ioSs
            }
            if (lChild !== ioPd) lChild!!.setParent(newSubRoot)
            if (rChild !== ioSs) rChild!!.setParent(newSubRoot)
        }
        if (toBeDeleted === _root) _root = newSubRoot
        _num_entry--
        return newSubRoot
    }
    fun _rotateLL(curSubRoot: BinarySearchTreeNode<E>?): BinarySearchTreeNode<E>? {
        val newSubRoot: BinarySearchTreeNode<E>?
        val BRC: BinarySearchTreeNode<E>?
        val curParent: BinarySearchTreeNode<E>?
        curParent = curSubRoot!!.getParent()
        newSubRoot = curSubRoot.getLeftChild()
        BRC = newSubRoot!!.getRightChild()
        curSubRoot.setLeftChild(BRC)
        BRC?.setParent(curSubRoot)
        newSubRoot.setRightChild(curSubRoot)
        newSubRoot.setParent(curParent)
        curSubRoot.setParent(newSubRoot)
        return newSubRoot
    }
    fun _rotateRR(curSubRoot: BinarySearchTreeNode<E>?): BinarySearchTreeNode<E>? {
        val newSubRoot: BinarySearchTreeNode<E>?
        val BLC: BinarySearchTreeNode<E>?
        val curParent: BinarySearchTreeNode<E>?
        curParent = curSubRoot!!.getParent()
        newSubRoot = curSubRoot.getRightChild()
        BLC = newSubRoot!!.getLeftChild()
        curSubRoot.setRightChild(BLC)
        BLC?.setParent(curSubRoot)
        newSubRoot.setLeftChild(curSubRoot)
        newSubRoot.setParent(curParent)
        curSubRoot.setParent(newSubRoot)
        return newSubRoot
    }
    fun _rotateLR(curSubRoot: BinarySearchTreeNode<E>): BinarySearchTreeNode<E>? {
        val subRoot: BinarySearchTreeNode<E>?
        val newSubRoot: BinarySearchTreeNode<E>?
        val A: BinarySearchTreeNode<E>?
        val B: BinarySearchTreeNode<E>?
        val C: BinarySearchTreeNode<E>?
        val BL: BinarySearchTreeNode<E>?
        val BR: BinarySearchTreeNode<E>?
        val curParent: BinarySearchTreeNode<E>?
        C = curSubRoot
        curParent = curSubRoot.getParent()
        A = C.getLeftChild()
        B = A!!.getRightChild()
        BL = B!!.getLeftChild()
        BR = B.getRightChild()
        subRoot = _rotateRR(A)
        newSubRoot = _rotateLL(C)
        newSubRoot!!.setParent(curParent)
        A.setParent(newSubRoot)
        C.setParent(newSubRoot)
        BL?.setParent(A)
        BR?.setParent(C)
        return newSubRoot
    }
    fun _rotateRL(curSubRoot: BinarySearchTreeNode<E>): BinarySearchTreeNode<E>? {
        val subRoot: BinarySearchTreeNode<E>?
        val newSubRoot: BinarySearchTreeNode<E>?
        val A: BinarySearchTreeNode<E>
        val B: BinarySearchTreeNode<E>?
        val C: BinarySearchTreeNode<E>?
        val BL: BinarySearchTreeNode<E>?
        val BR: BinarySearchTreeNode<E>?
        val curParent: BinarySearchTreeNode<E>?
        A = curSubRoot
        curParent = curSubRoot.getParent()
        C = A.getRightChild()
        B = A.getLeftChild()
        BL = B!!.getLeftChild()
        BR = B.getRightChild()
        subRoot = _rotateLL(C)
        newSubRoot = _rotateRR(A)
        newSubRoot!!.setParent(curParent)
        A.setParent(newSubRoot)
        C!!.setParent(newSubRoot)
        BL?.setParent(A)
        BR?.setParent(C)
        return newSubRoot
    }
    fun _reBalance(curSubRoot: BinarySearchTreeNode<E>): BinarySearchTreeNode<E>? {
        var newSubRoot: BinarySearchTreeNode<E>? = null
        var heightDiff = 0
        heightDiff = _getHeightDiff(curSubRoot)
        if (heightDiff > 1) { // left subtree is higher
            newSubRoot = if (_getHeightDiff(curSubRoot.getLeftChild()) > 0) { _rotateLL(curSubRoot)
            } else {
                _rotateLR(curSubRoot)
            }
        } else if (heightDiff < -1) {
            newSubRoot = if (_getHeightDiff(curSubRoot.getRightChild()) < 0) { _rotateRR(curSubRoot)
            } else {
                _rotateRL(curSubRoot)
            }
        }
        return newSubRoot
    }
    fun _insertAndRebalance(curSubRoot: BinarySearchTreeNode<E>?, curParent: BinarySearchTreeNode<E>?, newEntry: E): BinarySearchTreeNode<E>? {
        var curSubRoot = curSubRoot
        val newBSTN: BinarySearchTreeNode<E>
        val bstn: BinarySearchTreeNode<E>?
        val LC: BinarySearchTreeNode<E>?
        val RC: BinarySearchTreeNode<E>?
        if (curSubRoot == null) {
            newBSTN = BinarySearchTreeNode(newEntry)
            curSubRoot = newBSTN
            if (curParent != null) newBSTN.setParent(curParent)
            newBSTN.setLeftChild(null)
            newBSTN.setRightChild(null)
            _num_entry++
            return curSubRoot
        }
        val bstn_entry = curSubRoot.getEntry()
        if (curSubRoot.compareKey(newEntry) > 0) {
            LC = curSubRoot.getLeftChild()
            bstn = _insertAndRebalance(LC, curSubRoot, newEntry)
            if (bstn != null) {
                curSubRoot.setLeftChild(bstn)
                curSubRoot = _reBalance(curSubRoot)
            }
        } else {
            RC = curSubRoot.getRightChild()
            bstn = _insertAndRebalance(RC, curSubRoot, newEntry)
            if (bstn != null) {
                curSubRoot.setRightChild(bstn)
                curSubRoot = _reBalance(curSubRoot)
            }
        }
        return curSubRoot
    }
    fun insert_withRebalancing(newEntry: E) {
        val newSubRoot: BinarySearchTreeNode<E>?
        newSubRoot = _insertAndRebalance(_root, null, newEntry)
        if (newSubRoot != null)
            _root = newSubRoot
    }
}





