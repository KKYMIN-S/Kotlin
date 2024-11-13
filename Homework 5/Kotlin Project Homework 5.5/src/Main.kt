/* Kotlin Project Homework 5.5
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 30.
 * Int 숫자의 접두어(prefix) 포함 지정 길이의 16진수, 8진수, 2진수 출력
 */

fun main() {
    var str_dec: String // 10진수 값을 저장할 문자열 변수
    var str_oct: String // 8진수 값을 저장할 문자열 변수
    var str_hex: String // 16진수 값을 저장할 문자열 변수
    var str_bin: String // 2진수 값을 저장할 문자열 변수

    for (i in 0..16) {
        str_dec = i.toString() // i를 10진수 문자열로 변환하고 저장
        str_oct = "0o" + Integer.toOctalString(i).padStart(3, '0')  // 8진수의 접두어 0 추가, 4자리 맞추고 앞에 0 채움
        str_hex = "0x" + Integer.toHexString(i).padStart(2, '0')   // 16진수의 접두어 0x 추가, 2자리 맞추고 앞에 0 채움
        str_bin = "0b" + Integer.toBinaryString(i).padStart(8, '0')  // 2진수의 접두어 0b 추가, 8자리 맞추고 앞에 0 채움
        println("The number %4s in different bases with prefix : Hexadecimal ($str_hex), Octal ($str_oct), Binary($str_bin)".format(str_dec)) // 출력문
    }
}