fun main(args: Array<String>) {

    var conjuntoNumeros: MutableSet<Int> = mutableSetOf(1, 2, 3, 4)

    imprimirNumeros(conjuntoNumeros)

    conjuntoNumeros.add(10)
    imprimirNumeros(conjuntoNumeros)

    println("tamaño: ${conjuntoNumeros.size}")

    if (4 in conjuntoNumeros) {
        println("Si existe")
    } else {
        println("No existe")
    }

    conjuntoNumeros.remove(4)
    imprimirNumeros(conjuntoNumeros)

    // Si queremos saber la cantidad de elementos mayor a 1
    val cantidad = conjuntoNumeros.count { it > 1 }
    println("Cantidad: $cantidad")


    println()
    println(sumarEnteros(1,2,3,33,645,6,90,32))

}

fun sumarEnteros(vararg numeros: Int): Int {
    var suma = 0
    for (numero in numeros) {
        suma += numero
    }

    return suma
}

fun imprimirNumeros(conjuntoNumeros: MutableSet<Int>) {
    println()
    for (numero in conjuntoNumeros) {
        print(" $numero")
    }
}
