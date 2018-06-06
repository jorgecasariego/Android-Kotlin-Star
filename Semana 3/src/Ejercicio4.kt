// Ejercicio 4: Crear una expresión lambda que acepte 2 enteros como parametros
// y retorne el producto de esos dos enteros
fun main(args: Array<String>) {
    val producto = { numero1: Int, numero2: Int -> numero1 * numero2 }

    val numero1: Int = 50
    val numero2: Int = 10
    val resultado = producto(numero1, numero2)

    println("$numero1 x $numero2 = $resultado")
}