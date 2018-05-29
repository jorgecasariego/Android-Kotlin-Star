/**
    1. Definir una función de orden superior llamada operar.
    2. Llegan como parámetro dos enteros y una función.
    3. En el bloque de la función llamar a la función que llega como parámetro y enviar los dos primeros parámetros.
    4. La función retorna un entero.
 */

// 1. y 2.
fun operar(valor1: Int, valor2: Int, fn: (Int, Int) -> Int): Int {
    // 3.
    return fn(valor1, valor2)
}

fun sumarValores(valor1: Int, valor2: Int) = valor1 + valor2

fun restarValores(valor1: Int, valor2: Int) = valor1 - valor2

fun main(args: Array<String>) {
    val resultadoSuma = operar(2,2, ::sumarValores)
    println("La suma de 2 + 2 es $resultadoSuma")

    val resultadoResta = operar(5,2, ::restarValores)
    println("La resta de 5 - 2 es $resultadoResta")
}