package SortedMultiMap

import java.util.*

class Thesaurus (var keyword: String?, var type: String?){
    var treeSet_thesaurus : TreeSet<String> = TreeSet<String>()
    fun addThesaurus(word: String) {
        treeSet_thesaurus.add(word)
    }
    override fun toString(): String {
        var str = String.format("%5s [%3s] : {", keyword, type)
        treeSet_thesaurus.forEachIndexed{index, entry ->
            if (index == treeSet_thesaurus.size - 1)
                str += "$entry" else
                str += "$entry, "
        }
        str += "}"
        return str
    }
}
