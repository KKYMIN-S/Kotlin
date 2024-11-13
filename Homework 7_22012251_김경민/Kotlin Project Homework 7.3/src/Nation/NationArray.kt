package Nation

import java.util.*

class NationArray {
    private var num_nations = 0
    lateinit var nations: Array<Nation?>

    fun fgetNations(fin: Scanner) {
        num_nations = fin.nextInt()
        nations = arrayOfNulls(num_nations)
        for (i in 0 until num_nations) {
            val name: String = fin.next()
            val capital: String = fin.next()
            val population: Int = fin.nextInt()
            nations[i] = Nation(name, capital, population)
        }
    }

    // 지정된 속성에 따라 정렬하기
    fun sort(key_attr: String?, sorting_order: String) {
        nations = if (sorting_order == "increasing") {
            // 오름차순 정렬, Comparator: 두 개의 객체를 비교하는 함수형 인터페이스
            nations.sortedWith(Comparator { a, b -> a?.compareNation(key_attr!!, b!!) ?: 0 }).toTypedArray()
        } else {
            // 내림차순 정렬
            nations.sortedWith(Comparator { a, b -> b?.compareNation(key_attr!!, a!!) ?: 0 }).toTypedArray()
        }
    }

    override fun toString(): String {
        val result = StringBuilder("Nations (total ${nations.size} nations) =\n")
        nations.forEachIndexed { index, nation ->
            result.append(nation.toString())

            if ((index + 1) % 5 == 0) { // 5개 마다
                result.append("\n") // 줄바꿈
            } else if (index != nations.size - 1) { // 마지막 국가가 아니면
                result.append(", ") // 쉼표 추가
            }
        }
        return result.toString()
    }
}
