package MyKotlinClasses

class TelNum(var nation_code: Int, var reg_no: Int, var sw_no: Int, var line_no: Int) {
    override fun toString(): String { // TelNum 정보 출력 형식
        return String.format("%03d-%2d-%04d-%04d", nation_code, reg_no, sw_no, line_no)
    }
}