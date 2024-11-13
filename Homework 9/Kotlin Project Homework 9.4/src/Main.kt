/* Kotlin Project Homework 9.4
 * Programmed by Kim K.M. (22012251)
 * Last Updated : 2024. 10. 25.
 * WeightedGraph의 응용, 최단거리 경로 탐색
 */

import java.io.File
import java.util.*
import Graph.*

fun printPath(path: List<Vertex?>) {
    for (i in path.indices) {
        val v: Vertex? = path[i]
        print("%s".format(v!!.getName()))
        if (i != path.size - 1)
            print("->")
    }
    println()
}

fun main(args: Array<String>) {
    val fname = "Graph_KR_11_cities.txt"
    val fin = Scanner(File(fname))
    var src: String?
    var dest: String?
    var weight: Int
    val wgraph = WeightedGraph("WeightedGraph - KR (11 Cities)")
    var vrtx: Vertex
    var vrtx_src: Vertex?
    var vrtx_dest: Vertex?

    while (fin.hasNext()) {
        src = fin.next()
        dest = fin.next()
        weight = fin.nextInt()

        if (wgraph.findVertex(src) == null) {
            wgraph.addVertex(src)
        }
        vrtx_src = wgraph.findVertex(src)
        if (wgraph.findVertex(dest) == null) {
            wgraph.addVertex(dest)
        }
        vrtx_dest = wgraph.findVertex(dest)
        wgraph.addWeightedEdge(vrtx_src!!, vrtx_dest!!, weight)
    }
    fin.close()

    wgraph.printWeightedGraph()
    wgraph.initDistTable()
    wgraph.printDistTable()

    var path: List<Vertex?>
    val vStart: Vertex? = wgraph.findVertex("GJ")
    val vEnd: Vertex? = wgraph.findVertex("SC")

    print("DepthFirstSearch (%s -> %s) : ".format(vStart!!.getName(), vEnd!!.getName()))
    path = wgraph.DepthFirstSearch(vStart, vEnd)
    printPath(path)
    print("DepthFirstSearch (%s -> %s) : ".format( vEnd.getName(), vStart.getName()))
    path = wgraph.DepthFirstSearch(vEnd, vStart)
    printPath(path)
    print("BreadthFirstSearch (%s -> %s) : ".format( vStart.getName(), vEnd.getName()))
    path = wgraph.BreadthFirstSearch(vStart, vEnd)
    printPath(path)
    print("BreadthFirstSearch (%s -> %s) : ".format( vEnd.getName(), vStart.getName()))
    path = wgraph.BreadthFirstSearch(vEnd, vStart)
    printPath(path)
    print("Dijkstra ShortestPathFirst (%s -> %s) : " .format( vStart.getName(), vEnd.getName()))
    path = wgraph.DijkstraShortestPath(vStart, vEnd)
    printPath(path)
    print("Dijkstra ShortestPathFirst (%s -> %s) : " .format( vEnd.getName(), vStart.getName()))
    path = wgraph.DijkstraShortestPath(vEnd, vStart)
    printPath(path)
}