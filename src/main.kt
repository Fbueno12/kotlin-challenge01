import java.lang.Exception
import java.math.BigDecimal

data class Seat(val row: Int, val num: Int, val price: BigDecimal, var description: String) {
    // YOU MAY NOT EDIT THIS CLASS!
    override fun toString(): String = "Seat $row-$num $$price ($description)"
}

class Theater {
    // SEAT PRICES:
    // Seats in row 14 and 15 cost 14.50
    // Seats in rows 1 to 13 and numbered 1 to 3 or 34 to 36 cost 16.50
    // All other seats in row 1 cost 21.00
    // All other seats cost 18.00

    // SEAT DESCRIPTIONS:
    // Seats in row 15: "Back row"
    // Seats in row 14: "Cheaper row"
    // All other rows, seats 1 to 3 and 34 to 36: "Restricted view"
    // All other seats in rows 1 and 2: "Best view"
    // all other seats: "Standard seat"

    fun createSeats(): List<Seat> {

        val seats = mutableListOf<Seat>()

        (1..15).forEach { row ->
            (1..36).forEach { num ->
                val seatDescritives: Map<String, Any> = getDescritive(row, num)
                val seat = Seat(row, num, seatDescritives["price"] as BigDecimal, seatDescritives["description"] as String)
                seats.add(seat)
            }
        }

        return seats.toList()
    }

    private fun getDescritive(row: Int, num: Int): Map<String, Any> {
        val price: BigDecimal = when(row) {
            14, 15 -> BigDecimal(14.50)
            1 -> BigDecimal(21.00)
            else -> if ((1..3).contains(num) || (34..36).contains(num)) {
                BigDecimal(16.50)
            } else {
                BigDecimal(18.00)
            }
        }
        val description: String = when(row) {
            14 -> "Cheaper row"
            15 -> "Back row"
            else -> if ((1..3).contains(num) || (34..36).contains(num)){
                "Restricted view"
            } else if (row == 1 || row == 2) {
                "Best view"
            } else {
                "Standard seat"
            }
        }

        return mapOf("price" to price, "description" to description)
    }

    val seats = createSeats() //THIS MUST BE AN IMMUTABLE LIST
}

fun main(args: Array<String>) {
    val cheapSeats = Theater().seats.filter { it.price == BigDecimal(14.50) }
    for (seat in cheapSeats) println(seat)
}