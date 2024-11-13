package Class_Time

import kotlin.text.Typography.tm

class Time(var hh : Int, var mm : Int, var ss : Int) : Comparable<Time> {
    override fun compareTo(other: Time): Int {
        if (this.hh != other.hh) return this.hh - other.hh
        if (this.mm != other.mm) return this.mm - other.mm
        return this.ss - other.ss
    }
    override fun toString(): String {
        return "%02d:%02d:%02d".format(hh, mm, ss) // 시, 분, 초
    }

    fun getElapsedSec() : Int {
        var elps_sec = 0
        elps_sec = (hh * 3600) + (mm * 60) + ss

        return elps_sec
    }
}

fun printTimes(times: Array<Time>) { // 시간 출력 함수
    print("times = [")
    for ((index, time) in times.withIndex()) {
        if (index > 0) print(", ")  // 첫 index가 아니면 콤마와 공백을 추가
        print("(%02d:%02d:%02d)".format(time.hh, time.mm, time.ss))
    }
    println("]")
}
