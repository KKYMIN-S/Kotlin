/* Kotlin Project Homework 6.5
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 3.
 * Thesaurus Dictionary 구현
 */

import MyKotlinClasses.*
import java.io.File
import java.util.*

fun main(args: Array<String>) {
    // HashMap 생성: Word_Type을 키로, Thesaurus를 값으로 저장
    val thesaurusDict = HashMap<Word_Type, Thesaurus>()
    val fname = "sample_thesaurus_dict.txt"
    val fin = Scanner(File(fname))
    var keyword: String // 키워드
    var word: String // 유의어
    var type: String // 품사

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
    val keys: Set<Word_Type> = thesaurusDict.keys // HashMap에 저장된 모든 키 가져오기
    System.out.printf("keys = %s\n", keys)
    val it = keys.iterator() // 각 키에 대한 반복자 생성

    while (it.hasNext()) {
        val keyWordType = it.next() // 현재 반복자에서 키 가져오기
        val value = thesaurusDict[keyWordType]  // 해당 키에 대한 값(Thesaurus 객체) 가져오기
        println("key ($keyWordType) : $value)")
    }
}