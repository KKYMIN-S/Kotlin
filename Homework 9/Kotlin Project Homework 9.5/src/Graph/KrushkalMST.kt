/* class KrushkalMST */
package Graph
import java.util.*
import java.util.function.ToIntFunction


class KrushkalMST {
    var vrtx_group: IntArray
    var prioQ_wedge: PriorityQueue<WeightedEdge>? = null

    constructor(wgraph: WeightedGraph) {
        vrtx_group = IntArray(wgraph.num_vrtx)
        prioQ_wedge = PriorityQueue<WeightedEdge>(
            wgraph.wedges.size,
            Comparator.comparingInt<WeightedEdge>(ToIntFunction<WeightedEdge> { other: WeightedEdge -> other.getWeight() })
        )

        // 모든 edge를 우선순위 큐에 추가
        for (i in 0 until wgraph.wedges.size) {
            prioQ_wedge!!.add(wgraph.wedges[i])
        }

        // vrtx_group 초기화
        for (i in 0 until wgraph.num_vrtx) {
            vrtx_group[i] = i
        }

        val we_MST = ArrayList<WeightedEdge>()
        var selected_edges = 0

        while (selected_edges < wgraph.num_vrtx - 1) {
            val edge = prioQ_wedge!!.remove()

            // cycle을 생성하는지 확인
            val u_grp: Int = find_vrtx_group(edge.getSrcVID())
            val v_grp: Int = find_vrtx_group(edge.getDestVID())

            if (u_grp == v_grp) {
                // cycle을 생성하는 경우 무시
                continue
            } else {
                // MST에 이 edge를 추가
                we_MST.add(edge)
                selected_edges++

                union_vrtx_group(u_grp, v_grp)
            }
        }

        // MST 출력
        println("Edges for MinimumSpanningTree (by KrushkalMST) : ")
        for (i in we_MST.indices) {
            val edge = we_MST[i]
            System.out.printf(
                "WeightedEdge(%s -> %s, weight %2d)\n",
                edge.getSrc()!!.getName(),
                edge.getDest()!!.getName(),
                edge.getWeight()
            )
        }
    }

    fun find_vrtx_group(vid: Int): Int {
        return if (vrtx_group[vid] != vid) find_vrtx_group(vrtx_group[vid]) else vid
    }

    fun union_vrtx_group(u: Int, v: Int) {
        val u_grp = find_vrtx_group(u)
        val v_grp = find_vrtx_group(v)
        vrtx_group[v_grp] = u_grp
    }
}