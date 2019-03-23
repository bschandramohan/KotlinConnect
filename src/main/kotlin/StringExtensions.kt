fun main(args: Array<String>) {
    "Chandramohan".randomize()
    "Chandram".randomize()

    println("${"malayalam".isPalindrome()}")
    println("${"Chandra".isPalindrome()}")
}

fun <T> swap(array: Array<T>, indexA: Int, indexB: Int) {

    val size =array.size
    if (size < 2) return

    if (indexA == indexB) return
    if (indexA < 0 || indexA >= size) return
    if (indexB < 0 || indexB >= size) return

    val temp = array[indexA]
    array[indexA] = array[indexB]
    array[indexB] = temp
}

fun String.randomize() {
    val chars = this.toCharArray().toTypedArray()
    val lastIndex =  chars.size - 1
    for (index in 0..(lastIndex / 2)) {
        swap(chars, index, lastIndex - index)
        // println("Swap $index , ${chars.size - index - 1} = ${chars.contentToString()}")
    }

    println(chars.joinToString(""))
}

fun String.isPalindrome() : Boolean {
    for ((index, character) in this.withIndex()) {
        if (character != this[this.length - index -1])
            return false
    }
    return true
}
