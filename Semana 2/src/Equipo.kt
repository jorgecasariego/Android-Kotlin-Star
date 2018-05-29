data class Equipo(val nombre: String, val fundacion: Int, var ubicacion: String)

fun main(args: Array<String>) {
    val equipo1 = Equipo("Libertad", 1905, "Asunción")
    val equipo2 = Equipo("3 de febrero", 1970, "Ciudad del este")

    println(equipo1)
    println(equipo2)

    // Obteniendo el nombre del equipo 1
    println("El nombre el equipo 1 es ${equipo1.nombre}")

    equipo1.ubicacion = "San Lorenzo"

    println(equipo1)

    val gumarelo = equipo1.copy("Club Libertad")
    println("El equipo modificado es: $gumarelo")

    println("El primer parametro tiene el siguiente valor: ${gumarelo.component1()}")
    println("El segundo parametro tiene el valor: ${gumarelo.component2()}")

    //val nombreEquipo: String = gumarelo.nombre
    //val fundacion: Int = gumarelo.fundacion
    //val ubicacion: String = gumarelo.ubicacion

    val(nombreEquipo, fundacionEquipo, ubicacionEquipo) = gumarelo

    println("Fundacion: $fundacionEquipo")
    if (fundacionEquipo > 1950) {
        println("Es un equipo nuevo")
    } else {
        println("Es uno de los primeros equipos de la APF")
    }

    val equipos: MutableList<Equipo> = mutableListOf()
    equipos.add(equipo1)

    println("Equipos: $equipos")

    equipo1.ubicacion = "Fernando de la mora"
    equipos.add(equipo1)

    println("Equipos: $equipos")

}