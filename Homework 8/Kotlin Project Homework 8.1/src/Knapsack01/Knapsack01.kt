
package Knapsack01

import java.lang.Math.pow
import java.util.ArrayList
import java.math.*
import java.util.Scanner

class Knapsack01 {
    var items: MutableList<Item> = mutableListOf()
    var capacity = 0
    var num_items = 0

    // 첫 번째 생성자: 아이템 배열과 용량을 입력 받음
    constructor(items: Array<Item>, capacity: Int) {
        this.capacity = capacity
        for (item in items) {
            this.items.add(item)
        }
    }

    // 두 번째 생성자: 용량과 스캐너 객체를 입력 받음
    constructor(capacity: Int, fin: Scanner) {
        this.capacity = capacity
        val num_items: Int = fin.nextInt()
        this.num_items = num_items
        for (i in 0 until num_items) {
            val name: String = fin.next()
            val value: Int = fin.nextInt()
            val weight: Int = fin.nextInt()
            val item: Item = Item(name, value, weight)
            this.items.add(item)
        }
    }

    // 브루트 포스 방법으로 0/1 배낭 문제를 해결하는 함수
    fun BruteForce_KP01() : KSP_Solution {
        // 부분 집합의 배열 생성 (power set)
        var powerSet : Array<MutableList<Item>> = Array((pow(2.0, this.items.size.toDouble())).toInt()){mutableListOf()}
        var num_items : Int = this.items.size;

        // 모든 부분 집합을 생성
        for (i in 0 .. (pow(2.0, num_items.toDouble())-1).toInt()) {
            var subSet_items : MutableList<Item> = mutableListOf()
            var binStr : String = Integer.toBinaryString(i)
            if (binStr.length > num_items) {
                println("getBinaryRepr::num_digits is not enough !!");
            }
            var binStr_length = binStr.length
            for (j in 0 .. (num_items - binStr_length - 1)) {
                binStr = "0" + binStr // 앞쪽을 '0'으로 채움
            }
            for (j in 0 .. num_items-1) {
                if (binStr[j] == '1') {
                    subSet_items.add(this.items[j]);
                }
            }
            powerSet[i] = subSet_items
        }

        // 생성된 부분 집합 중 최적의 부분 집합 선택
        var bestSubsetValue : Int = 0
        var bestSubsetWeight = 0
        var subsetValue : Int
        var subsetWeight : Int
        var bestSubset : MutableList<Item> = mutableListOf()
        for (subSet in powerSet) {
            subsetValue = 0
            subsetWeight = 0
            for (e in subSet) {
                subsetValue += e.value
                subsetWeight += e.weight
            }
            if ((subsetWeight <= this.capacity) && (subsetValue > bestSubsetValue)) {
                bestSubsetValue = subsetValue
                bestSubset = subSet
                bestSubsetWeight = subsetWeight
            }
        }

        // 최적의 솔루션을 반환
        var solution : KSP_Solution = KSP_Solution();
        solution.selectedItems = bestSubset;
        solution.totalValue = bestSubsetValue
        solution.totalWeight = bestSubsetWeight;
        return solution;
    } // end BruteForce_KP01()

