fun operacion(valor1: Int, valor2: Int, fn: (Int, Int)->Int): Int {
    return fn(valor1, valor2)
}

fun main(args: Array<String>) {
    val suma = operacion(1, 2,  {x, y -> x + y})
    println("El valor de la suma es $suma")

    val potencia = operacion(2, 3) {x, y ->
        var valor = 1

        for(i in 1..y) {
            valor *= x
        }

        valor
    }

    println("El resultado de hacer 2^3 es $potencia")
}