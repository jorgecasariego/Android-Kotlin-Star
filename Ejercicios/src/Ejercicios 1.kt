fun main(args: Array<String>) {
    // Ejercicio 1: Crear una expresión lambda que acepte 2
    // enteros como parametros
    // y retorne el producto de esos dos enteros

    val producto = { a: Int, b: Int -> a * b}
    val resultadoProducto = producto(5, 4)
    println(resultadoProducto)
}