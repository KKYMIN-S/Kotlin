package Trie

import java.util.*

class Trie {
    private var _root: TrieNode? = null
    private var _name: String? = null

    constructor(nm: String) {
        _root = TrieNode('/', null)
        _name = nm
    }
    fun getName(): String? {
        return _name
    }
    fun insert(word: String) {
        var curTN = _root
        var children: TreeMap<Char, TrieNode>
                = curTN!!.getChildren()
        for (i in 0 until word.length) {
            val c = word[i]

            if (children.containsKey(c)) {
                curTN = children[c]
            } else {
                curTN = TrieNode(c, curTN)
                children[c] = curTN
            }
            children = curTN!!.getChildren()
            if (i == word.length - 1) {
                curTN!!.setLeaf(true)
                curTN!!.setKeyWord(word)
            }
        }
    }
    fun search(word: String): Boolean {
        val curTN = _searchKeyword(word)
        return if (curTN != null && curTN.isLeaf()) {
            true
        } else {
            false
        }
    }
    fun _searchKeyword(word: String): TrieNode? {
        var curTN = _root
        var children: TreeMap<Char, TrieNode>
                = curTN!!.getChildren()
        for (i in 0 until word.length) {
            val c = word[i]
            if (children.containsKey(c)) {
                curTN = children[c]
                children = curTN!!.getChildren()
            } else {
                curTN = null
                break
            }
        }
        return curTN
    }
    fun _getKeywords(curTN: TrieNode?, keywords: ArrayList<String?>) {
        var tn: TrieNode
        if (curTN!!.isLeaf()) {
            keywords.add(curTN!!.getKeyWord())
        }
        val children = curTN!!.getChildren()
        val trieNodes: Set<Map.Entry<Char, TrieNode>> = children.entries
        for ((_, value) in trieNodes) {
            tn = value
            if (tn.getChildren() != null) { _getKeywords(tn, keywords)
            }
        }
    }
    fun getKeywords(): ArrayList<String?> {
        val curTN = _root
        val keywords = ArrayList<String?>()
        _getKeywords(curTN, keywords)
        return keywords
    }
    fun getPredictiveWords(keyword: String): ArrayList<String?> {
        val curTN = _searchKeyword(keyword)
        val keywords = ArrayList<String?>()
        _getKeywords(curTN, keywords)
        return keywords
    }
}

