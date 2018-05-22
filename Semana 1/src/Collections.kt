fun main(args: Array<String>) {
    /*val diasSemana: List<String> = listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes")

    val resultado = diasSemana.filter { it == "Lunes" || it == "Miercoles" || it == "Viernes" }
    println("Resultado es $resultado")


    //Tamaño de la coleccion
    println("El tamaño es ${diasSemana.size}")
    //Obtener posición 4
    println("La posicion 4 tiene el valor ${diasSemana.get(4)}")

    //Obtener el primer valor de la colección
    println("El primer elemento de la colección es ${diasSemana.first()}")
    println("El ultimo elemento de la coleccion es${diasSemana.last()}")

    // Imprimir todos los elementos
    println(diasSemana)

    /*
        1. mostrar los que tengan menos de 15 letras
        2. Ordenar alfabeticamente
        3. Convertir a mayuscula
        4.
     */
    val ciudades: List<String> = listOf("Encarnación", "Asunción", "Pedro Juan Cabellero", "Ciudad del Este")

    ciudades
            .filter { it -> it.length < 20 }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }

    println(ciudades)*/

    val dias: MutableList<String> = mutableListOf("Lunes", "Martes")
    // Agregar el resto de los dias
    dias.add("Miercoles")
    println("Dias: $dias")

    dias.add(0, "Domingo")
    println("Dias: $dias")

    if(dias.none()){
        println("La lista no tiene valores")
    } else {
        println("La lista tiene elementos")
    }

    val sinElementos: MutableList<String> = mutableListOf()
    sinElementos.add("Primer elemento")

    println(sinElementos)

    dias.removeAt(0)
    println(dias)

    sinElementos.removeAt(0)
    println(sinElementos)

    println("El primer elemento es ${sinElementos.firstOrNull()}")

    println("El quinto elemento del array es ${sinElementos.elementAtOrNull(5)}")
    println("El quinto elemento del array es ${sinElementos.elementAtOrElse(5, {
        ": No existe 5to elemento"
    })}")

    val equiposParaguay: MutableList<String> = mutableListOf("Olimpia", "Cerro", "Nacional", "Libertad")
    val posicionesEquipos: MutableList<String> = mutableListOf()

    equiposParaguay.forEachIndexed { index, equipo ->
        posicionesEquipos.add("${index+1} : $equipo")
    }

    println("Equipos: $equiposParaguay")
    println("Posiciones: $posicionesEquipos")
}