/* Kotlin Project Homework 9.2
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 25.
 * Kotlin generic class SortedMultiMap <K, V> 기반 Thesaurus Dictionary 구성
 */

import java.io.File
import java.util.*
import SortedMultiMap.*

fun main(args: Array<String>) {
    val thesaurusMultiMap: SortedMultiMap<String, Thesaurus> = SortedMultiMap<String, Thesaurus>()
    val fname = "thesaurus_dict.txt"
    val fin = Scanner(File(fname))
    var keyword: String
    var word: String
    var type: String
    while (fin.hasNext()) {
        val str_line = fin.nextLine()
        println("ThesaurusDict:: processing %s".format(str_line))
        val strTokenizer = StringTokenizer(str_line, " ")

        keyword = strTokenizer.nextToken()
        type = strTokenizer.nextToken()
        val thesaurus = Thesaurus(keyword, type)
        while (strTokenizer.hasMoreTokens()) {
            word = strTokenizer.nextToken()
            thesaurus.addThesaurus(word)
        }
        thesaurusMultiMap.put(keyword, thesaurus)
    }
    fin.close()

    val keys = thesaurusMultiMap.keySet()
    println("keys = %s".format(keys))
    val it = keys.iterator()
    var arrayList_thesaurus: Collection<Thesaurus?>?
    while (it.hasNext()) {
        val keyWord = it.next()
        arrayList_thesaurus = thesaurusMultiMap[keyWord]
        println("key (%s) :".format(keyWord))
        for (ths in arrayList_thesaurus!!)
            println(" %s".format(ths))
        //println();
    }
}
