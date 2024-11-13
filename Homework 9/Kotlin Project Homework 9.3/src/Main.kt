/* Kotlin Project Homework 9.3
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 25.
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

    while (fin.hasNext()) { // 파일에 있는 단어들을 하나씩 Trie에 삽입.
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
    // Trie에 저장된 모든 키워드를 가져와 출력.
    var keywords: ArrayList<String?>
    keywords = trie_str.getKeywords()
    print("Total keywords of %s : ".format(trie_str.getName()))
    for (word in keywords) {
        print("%s ".format(word))
    }
    println()

    // 특정 키워드에 대한 예측 단어를 가져와 출력.
    val test_keyword = "ab"
    keywords = trie_str.getPredictiveWords(test_keyword)
    print("Predictive words of %s : ".format(test_keyword))
    for (word in keywords) {
        print("%s ".format(word))
    }
    println()
}