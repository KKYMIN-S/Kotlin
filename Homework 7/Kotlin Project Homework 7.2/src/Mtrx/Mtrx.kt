package Mtrx

import java.io.FileWriter
import java.io.IOException
import java.util.*

class Mtrx {
    var m_name: String? = null  // 행렬 이름
    var n_row = 0  // 행의 개수
    var n_col = 0  // 열의 개수
    var mtrx_data = Array(n_row) { DoubleArray(n_col) }  // 행렬 데이터

    // 기본 생성자
    constructor() {
        this.m_name = ""
        this.n_row = 0
        this.n_col = 0
    }

    // 이름을 받는 생성자
    constructor(nm: String) {
        this.m_name = nm
        this.n_row = 0
        this.n_col = 0
    }

    // 이름, 행의 개수, 열의 개수를 받는 생성자
    constructor(nm: String, n_row: Int, n_col: Int) {
        this.m_name = nm
        this.n_row = n_row
        this.n_col = n_col
        this.mtrx_data = Array(n_row) { DoubleArray(n_col) }
    }

    // 이름, 행의 개수, 열의 개수, 초기 행렬 데이터를 받는 생성자
    constructor(nm: String, n_row: Int, n_col: Int, m_data: Array<DoubleArray>) {
        this.m_name = nm // 이름
        this.n_row = n_row // 행
        this.n_col = n_col // 열
        this.mtrx_data = Array(n_row) { DoubleArray(n_col) }
        for (r in 0 until n_row) {
            for (c in 0 until n_col) {
                mtrx_data[r][c] = m_data[r][c]
            }
        }
    }

    // 동반 객체를 사용한 행렬 입력 함수
    companion object { // 동반 객체
        fun fget_Mtrx(fin: Scanner): Mtrx {
            val mtrx = Mtrx()
            mtrx.m_name = fin.next()
            mtrx.n_row = fin.nextInt()
            mtrx.n_col = fin.nextInt()
            mtrx.mtrx_data = Array(mtrx.n_row) { DoubleArray(mtrx.n_col) }
            for (r in 0 until mtrx.n_row) {
                for (c in 0 until mtrx.n_col) {
                    mtrx.mtrx_data[r][c] = fin.nextDouble()
                }
            }
            return mtrx
        }
    }

    // 행렬 이름 설정 함수
    fun setMtrxName(nm: String) {
        m_name = nm // 행렬 이름
    }

    // 행렬 출력 함수
    fun printMtrx(){
        System.out.printf("%s (%d x %d) = \n", m_name, n_row, n_col)
        for (r in 0 until n_row) {
            for (c in 0 until n_col) {
                System.out.printf("%7.2f", mtrx_data[r][c])
            }
            System.out.printf("\n")
        }
    }

    // 행렬 파일 출력 함수
    @Throws(IOException::class) // 예외 처리
    fun fprintMtrx(fout: FileWriter){
        var str_data:String?
        fout.write(m_name)
        fout.write(" ")
        fout.write(String.format("%3d",n_row))
        fout.write(" ")
        fout.write(String.format("%3d",n_col))
        fout.write("\n")

        for(r in 0 until n_row) {
            for (c in 0 until n_col) {
                str_data = java.lang.String.format("%7.2f", this.mtrx_data[r][c])
                fout.write(str_data)
            }
            fout.write("\n")
        }
        fout.flush()
    }

    operator fun plus(other: Mtrx): Mtrx { // 연산자 오버로딩
        val m_result = Mtrx("R", n_row, n_col)
        for (r in 0 until n_row) {
            for (c in 0 until n_col) {
                m_result.mtrx_data[r][c] = this.mtrx_data[r][c] + other.mtrx_data[r][c]
            }
        }
        return m_result
    }

    operator fun minus(other: Mtrx): Mtrx { // 연산자 오버로딩
        val m_result = Mtrx("R", n_row, n_col)
        for (r in 0 until n_row) {
            for (c in 0 until n_col) {
                m_result.mtrx_data[r][c] = this.mtrx_data[r][c] - other.mtrx_data[r][c]
            }
        }
        return m_result
    }

    operator fun times(other: Mtrx): Mtrx { // 연산자 오버로딩
        val m_result = Mtrx("R", n_row, other.n_col)
        var temp_sum: Double

        for (r in 0 until n_row) { // 3중 for문
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
}