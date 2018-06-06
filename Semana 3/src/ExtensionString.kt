fun String.primeraMitad(): String {
    return this.substring(0..this.length/2-1)
}

fun String.segundaMitad(): String {
    return this.substring(this.length/2..this.length-1)
}

fun IntArray.imprimir() {
    this.forEach { kotlin.io.print(" $it") }
}

fun main(args: Array<String>) {
    val cadena = "Esto es un ejemplo de string con varias palabras"

    println("Primera Mitad: ${cadena.primeraMitad()}")
    println("Primera Mitad: ${cadena.segundaMitad()}")

    val cadenaEnteros = IntArray(10){it}
    cadenaEnteros.imprimir()
}
