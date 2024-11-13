/* Homework 7.2.kt
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 7. 8.
 * class Mtrx, Array, File I/O
 */

import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.*

class Mtrx {
    private var m_name: String
    private var n_row: Int
    private var n_col: Int
    private lateinit var mtrx_data: Array<DoubleArray>

    constructor(nm: String) {
        this.m_name = nm
        this.n_row = 0
        this.n_col = 0
    }

    constructor(nm: String, n_row: Int, n_col: Int) {
        this.m_name = nm
        this.n_row = n_row
        this.n_col = n_col
        this.mtrx_data = Array(n_row) { DoubleArray(n_col) }
    }

    constructor(nm: String, n_row: Int, n_col: Int, m_data: Array<DoubleArray>) {
        this.m_name = nm
        this.n_row = n_row
        this.n_col = n_col
        this.mtrx_data = Array(n_row) { DoubleArray(n_col) }
        for (r in 0 until n_row) {
            for (c in 0 until n_col) {
                mtrx_data[r][c] = m_data[r][c]
            }
        }
    }

    fun setMtrxName(nm: String) {
        m_name = nm
    }

    fun printMtrx() {
        System.out.printf("%s (%d x %d) = \n", m_name, n_row, n_col)
        for (r in 0 until n_row) {
            for (c in 0 until n_col) {
                System.out.printf("%7.2f", mtrx_data[r][c])
            }
            System.out.printf("\n")
        }
    }

    @Throws(IOException::class)
    fun fprintMtrx(fout: FileWriter) {
        var str_data: String?
        fout.write(String.format("%s (%d x %d) = \n", m_name, n_row, n_col))
        for (r in 0 until n_row) {
            for (c in 0 until n_col) {
                str_data = java.lang.String.format("%7.2f", this.mtrx_data[r][c])
                fout.write(str_data)
            }
            fout.write("\n")
        }
        fout.flush()
    }

    fun addMtrx(other: Mtrx): Mtrx {
        val m_result = Mtrx("R", n_row, n_col)
        for (r in 0 until n_row) {
            for (c in 0 until n_col) {
                m_result.mtrx_data[r][c] =
                    this.mtrx_data[r][c] + other.mtrx_data[r][c]
            }
        }
        return m_result
    }

    fun subMtrx(other: Mtrx): Mtrx {
        val m_result = Mtrx("R", n_row, n_col)
        for (r in 0 until n_row) {
            for (c in 0 until n_col) {
                m_result.mtrx_data[r][c] =
                    mtrx_data[r][c] - other.mtrx_data[r][c]
            }
        }
        return m_result
    }

    fun mulMtrx(other: Mtrx): Mtrx {
        val m_result = Mtrx("R", n_row, other.n_col)
        var temp_sum = 0.0
        for (r in 0 until n_row) {
            for (c in 0 until other.n_col) {
                temp_sum = 0.0
                for (k in 0 until n_col) {
                    temp_sum += mtrx_data[r][k] * other.mtrx_data[k][c]
                }
                m_result.mtrx_data[r][c] = temp_sum
            }
        }
        return m_result
    }

    companion object { // 동반 객체
        fun fget_Mtrx(fin: Scanner): Mtrx {
            val m_name = fin.next()
            val n_row = fin.nextInt()
            val n_col = fin.nextInt()
            val mtrx_data = Array(n_row) { DoubleArray(n_col) }

            for (r in 0 until n_row) {
                for (c in 0 until n_col) {
                    mtrx_data[r][c] = fin.nextDouble()
                }
            }
            return Mtrx(m_name, n_row, n_col).apply {
                this.m_name = m_name
                this.n_row = n_row
                this.n_col = n_col
                this.mtrx_data = mtrx_data
            }
        }
    }
}

fun main(args: Array<String>) {
    val fin_name = "Mtrx_data.txt"
    val fout_name = "Mtrx_operation_result.txt"
    val fin = Scanner(File(fin_name))
    val fout = FileWriter(File(fout_name))
    val mtrxs = arrayOfNulls<Mtrx>(6)
    mtrxs[0] = Mtrx.fget_Mtrx(fin)
    mtrxs[1] = Mtrx.fget_Mtrx(fin)
    mtrxs[2] = Mtrx.fget_Mtrx(fin)
    mtrxs[0]!!.printMtrx(); mtrxs[0]?.fprintMtrx(fout)
    mtrxs[1]!!.printMtrx(); mtrxs[1]?.fprintMtrx(fout)
    mtrxs[2]!!.printMtrx(); mtrxs[2]?.fprintMtrx(fout)
    mtrxs[3] = mtrxs[0]!!.addMtrx(mtrxs[1]!!)
    mtrxs[3]!!.setMtrxName("mAdd_AB")
    mtrxs[3]!!.printMtrx(); mtrxs[3]?.fprintMtrx(fout)
    mtrxs[4] = mtrxs[0]!!.subMtrx(mtrxs[1]!!)
    mtrxs[4]!!.setMtrxName("mSub_AB")
    mtrxs[4]!!.printMtrx(); mtrxs[4]?.fprintMtrx(fout)
    mtrxs[5] = mtrxs[0]!!.mulMtrx(mtrxs[2]!!)
    mtrxs[5]!!.setMtrxName("mMul_AC")
    mtrxs[5]!!.printMtrx(); mtrxs[5]?.fprintMtrx(fout)
    fout.close()
}
