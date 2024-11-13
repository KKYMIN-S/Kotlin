package MyKotlinClasses

class Thesaurus {
    private var keyword: String? = null
    private var type: String? = null // noun, verb, adj, adv, etc.
    private val list_thesaurus: ArrayList<String> = ArrayList() // 최대 10개의 단어, init

    constructor()
    constructor(word: String?, type: String?, array_thesaurus: Array<String>) {
        keyword = word
        this.type = type
        for (str in array_thesaurus) {
            list_thesaurus.add(str)
        }
    }

    fun setKeyWord(keyWord: String) {
        this.keyword = keyWord
    }

    fun setType(Type: String) {
        this.type = Type
    }

    fun addThesaurus(word: String?) {
        list_thesaurus.add(word ?: "default_value")
    }

    override fun toString(): String {
        var str = "{ " // 초기화
        for (i in 0 until list_thesaurus.size) {
            str += "${list_thesaurus[i]}, "
        }
        str += " }"
        return str
    }
}

