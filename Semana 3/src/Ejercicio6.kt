/**
 - Cargar un array de enteros con los valores 1, 2, 3, 4, 5, 6
 - Usando una expresión lambda multiplicar cada valor del array de enteros por 4 utilizando
 el keyword “it” y luego imprimir
 */

fun main(args: Array<String>) {
    val enteros = intArrayOf(1, 2, 3, 4, 5, 6)

    // Manera larga
    enteros.forEach { numero ->  print(" ${numero * 4}")}
    // Manera corta
    enteros.forEach { print(" ${it * 4}") }

}