class Bolsi(val nombre: String, val edad: Int) {
    fun esMayor(fn: (Int) -> Boolean): Boolean {
        return fn(edad)
    }
}

fun mayorEstadoUnidos(edad: Int): Boolean {
    return edad > 21
}

fun mayorParaguay(edad: Int): Boolean {
    return edad > 18
}

fun main(args: Array<String>) {
    val personaParaguaya = Bolsi("Pepe", 20)
    val personaUSA = Bolsi("John", 19)

    // Suponiendo que nos encontremos utilizando la app en Paraguay
    if(personaUSA.esMayor(::mayorParaguay)) {
        println("${personaUSA.nombre} puede tomar cerveza en Paraguay")
    } else {
        println("${personaUSA.nombre} no puede tomar cerveza en Paraguay")
    }

    if(personaUSA.esMayor(::mayorEstadoUnidos)) {
        println("${personaUSA.nombre} puede tomar cerveza en USA")
    } else {
        println("${personaUSA.nombre} no puede tomar cerveza en USA")
    }
}