    fun DynKSP_BottomUp(): KSP_Solution {
        // 부분 문제의 해를 저장할 2D 배열 'm'을 초기화.
        val m: Array<Array<KSP_Solution>> = Array(items.size + 1) {
            Array<KSP_Solution>(this.capacity + 1) { KSP_Solution() }
        }

        // 첫 번째 열을 초기화: 각 아이템 k에 대해, 용량이 0인 경우 totalValue와 totalWeight는 0.
        for (k in 0..items.size) {
            for (w in 0..capacity) {
                m[k][w].selectedItems = mutableListOf() // selectedItems 리스트를 초기화.
            }
            m[k][0].totalValue = 0 // 용량이 0일 때, totalValue를 0으로 초기화.
            m[k][0].totalWeight = 0 // 용량이 0일 때, totalWeight를 0으로 초기화.
        }

        // 첫 번째 행을 초기화: item이 없을 경우 totalValue와 totalWeight는 0.
        for (w in 0..capacity) {
            m[0][w].totalValue = 0 // item이 없을 때, totalValue를 0으로 초기화.
            m[0][w].totalWeight = 0 // item이 없을 때, totalWeight를 0으로 초기화.
        }

        // 각 item에 대해 반복.
        var k = 1
        var i = 0
        while (k <= items.size) {
            for (w in 1..capacity) {
                // 현재 item의 무게가 현재 용량보다 큰 경우, item을 제외.
                if (items[i].weight > w) {
                    m[k][w] = m[k - 1][w] // 이전 행의 해를 복사.
                } else {
                    // 더 나은 해를 제공하는 경우 아이템을 포함시.
                    if (m[k - 1][w].totalValue > m[k - 1][w - items[i].weight].totalValue + items[i].value) {
                        m[k][w] = m[k - 1][w] // 이전 행의 해를 복사.
                    } else {
                        // 현재 item을 포함하여 새로운 해를 만듦.
                        m[k][w].selectedItems.addAll(m[k - 1][w].selectedItems)
                        m[k][w].selectedItems.add(items[i])
                        m[k][w].totalValue = m[k - 1][w - items[i].weight].totalValue + items[i].value
                        m[k][w].totalWeight = m[k - 1][w - items[i].weight].totalWeight + items[i].weight
                    }
                }
            }
            k++ // 다음 item으로 이동.
            i++ // item의 index 증가.
        }

        var solution: KSP_Solution = KSP_Solution()
        solution = m[items.size][capacity]

        return solution
    } // end DynKSP_BottomUp()

    fun DynKSP_TopDown(): KSP_Solution? {
        val solutionTable : Array<Array<KSP_Solution?>> =
            Array(items.size + 1) {Array<KSP_Solution?>(this.capacity + 1){KSP_Solution()} }
        for (k in 0..items.size) {
            for (w in 0..this.capacity) {
                solutionTable[k][w] = null
            }
            solutionTable[k][0] = KSP_Solution()
            solutionTable[k][0]!!.selectedItems = ArrayList()
            solutionTable[k][0]!!.totalValue = 0
            solutionTable[k][0]!!.totalWeight = 0
        }
        val index_start = 0
        return _dynKSP_recursive(index_start, this.capacity, solutionTable)
    }

    fun _dynKSP_recursive(index: Int, avail: Int, solTbl: Array<Array<KSP_Solution?>>): KSP_Solution? {
        var solution: KSP_Solution? = KSP_Solution()
        if (index >= num_items || avail <= 0) {
            solution = KSP_Solution()
            solution.selectedItems = ArrayList()
            solution.totalValue = 0
            solution.totalWeight = 0
            return solution
        } else if (solTbl[index][avail] != null) {
            return solTbl[index][avail]
        } else if (this.items[index].weight > avail) {
            solution = _dynKSP_recursive(index + 1, avail, solTbl)
        } else {
            val candidateItem: Item = this.items[index]
            var solution_with: KSP_Solution? = KSP_Solution()
            var solution_without: KSP_Solution? = KSP_Solution()
            solution_with = _dynKSP_recursive(index + 1, avail - candidateItem.weight, solTbl)
            solution_with!!.totalValue += candidateItem.value
            solution_with!!.totalWeight += candidateItem.weight
            solution_with!!.selectedItems.add(candidateItem)
            solution_without = _dynKSP_recursive(index + 1, avail, solTbl)
            solution = if (solution_with!!.totalValue > solution_without!!.totalValue) {
                solution_with
            } else {
                solution_without } }
        solTbl[index][avail] = KSP_Solution()
        solTbl[index][avail]!!.selectedItems = solution!!.selectedItems
        solTbl[index][avail]!!.totalValue = solution!!.totalValue
        solTbl[index][avail]!!.totalWeight = solution!!.totalWeight
        return solution
    }

    fun printSolution(sol : KSP_Solution?) {
        print("total_value = %d, total_weight = %d, selected_items = [".format(sol!!.totalValue, sol!!.totalWeight))
        for (item in sol!!.selectedItems) {
            print("%s, ".format(item))
        }
        print("]\n")
    }
}