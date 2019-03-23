
// open keyword required for ability to use subclasses
open class Foo {
    open fun f() { println("Foo.f()") }
    open val x: Int get() = 1
}

class Bar : Foo() {
    override fun f() {
        super.f()
        println("Bar.f()")
    }

    override val x: Int
        get() = super.x + 1

    inner class Baz {
        fun g() {
            super@Bar.f() // Calls Foo's implementation of f()
            println(super@Bar.x) // Uses Foo's implementation of x's getter

            f()
            println(x)
        }
    }

    val baz = Baz()
}

open class A {
    open fun f() { print("A") }
    fun a() { print("a") }
}

// interfaces cannot store state
interface B {
    fun f() { print("B") } // interface members are 'open' by default
    fun b() { print("b") }
}

class C : A(), B {
    // The compiler requires f() to be overridden:
    override fun f() {
        super<A>.f() // call to A.f()
        super<B>.f() // call to B.f()
    }
}


class TryClass(className: String) {

    var classNameToPrint: String = ""

    init {
        classNameToPrint = className
    }

    override fun toString() = """
    TryClass {
        classNameToPrint = $classNameToPrint
    }
    """
}

class TryClass0() {

    var classNameToPrint: String = ""

    constructor(className: String) : this() {
        classNameToPrint = className
    }

    override fun toString() = """
    TryClass0 {
        classNameToPrint = $classNameToPrint
    }
    """
}


fun main(args: Array<String>) {
    val obj1 = TryClass("TryClass name")
    println(obj1)

    val obj2 = TryClass0("TryClass 0")
    println(obj2)

    val bar = Bar()
    val baz = bar.baz
    baz.g()
}
