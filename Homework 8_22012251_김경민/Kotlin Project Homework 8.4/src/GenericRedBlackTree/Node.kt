package GenericRedBlackTree

enum class Color { RED, BLACK }
class Node<T : Comparable<T>>(
    var entry: T,
    var color: Color = Color.RED,
    var left: Node<T>? = null,
    var right: Node<T>? = null,
    var parent: Node<T>? = null
) {
    override fun toString(): String {
        var str: String = "Node("
        str += "%s".format(entry.toString())
        str += ")"

        return str
    }
}

class RedBlackTree<T : Comparable<T>> {
    private var root: Node<T>? = null

    // Left rotation
    private fun rotateLeft(x: Node<T>) {
        println("rotateLeft(%s)".format(x))
        val y = x.right ?: return
        x.right = y.left
        if (y.left != null) {
            y.left?.parent = x
        }
        y.parent = x.parent
        when {
            x.parent == null -> root = y
            x == x.parent?.left
            -> x.parent?.left = y

            else -> x.parent?.right = y
        }
        y.left = x
        x.parent = y
    } // end rotateLeft()


    // Right rotation
    private fun rotateRight(x: Node<T>) {
        println("rotateRight(%s)".format(x))
        val y = x.left ?: return
        x.left = y.right
        if (y.right != null) {
            y.right?.parent = x
        }
        y.parent = x.parent
        when {
            x.parent == null -> root = y
            x == x.parent?.right -> x.parent?.right = y
            else -> x.parent?.left = y
        }
        y.right = x
        x.parent = y
    } // end rotateRight()

    // Fix violations after insertion
    private fun fixInsert(z: Node<T>) {
        var node = z
        while (node.parent?.color == Color.RED) {
            val parent = node.parent ?: break
            val grandParent = parent.parent ?: break
            if (parent == grandParent.left) {
                val uncle = grandParent.right
                if (uncle?.color == Color.RED) {
                    // Case 1: Uncle is RED
                    parent.color = Color.BLACK
                    uncle.color = Color.BLACK
                    grandParent.color = Color.RED
                    node = grandParent
                } else {
                    // Case 2: Uncle is BLACK
                    if (node == parent.right) {
                        node = parent
                        rotateLeft(node)
                    }
                    // Case 3: Recolor and rotate
                    parent.color = Color.BLACK
                    grandParent.color = Color.RED
                    rotateRight(grandParent)
                }
            } else { // (parent == grandParent.right)
                val uncle = grandParent.left
                if (uncle?.color == Color.RED) {
                    // Case 1: Uncle is RED
                    parent.color = Color.BLACK
                    uncle.color = Color.BLACK
                    grandParent.color = Color.RED
                    node = grandParent
                } else {
                    // Case 2: Uncle is BLACK
                    if (node == parent.left) {
                        node = parent
                        rotateRight(node)
                    }
                    // Case 3: Recolor and rotate
                    parent.color = Color.BLACK
                    grandParent.color = Color.RED
                    rotateLeft(grandParent)
                }
            }
        }
        root?.color = Color.BLACK
    } // end fixInsert(z)

    fun insert(entry: T) {
        var newNode = Node(entry)
        var y: Node<T>? = null
        var x = root
        while (x != null) {
            y = x
            x = if (newNode.entry < x.entry) x.left
            else x.right
        }
        newNode.parent = y
        when {
            y == null -> root = newNode
            newNode.entry < y.entry
            -> y.left = newNode

            else
            -> y.right = newNode
        }
        newNode.color = Color.RED
        println("before fixInsert()")
        this.printRBTree_withDepth()
        fixInsert(newNode)
        println("after fixInsert()")
        this.printRBTree_withDepth()
    } // end insert()

    // Search for a entry
    fun search(entry: T): Node<T>? {
        var current = root
        while (current != null && entry != current.entry) {
            current = if (entry < current.entry) current.left else current.right
        }
        return current
    } // end search()

    // In-order traversal (for display purposes)
    fun inOrderTraversal(node: Node<T>?) {
        if (node != null) {
            inOrderTraversal(node.left)
            println("${node.entry} (${node.color})")
            inOrderTraversal(node.right)
        }
    }

    // Print the tree
    fun printTree() {
        inOrderTraversal(root)
    }

    fun _printRBTree_withDepth(node: Node<T>?, depth: Int) {
        if (node != null) {
            if (node.right != null) _printRBTree_withDepth(node.right, depth + 1)
            for (i in 0 until depth)
                print("     ")
            println("${node.entry} (${node.color}, level = $depth)")
            if (node.left != null) _printRBTree_withDepth(node.left, depth + 1)
        }
    }

    fun printRBTree_withDepth() {
        var node: Node<T>? = root
        var depth = 0
        if (node != null) {
            _printRBTree_withDepth(node, depth)
        }
    }
}