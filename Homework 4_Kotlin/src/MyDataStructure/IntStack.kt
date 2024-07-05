package MyDataStructure

class IntStack(private val capacity: Int) {
    private val stack_array: IntArray = IntArray(capacity)
    private var stack_top = -1 // initial position

    fun push(entry: Int): Int {
        if (isFull()) {
            println("Stack is full. Cannot push new element.")
        }
        stack_array[++stack_top] = entry
        return entry
    }

    fun pop(): Int {
        if (isEmpty()) {
            println("Stack is empty. Cannot pop element.")
        }
        var pop_array = stack_array[0]
        for (i in 0 until capacity - 1) {
            stack_array[i] = stack_array[i + 1]
        }
        stack_top--
        return pop_array
    }

    fun isEmpty(): Boolean {
        return stack_top == -1
    }

    fun isFull(): Boolean {
        return stack_top == capacity - 1
    }

    override fun toString(): String {
        var str: String = ""
        for (i in 0..stack_top) {
            str += String.format("%d ".format(stack_array[i]))
        }
        return str
    }
}