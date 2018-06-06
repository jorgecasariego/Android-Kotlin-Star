/**
 * Ejercio 1:
 * ¿Cómo haríamos si quisiéramos crear un lambda que multiplique su argumento por 100 y
 * que luego retorne el valor pero en String?
 */
fun main(args: Array<String>) {

    val numero = { x: Int ->
        (x * 100).toString()
    }

    val numeroToString = numero(50)

    println("El valor del entero to String es $numeroToString")

}

fun multiplicarPor100(x: Int): String {
    val resultado = x * 100

    return resultado.toString()
}