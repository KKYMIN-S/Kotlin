package MyKotlinClasses

class Person(val name: String, val telNo: TelNum) {
    fun get_name(): String { // Person 객체의 name을 반환하는 함수
        return this.name // 객체의 name 데이터를 반환
    }
    override fun toString(): String { // Person 정보 출력 형식
        return "Person(Name= %5s, telNo= %16s)".format(name, telNo)
    }
}