/*
    // Ejercicio 2:
    // 1. Crear una clase Persona que tenga 2 atributos (nombre y edad)
    // 2. Crear una lista de personas con nombre y edad
    // 3. Seleccionar la persona de mayor edad utilizando la función maxBy y la edad de la persona
 */
data class Persona(val nombre: String, val edad: Int)

fun main(args: Array<String>) {
    val personas = listOf(
            Persona("Juan", 15),
            Persona("Andres", 45),
            Persona("Jorge", 33),
            Persona("Marcos", 5),
            Persona("Alicia", 25),
            Persona("Carlos", 55)
    )

    val personaSeleccionada = personas.maxBy { persona -> persona.edad }
    println("La persona seleccionada es $personaSeleccionada")
    println("La persona seleccionada es ${personaSeleccionada?.nombre}")
    println("La persona seleccionada es ${personaSeleccionada?.edad}")

    // Ejercicio 3: utilizando la lista de personas del ejercicio 2
    // 4. Filtrar la lista de personas el cual su nombre  comienzan con la letra "A"
    // utilizando el metodo filter y el metodo startsWith()
    val personasConA = personas.filter { it.nombre.startsWith("A") }

    println("Personas con A: $personasConA")
}