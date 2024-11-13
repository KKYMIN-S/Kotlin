package SortedMultiMap

import java.util.*

class SortedMultiMap<K, V> {
    private val sortedMap: MutableMap<K, MutableCollection<V>?> = TreeMap()

    fun put(key: K, value: V) {
        if (!sortedMap.containsKey(key)) {
            sortedMap[key] = mutableListOf()
        }
        sortedMap[key]?.add(value)
    }
    operator fun get(key: K): Collection<V>? {
        return sortedMap[key]
    }
    fun remove(key: K, value: V) {
        sortedMap[key]?.remove(value)
    }
    fun containsKey(key: K): Boolean {
        return sortedMap.containsKey(key)
    }
    fun containsEntry(key: K, value: V): Boolean {
        return sortedMap[key]?.contains(value) ?: false
    }
    fun keySet(): Set<K> {
        return sortedMap.keys
    }
    fun values(): Collection<MutableCollection<V>?> {
        return sortedMap.values
    }
    fun entrySet(): Set<Map.Entry<K, MutableCollection<V>?>> {
        return sortedMap.entries
    }
    override fun toString(): String {
        return sortedMap.toString()
    }
}