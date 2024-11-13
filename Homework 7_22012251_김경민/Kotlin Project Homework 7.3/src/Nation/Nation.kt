package Nation

class Nation(val name: String, val capital: String, val population: Int) {
    // 국가를 비교하는 함수
    fun compareNation(attrName: String, other: Nation): Int {
        var result = 0
        when (attrName) {
            "name" -> result = this.name.compareTo(other.name) // 이름
            "capital" -> result = this.capital.compareTo(other.capital) // 수도
            "population" -> result = this.population - other.population // 인구수
        }
        return result
    }

    // Nation 객체를 문자열로 표현
    override fun toString(): String {
        return "($name, $capital, $population)"
    }
}