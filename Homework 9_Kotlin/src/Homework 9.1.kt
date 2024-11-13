/* Homework 9.1.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 8.
 * Trie 기반 예측문자열 검색기 구현
 */

import java.io.File
import java.util.*
import Trie.*

fun main(args: Array<String>) {
    val trie_str = Trie("Trie_String")
    val fname = "Keywords.txt"
    val fin = Scanner(File(fname))
    var w: String?

    while (fin.hasNext()) {
        w = fin.next()
        trie_str.insert(w)
    }
    val test_words = arrayOf("abcd", "ab", "abc", "k", "x", "z")

    for (kw in test_words) {
        if (trie_str.search(kw)) {
            print("%5s : found in trie\n".format(kw))
        }
        else {
            print("%5s : not found in trie\n".format(kw))
        }
    }
    var keywords: ArrayList<String?>
    keywords = trie_str.getKeywords()
    print("Total keywords of %s : ".format(trie_str.getName()))
    for (word in keywords) {
        print("%s ".format(word))
    }
    println()
    val test_keyword = "ab"
    keywords = trie_str.getPredictiveWords(test_keyword)
    print("Predictive words of %s : ".format(test_keyword))
    for (word in keywords) {
        print("%s ".format(word))
    }
    println()
}