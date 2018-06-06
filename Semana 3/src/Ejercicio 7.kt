// Como usar lambdas con Array
fun main(args: Array<String>) {

    // Inicializar un array de enteros con un valor cualquiera
    var cadenaEnteros = IntArray(10){ 5 }

    cadenaEnteros.forEach { print(" $it") }

    println()
    // Inicializar otro array de enteros con valores: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    var cadenaEnteros2 = IntArray(10){it}
    cadenaEnteros2.forEach { print(" $it") }

    println()
    // Inicializar otro array de enteros con valores: 0, 2, 4, 6, 8, 10, 12, 14, 16, 18
    var cadenaEnteros3 = IntArray(10){ it * 2}
    cadenaEnteros3.forEach { print(" $it") }
}