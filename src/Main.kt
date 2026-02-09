import java.sql.SQLOutput

fun main() {

    val passengers = listOf<Passenger>(
        Passenger("Mick", 18, true, 40.0),
        Passenger("Jack", 12, false, 20.0),
        Passenger("Stephanie", 38, true, 10.0),
        Passenger("Alexander", 25, false, 60.0),
        Passenger("Rok", 42, true, 100.0),
        Passenger("Jay", 45, false, 70.0),
        Passenger("Theresa", 52, true, 55.0),
        Passenger("Alex", 55, false, 82.0),
        Passenger("Jessica", 61, true, 90.0),
    )

    val economyFlight = EconomyFlight("1", "New York")
    val bussinesFlight = BussinessFlight("2", "London")

    /**
     * Find: Use firstOrNull to find a passenger named "Alice".
     * Create: An EconomyFlight and a BusinessFlight instance.
     * Calculate: Use ?.let on Alice to:
     * Calculate her Economy price.
     * Calculate her Business price.
     * Print the difference: "Upgrade for Alice costs: $[Difference]".
     */
    println(passengers.firstOrNull { passenger -> passenger.name == "Alice" }?.name ?: "Alice was not found")

    passengers.firstOrNull { passenger -> passenger.name == "Rok" }?.let {
        val economyPrice = economyFlight.calculateTicketPrice(it)
        val bussinesPrice = bussinesFlight.calculateTicketPrice(it)
        println("Bussines price : $bussinesPrice")
        println("Economy price : $economyPrice")
        println("Diff in  price : ${bussinesPrice - economyPrice}")
    }
    println()

    /**
     * The Goal: The Pilot needs a safety report before takeoff
     * Check: Are all passengers carrying less than 50kg? (all)
     * Check: Are any passengers under the age of 12? (any)
     * Check: Do none of the passengers have a name called "Unknown"? (none)
     * The Action: Try to print three boolean (True/False) results using those three functions on your passengers list.
     */
    val above50 = passengers.all { passenger -> passenger.luggageWeight > 50 }
    val below12 = passengers.any { passenger -> passenger.age < 12 }
    val unknown = passengers.none { passenger -> passenger.name == "Unknown" }

    println("Does all passengers have luggage above 50 kg : $above50")
    println("Is there any passengers age below 12 : $below12")
    println("Is there any passenger with name Unknown : $unknown")
    println()

    /**
     * Let's see how much the airline makes if everyone on your list boards the Business Flight.
     */
    val bussinesSum = passengers.sumOf { passenger -> bussinesFlight.calculateTicketPrice(passenger) }
    println("IF all passengers board Bussinses Flight sum is : $bussinesSum")
    println()

    /**
     * We need a list of names for all passengers who are carrying "Heavy" luggage (more than 25kg).
     */
    val heavyPassenger = passengers.filter { passenger -> passenger.luggageWeight > 25 }
        .map { passenger -> passenger.name }
    println("Passengers with heavy luggage > 25kg : $heavyPassenger")
    println()

    /**
     * Imagine you have a list of "Potential VIPs" (Strings), but some entries are null. You want to find their corresponding Passenger objects.
     */
    val listOfVIP = listOf<String?>("Alice", null, "Rok", null)
    listOfVIP.filterNotNull().map { name -> passengers.find { passenger -> passenger.name == name } }.filterNotNull()

}


data class Passenger(val name:String, val age:Int, val hasPriorityBoarding:Boolean, val luggageWeight:Double) {

}

abstract class Flight(
    val flightNumber:String,
    val destination:String) {

    abstract fun calculateTicketPrice(p: Passenger):Double
}

class EconomyFlight(
    flightNumber: String,
    destination: String,
    val ticket: Double = 100.0
) : Flight(flightNumber, destination) {

    override fun calculateTicketPrice(p: Passenger): Double {
        return ticket
    }
}

class BussinessFlight (
    flightNumber: String,
    destination: String,
    val ticket: Double = 100.0
) : Flight(flightNumber, destination) {

    override fun calculateTicketPrice(p: Passenger): Double {
        return ticket + (p.luggageWeight * 20)
    }
}



