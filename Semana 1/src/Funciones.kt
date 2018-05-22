fun main(args: Array<String>) {
    mostrarMiNombre()

    println("Mi edad es ${calcularEdad(1982)}")

    val numero1 = 4
    val numero2 = 5
    val resultado = multiplicar(numero1, numero2)
    println("El resultado de multiplicar $numero1 y $numero2 es $resultado")

    val valor1 = "Hola"
    val valor2 = " Mundo"

    println("Concatenar: " + concatenar(valor1, valor2))

    //Ejercicio
    // 1. Crear una funcion que reciba edad e imprima si es mayor de edad o no en una linea
    println(esMayorEdad(21))

    println("Tu edad?")
    var edad = readLine()

    println("TU nombre?")
    var nombre = readLine()

    println("Tu edad es $edad y tu nombres es $nombre")

}

fun esMayorEdad(edad: Int) = if (edad > 18) "Es Mayor de edad" else "Es menor de edad"

fun concatenar(primero: String, segundo: String) = primero + segundo

fun multiplicar(x: Int, y: Int):Int = x * y

fun calcularEdad(edad: Int): Int {
    return 2018 - edad
}

fun mostrarMiNombre() {
    println("Jorge Casariego")
}