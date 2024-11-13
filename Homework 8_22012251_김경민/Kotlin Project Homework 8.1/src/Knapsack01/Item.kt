package Knapsack01

class Item(var name: String, var value: Int, var weight: Int) {
    override fun toString(): String {
        val str: String
        str = String.format("(%s, %d, %d)", name, value, weight)
        return str
    }
}
