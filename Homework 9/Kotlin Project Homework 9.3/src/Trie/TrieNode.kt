package Trie

import java.util.*

class TrieNode { // TrieNode
    private var ch = 0.toChar() // 이 노드가 나타내는 문자
    private var keyword: String? = null
    private var parent: TrieNode? = null
    private var children = TreeMap<Char, TrieNode>() // 자식 노드들을 저장하는 TreeMap
    private var isLeaf = false // 단어의 끝을 나타내는지를 저장하는 변수
    constructor() {}
    constructor(ch: Char, pr: TrieNode?) {
        this.ch = ch
        parent = pr
    }
    fun getCh(): Char { // 현재 노드가 나타내는 문자를 반환
        return ch
    }
    fun getChildren(): TreeMap<Char, TrieNode> { // 자식 노드들을 반환 - TreeMap 형태
        return children
    }
    fun setChildren(children: TreeMap<Char, TrieNode>) { // 자식 노드를 설정
        this.children = children
    }
    fun isLeaf(): Boolean {
        return isLeaf
    }
    fun setLeaf(isLeaf: Boolean) {
        this.isLeaf = isLeaf
    }
    fun setKeyWord(keyword: String?) { // 이 노드에서 완성되는 키워드를 설정하는 함수
        this.keyword = keyword
    }
    fun getKeyWord(): String? {
        return keyword
    }
}