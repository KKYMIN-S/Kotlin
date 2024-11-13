/* Kotlin Project Homework 2.5
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 9. 11.
 * 행렬 (Matrix) 연산
 */

fun printMtrx(mtrx_name: String, mtrx_data: Array<DoubleArray>) {
    print("%s = \n".format(mtrx_name))
    for (row in mtrx_data) {
        for (element in row) {
            print("%7.2f".format(element))
        }
        println()
    }
}
fun addMtrx(mA_data: Array<DoubleArray>, mB_data: Array<DoubleArray>): Array<DoubleArray> {
    var mR = Array<DoubleArray>(mA_data.size){DoubleArray(mB_data[0].size)}
    for (row in 0 until mA_data.size) {
        for (col in 0 until mB_data[0].size) {
            mR[row][col] = mA_data[row][col] + mB_data[row][col]
        }
    }
    return mR
}
fun subMtrx(mA_data: Array<DoubleArray>, mB_data: Array<DoubleArray>): Array<DoubleArray> {
    var mR = Array<DoubleArray>(mA_data.size){DoubleArray(mB_data[0].size)}
    for (row in 0 until mA_data.size) {
        for (col in 0 until mB_data[0].size) {
            mR[row][col] = mA_data[row][col] - mB_data[row][col]
        }
    }
    return mR
}
fun mulMtrx(mA_data: Array<DoubleArray>, mB_data: Array<DoubleArray>): Array<DoubleArray> {
    var mR = Array<DoubleArray>(mA_data.size){DoubleArray(mB_data[0].size)}
    var temp_sum: Double
    for (row in 0 until mA_data.size) {
        for (col in 0 until mB_data[0].size) {
            temp_sum = 0.0
            for (k in 0 until mB_data[0].size) {
                temp_sum += mA_data[row][k] * mB_data[k][col]
            }
            mR[row][col] = temp_sum
        }
    }
    return mR
}

fun main() {
    val mA = arrayOf(
        doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0),
        doubleArrayOf(6.0, 7.0, 8.0, 9.0, 10.0),
        doubleArrayOf(11.0, 12.0, 13.0, 14.0, 15.0)
    )
    val mB = arrayOf(
        doubleArrayOf(1.0, 0.0, 0.0, 0.0, 0.0),
        doubleArrayOf(0.0, 1.0, 0.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0, 0.0, 0.0)
    )
    val mAddAB: Array<DoubleArray>
    val mSubAB: Array<DoubleArray>
    print("mA (nRow = %d, nCol = %d)\n".format(mA.size, mA[0].size))
    printMtrx("mA", mA)
    printMtrx("mB", mB)
    mAddAB = addMtrx(mA, mB)
    printMtrx("mAddAB", mAddAB)
    mSubAB = subMtrx(mA, mB)
    printMtrx("mSubAB", mSubAB)
    val mC = arrayOf(
        doubleArrayOf(1.0, 0.0, 0.0),
        doubleArrayOf(0.0, 1.0, 0.0),
        doubleArrayOf(0.0, 0.0, 1.0),
        doubleArrayOf(0.0, 0.0, 0.0),
        doubleArrayOf(0.0, 0.0, 0.0)
    )
    val mMulAC: Array<DoubleArray>
    printMtrx("mA", mA)
    printMtrx("mC", mC)
    mMulAC = mulMtrx(mA, mC)
    printMtrx("mMulAC", mMulAC)
}