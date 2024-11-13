package Graph

class WeightedEdge {
    private var src: Vertex? = null
    private var dest: Vertex? = null
    private var weight = 0

    constructor(src: Vertex?, dest: Vertex?, weight: Int) {
        this.src = src
        this.dest = dest
        this.weight = weight
    }
    override fun toString(): String {
        return src.toString() + "->" + dest
    }
    fun getSrc(): Vertex? {
        return src
    }
    fun getSrcVID(): Int {
        return src!!.getVID()
    }
    fun getDestVID(): Int {
        return dest!!.getVID()
    }
    fun getDest(): Vertex? {
        return dest
    }
    fun getWeight(): Int {
        return weight
    }
}