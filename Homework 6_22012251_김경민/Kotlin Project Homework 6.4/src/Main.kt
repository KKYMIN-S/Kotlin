/* Kotlin Project Homework 6.4
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 3.
 * HashMap<K, V> 기반의 Inter-city distance table
 */

data class CityPair(val from: String, val to: String)
data class ICD(val from: String, val to: String, val dist: Int)

// 두 도시 간의 거리를 HashMap에 추가하는 함수
fun insertICDs(ICDTbl: HashMap<CityPair, Int>, ICDs: Array<ICD>) {
    for (icd in ICDs) {
        ICDTbl[CityPair(icd.from, icd.to)] = icd.dist // from -> to 방향의 거리 추가
        ICDTbl[CityPair(icd.to, icd.from)] = icd.dist // to -> from 방향의 거리 추가
    }
}

// HashMap에 저장된 도시간 거리를 출력하는 함수
fun printIcdTbl(ICDTbl: HashMap<CityPair, Int>) {
    println("Inter-City Distance Table = ")
    for ((cityPair, dist) in ICDTbl) {
        println(" ${cityPair.from} -> ${cityPair.to} : ${dist}")
    }
}

fun main() {
    // HashMap 생성: Key로 Key 클래스 객체를 사용, Value로 String을 사용
    val ICDs: Array<ICD> = arrayOf(
        ICD("Seoul", "Daegu", 300),
        ICD("Seoul", "Busan", 475),
        ICD("Daegu", "Busan", 175),
        ICD("Seoul", "Daejeon", 150),
        ICD("Daegu", "Daejeon", 150)
    )
    val ICDTbl = HashMap<CityPair, Int>()  // HashMap 생성: Key는 CityPair, Value는 거리(Int)

    insertICDs(ICDTbl, ICDs) // 도시 간 거리 정보를 HashMap에 추가
    printIcdTbl(ICDTbl)  // HashMap에 저장된 도시 간 거리 정보 출력
}