/* Kotlin Project Homework 9.5
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 25.
 * Minimum Spanning Tree 그래프
 */

import java.io.File
import java.io.FileNotFoundException
import Graph.*

@Throws(FileNotFoundException::class)
fun main() {
    val fname = "Graph_KR_11.txt" // 파일 이름
    val wgraph = WeightedGraph(fname)
    readGraphFromFile(fname, wgraph)
    KrushkalMST(wgraph)
}

// 파일에서 데이터를 읽어와 WeightedGraph에 저장하는 함수
fun readGraphFromFile(fileName: String, graph: WeightedGraph) {
    try {
        val file = File(fileName)
        file.forEachLine { line ->
            val tokens = line.split(" ")
            if (tokens.size == 3) {
                val src = tokens[0]  // 첫 번째 정점
                val dest = tokens[1]  // 두 번째 정점
                val weight = tokens[2].toInt()  // 가중치

                // 정점이 이미 존재하는지 확인하고 추가 (중복 허용 x)
                val vertexSrc = graph.addVertex(src) ?: graph.findVertex(src)
                val vertexDest = graph.addVertex(dest) ?: graph.findVertex(dest)

                // 두 정점 간에 가중치를 가진 간선을 추가
                if (vertexSrc != null && vertexDest != null) {
                    graph.addWeightedEdge(vertexSrc, vertexDest, weight)
                }
            }
        }
    } catch (e: FileNotFoundException) {
        println("File not found: $fileName")
    }
}