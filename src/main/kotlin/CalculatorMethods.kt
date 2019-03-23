// Generics for Calculator ; Refer to https://discuss.kotlinlang.org/t/how-to-write-generic-functions-for-all-numeric-types/7367
// for some inspiration on making it work

fun <T: Number> doSum(a: T, b: T) : T {
    val result : Number = when {
        a is Int && b is Int -> a + b
        a is Long && b is Long -> a + b
        a is Float && b is Float -> a + b
        a is Double && b is Double -> a + b
        else -> throw IllegalArgumentException()
    }

    @Suppress("UNCHECKED_CAST")
    return result as T
}

fun <T: Number> doDiff(a: T, b: T) : T {
    val result : Number = when {
        a is Int && b is Int -> a - b
        a is Long && b is Long -> a - b
        a is Float && b is Float -> a - b
        a is Double && b is Double -> a - b
        else -> throw IllegalArgumentException()
    }

    @Suppress("UNCHECKED_CAST")
    return result as T
}

fun <T: Number> doOperation(a: T, b: T, operationToPerform: (T, T) -> T ) {
    println(operationToPerform(a, b))
}

fun main(args: Array<String>) {

    println(doSum(3, 5))
    println(doSum(3.6, 5.2))

    doOperation (2, 3, ::doSum) // Convention to call Higher order function
    doOperation (8, 5, ::doDiff)
    doOperation (5, 8) { x, y -> x * y }

    doOperation (2.5, 3.6, ::doSum)
    doOperation (8, 5, ::doDiff)
    doOperation (5.9, 8.6) { x, y -> x * y }
}


fun sumInts(a: Int, b: Int) : Int = a + b

// https://discuss.kotlinlang.org/t/how-to-pass-a-function-as-parameter-to-another/848
fun operationInts(a: Int, b: Int, operatorToPerform: (Int, Int) -> Int) {
    println(operatorToPerform(a, b))
}

