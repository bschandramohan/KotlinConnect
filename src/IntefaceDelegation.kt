// Interface Delegation:  Use instead of Abstract classes and lots of subclasses. Adds behavior

// Animal - Land , Water
// Animal - Herbivore, Carnivore

class Animal(stayType: StayType, eatType: EatType) : StayType by stayType, EatType by eatType {
    override fun toString(): String {
        return "Animal of StayType=$stayType and eatingType=$eatType"
    }
}

interface StayType {
    val stayType: String
}

interface EatType {
    val eatType: String
}

object LandStay : StayType {
    override val stayType: String = "LAND"
}

object WaterStay : StayType {
    override val stayType: String = "WATER"
}

object AirStay : StayType {
    override val stayType: String = "AIR"
}

object Herbivore: EatType {
    override val eatType = "HERBIVORE"
}

object Carnivore: EatType {
    override val eatType = "CARNIVORE"
}

fun main(args: Array<String>) {
    val lion = Animal(LandStay, Carnivore)
    val crownFish = Animal(WaterStay, Herbivore)

    println(lion)
    println(crownFish)
}

