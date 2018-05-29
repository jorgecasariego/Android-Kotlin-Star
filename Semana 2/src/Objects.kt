object Matematica {
    val PI = 3.14
    fun cuadrado(elemento: Int) = elemento * elemento
}

fun main(args: Array<String>) {
    println("El valor de PI es ${Matematica.PI}")

    println("EL cuadrado de 2 es ${Matematica.cuadrado(2)}")
}