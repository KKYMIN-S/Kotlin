package Trie

import java.util.*

class TrieNode {
    private var ch = 0.toChar()
    private var keyword: String? = null
    private var parent: TrieNode? = null
    private var children = TreeMap<Char, TrieNode>()
    private var isLeaf = false
    constructor() {}
    constructor(ch: Char, pr: TrieNode?) {
        this.ch = ch
        parent = pr
    }
    fun getCh(): Char {
        return ch
    }
    fun getChildren(): TreeMap<Char, TrieNode> {
        return children
    }
    fun setChildren(children: TreeMap<Char, TrieNode>) {
        this.children = children
    }
    fun isLeaf(): Boolean {
        return isLeaf
    }
    fun setLeaf(isLeaf: Boolean) {
        this.isLeaf = isLeaf
    }
    fun setKeyWord(keyword: String?) {
        this.keyword = keyword
    }
    fun getKeyWord(): String? {
        return keyword
    }
}
