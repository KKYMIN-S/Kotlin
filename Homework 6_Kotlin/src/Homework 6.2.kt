/* Homework 6.2.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 5.
 * Thesaurus Dictionary 구현
 */

import java.io.File
import java.util.*

class Word_Type(private val word: String, private val type: String) {
    override fun toString(): String {
        return word + "_" + type
    }
}
class Thesaurus {
    private var keyword: String? = null
    private var type: String? = null // noun, verb, adj, adv, etc.
    private val list_thesaurus = ArrayList<String>() // max 10 words

    constructor()
    constructor(word: String?, type: String?, array_thesaurus: Array<String>) {
        keyword = word
        this.type = type
        for (str in array_thesaurus) {
            list_thesaurus.add(str)
        }
    }
    fun setKeyWord(word: String?) {
        keyword = word
    }

    fun setType(type: String?) {
        this.type = type
    }
    fun addThesaurus(word: String) {
        list_thesaurus.add(word)
    }
    override fun toString(): String {
        var str = String.format("%5s [%3s] : {", keyword, type)
        for (w in list_thesaurus) {
            str += "$w, "
        }
        str += "}"
        return str
    }
}

fun main(args: Array<String>) {
    val thesaurusDict = HashMap<Word_Type, Thesaurus>()
    val fname = "Thesaurus_dict.txt"
    val fin = Scanner(File(fname))
    var keyword: String
    var word: String
    var type: String
    while (fin.hasNext()) {
        val str_line = fin.nextLine()
        System.out.printf("ThesaurusDict:: processing %s\n", str_line)
        val strTokenizer = StringTokenizer(str_line, " ")
        val thesaurus = Thesaurus()
        keyword = strTokenizer.nextToken()
        thesaurus.setKeyWord(keyword)
        type = strTokenizer.nextToken()
        thesaurus.setType(type)
        while (strTokenizer.hasMoreTokens()) {
            word = strTokenizer.nextToken()
            thesaurus.addThesaurus(word)
        }
        thesaurusDict[Word_Type(keyword, type)] = thesaurus
    }
    fin.close()
    val keys: Set<Word_Type> = thesaurusDict.keys
    System.out.printf("keys = %s\n", keys)
    val it = keys.iterator()
    while (it.hasNext()) {
        val keyWordType = it.next()
        val value = thesaurusDict[keyWordType]
        println("key ($keyWordType) : $value)")
    }
}