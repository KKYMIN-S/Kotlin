package HeapPriQ

class Event : Comparable<Event>{
    protected var _priority = 0
    protected var _value: String? = null

    constructor() {}
    constructor(key: Int, value: String?) {
        _priority = key
        _value = value
    }

    fun getPriority(): Int {
        return _priority
    }

    fun getValue(): String? {
        return _value
    }

    override fun compareTo(other: Event): Int {
        return (this._priority - other._priority)
    }

    override fun toString(): String {
        return String.format("Ev(%d, %s)", _priority, _value)
    }
}
