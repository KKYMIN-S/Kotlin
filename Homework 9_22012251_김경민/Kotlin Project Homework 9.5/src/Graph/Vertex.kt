package Graph

class Vertex {
    var vname: String? = null
    var vid = 0
    var wedges: MutableList<WeightedEdge> = ArrayList<WeightedEdge>()
    var children: List<Vertex> = ArrayList()
    var visited = false
    var prev: Vertex? = null
    var accDist = 0
    var level = 0

    constructor(name: String?) // 생성자
    {
        vname = name
        visited = false
        prev = null
        accDist = 0
    }
    fun getName(): String? {
        return vname
    }
    fun setVID(vid: Int) {
        this.vid = vid
    }
    fun getVID(): Int {
        return vid
    }
    fun addWeightedEdge(vrtx_dest: Vertex?, weight: Int) {
        val wedge = WeightedEdge(this, vrtx_dest, weight)
        wedges.add(wedge)
    }
    fun getWeightedEdges(): List<WeightedEdge> {
        return wedges
    }
    override fun toString(): String {
        return vname!!
    }
}