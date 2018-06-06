/*
    // Ejercicio 5:
    // 1. Crear una clase Persona que tenga 2 atributos (nombre y edad)
    // 2. Crear una lista de personas con nombre y edad
    // 3. Seleccionar la persona de mayor edad utilizando la función maxBy y la edad de la persona
 */
data class Persona(val nombre: String, val edad: Int)

fun main(args: Array<String>) {
    val personas = listOf(
            Persona("Juan", 14),
            Persona("Maria", 45),
            Persona("Marcos", 23),
            Persona("Tamara", 65)
    )

    val personaDeMayorEdad = personas.maxBy { persona -> persona.edad }
    println("La persona de mayor edad tiene ${personaDeMayorEdad?.edad} años")

    val personasConM = personas.filter { it.nombre.startsWith('M') }
    println("La cantidad de personas que tienen nombre con 'M' es ${personasConM.size}")
    println(personasConM)
}