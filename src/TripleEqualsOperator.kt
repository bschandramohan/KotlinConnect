
class AClass() {
    var i: Int = 0

    constructor(value: Int): this() {
        i = value
    }
}

class BClass(var i: Int = 0) {
    override fun equals(other: Any?): Boolean {
        if (other is BClass) {
            return other.i == this.i
        }
        return super.equals(other)
    }
}

fun main(args: Array<String>) {
    val a = 1
    val b = 1
    // Note: === is same as == for primitives
    println(a === 1)
    println(a === b)
    println(a == 1)
    println(a == b)

    println("\nInteger object comparison")
    val aInt = Integer(1)
    val bInt = Integer(1)
    // Note: === is not same as == for Integer objects
    println(aInt == bInt)
    println(aInt === bInt)

    println("\nString object comparison")
    val aString = "2"
    val bString = "2"
    // Note: === is not same as == for String objects
    println(aString == bString)
    println(aString === bString)

    println("\nCustom object comparison")
    val aObj = AClass(3)
    val bObj = AClass(3)
    val cObj = bObj

    println("aObj == bObj is ${aObj == bObj}")
    println("bObj == cObj is ${bObj == cObj}")
    println("cObj == aObj is ${cObj == aObj}")
    println("")
    println("aObj === bObj is ${aObj === bObj}")
    println("bObj === cObj is ${bObj === cObj}")
    println("cObj === aObj is ${cObj === aObj}")

    println("\nCustom object comparison")
    val aObj1 = BClass(3)
    val bObj1 = BClass(3)
    val cObj1 = bObj1

    println("aObj1 == bObj1 is ${aObj1 == bObj1}")
    println("bObj1 == cObj1 is ${bObj1 == cObj1}")
    println("cObj1 == aObj1 is ${cObj1 == aObj1}")
    println("")
    println("aObj1 === bObj1 is ${aObj1 === bObj1}")
    println("bObj1 === cObj1 is ${bObj1 === cObj1}")
    println("cObj1 === aObj1 is ${cObj1 === aObj1}")
}

