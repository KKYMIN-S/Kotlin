package HeapPriQ


open class GenericArray <E> {
    protected var genArray: Array<Any?> ? = null
    protected var capacity = 0
    protected var size = 0
    constructor(capa: Int) {
        genArray = arrayOfNulls(capa)
        capacity = capa
        size = 0
    }
}
