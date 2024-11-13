/* Kotlin Project Homework 3.5
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 17.
 * 정수 자료형 데이터의 비트단위 연산 및 결과 출력
 */

import java.util.*

enum class BinOp_Type {
    ADD, SUB, MUL, DIV, MOD, BIT_AND, BIT_OR, BIT_XOR, BIT_NOT, SHIFT_LEFT, SHIFT_RIGHT, UNSIGNED_SHIFT_RIGHT
}

var bop_sign = arrayOf("+", "-", "*", "/", "%", "&", "|", "^", "~", "<<", ">>", ">>>")

fun printInt_Bits(data: Int, num_bits: Int) {
    // data를 8bits 단위 씩 2진수 형식의 문자열로 변환
    val binaryString = Integer.toBinaryString(data).padStart(num_bits, '0').chunked(8).joinToString(" ")
    println(binaryString)
}

fun printBitOperation(x: Int, y: Int, z: Int, bop: BinOp_Type, num_bits: Int) {
    val operator = when (bop) {
        BinOp_Type.BIT_AND -> "&"
        BinOp_Type.BIT_OR -> "|"
        BinOp_Type.BIT_XOR -> "^"
        BinOp_Type.SHIFT_LEFT -> "<<"
        BinOp_Type.SHIFT_RIGHT -> ">>"
        BinOp_Type.UNSIGNED_SHIFT_RIGHT -> ">>>"
        else -> bop_sign[bop.ordinal]
    }
    val xBits = Integer.toBinaryString(x).padStart(num_bits, '0').chunked(8).joinToString(" ")
    val yBits = Integer.toBinaryString(y).padStart(num_bits, '0').chunked(8).joinToString(" ")
    val zBits = Integer.toBinaryString(z).padStart(num_bits, '0').chunked(8).joinToString(" ")


    println("\t%s\n%3s %s\n \t%s\n \t%s".format(xBits, operator, yBits, "-".repeat(36), zBits))
}

fun main() {
    val cin = Scanner(System.`in`)
    val data_1: Int; val data_2: Int
    val data_3: Int; val data_4: Int
    val data_5: Int; val data_6: Int
    val data_7: Int; val data_8: Int
    val k = 3
    var cin_data: String
    val num_bits = 32

    data_1 = 0x07
    print("First input data (Hexa-decimal) %08X = (Decimal) %3d = (Octal) %#4o = (Binary)".format(data_1, data_1, data_1 ))
    printInt_Bits(data_1, num_bits); println()
    data_2 = 0xA3
    print( "Second input data (Hexa-decimal) %08X = (Decimal) %3d = (Octal) %#4o = (Binary)".format(data_2, data_2, data_2 ))
    printInt_Bits(data_2, num_bits); println()

    /* bit-wise AND */
    data_3 = data_1 and data_2
    println("\nBit-wise AND of two input data (%08X & %08X) => %08X".format(data_1, data_2, data_3))
    printBitOperation(data_1, data_2, data_3, BinOp_Type.BIT_AND, num_bits)

    /* bit-wise OR */
    data_4 = data_1 or data_2
    println("\nBit-wise OR of two input data (%08X | %08X) => %08X".format(data_1, data_2, data_4))
    printBitOperation(data_1, data_2, data_4, BinOp_Type.BIT_OR, num_bits)

    /* bit-wise XOR */
    data_5 = data_1 xor data_2
    println("\nBit-wise XOR of two input data (%08X | %08X) => %08X".format(data_1, data_2, data_5))
    printBitOperation(data_1, data_2, data_5, BinOp_Type.BIT_XOR, num_bits)

    /* Shift Left */
    data_6 = data_1 shl k
    println("\nShift Left of (%08X << %08X) => %08X".format(data_1, k, data_6))
    printBitOperation(data_1, k, data_6, BinOp_Type.SHIFT_LEFT, num_bits)

    /* Arithmetic Shift Right */
    val data_negative = -0x7
    print("\ndata_negative = "); printInt_Bits(data_negative, num_bits); println()
    data_7 = data_negative shr k
    println("\nShift Right of (%08X >> %08X) => %08X".format( data_negative, k, data_7))
    printBitOperation(data_negative, k, data_7, BinOp_Type.SHIFT_RIGHT, num_bits)
    /* Logical Shift Right */
    data_8 = data_negative ushr k
    println("\nUnsigned Shift Right of (%08X >> %08X) => %08X".format( data_negative, k, data_8))
    printBitOperation(data_negative, k, data_8, BinOp_Type.UNSIGNED_SHIFT_RIGHT, num_bits)
}
