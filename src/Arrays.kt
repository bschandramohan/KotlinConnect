fun main(args: Array<String>) {

    val arrayNumbers = arrayOf( 1, 2, 3)
    val arrayNumbers1 = Array(15, {i -> i*i})
    val arrayNumbers2 = Array(15) {i -> i*i} // Last parameter can be moved out

    printArrays(arrayNumbers)
    printArrays(arrayNumbers1)
    printArrays(arrayNumbers2)
}

// NOTE: For generic functions, <T> should be declared before using it in params
fun <T> printArrays(printArray: Array<T>) {
    for (t in printArray) {
        println(t)
    }
    println()
}
