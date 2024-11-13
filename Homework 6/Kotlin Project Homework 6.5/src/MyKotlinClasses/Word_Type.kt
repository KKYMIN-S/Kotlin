package MyKotlinClasses

class Word_Type(private val word: String, private val type: String) {
    override fun toString(): String {
        return word + "_" + type
    }
}