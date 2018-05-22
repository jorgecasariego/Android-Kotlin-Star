fun main(args: Array<String>) {
    val diasSemana = arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Virnes")

    println("El segundo elemento del array es ${diasSemana.get(2)}")

    println("El tamaño del array es de ${diasSemana.size}")

    val variablesMixtas = arrayOf("Hola", false, 10, true)

    println("El cuarto elemento del array es ${variablesMixtas.get(3)}")

    //Modificar un elemento del array de dias
    diasSemana.set(4, "Viernes")
    println("La posición 4 del array es ${diasSemana.get(4)}")

    val valoresEnteros1 = arrayOf<Int>(1, 2, 3, 4)
    val valoresEnteros2 = intArrayOf(5, 6, 7, 8)

    // Recorrer un array de elementos
    for (dia in diasSemana) {
        print(" $dia")
    }

    println()
    for ((posicion, dia) in diasSemana.withIndex()) {
        println("El $dia esta en la posición $posicion")
    }

    diasSemana.forEach {
        println("El elemento actual es $it")
    }

    println()
    println()
    diasSemana.forEach { diaActual ->
        println("Dia: $diaActual")
    }
}