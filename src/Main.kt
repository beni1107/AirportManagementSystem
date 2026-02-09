fun main() {

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



