fun main(args: Array<String>) {
    println("Hola Mundo")

    val hola = "Hola"
    val mundo = "Mundo"

    //println("Jorge, " + hola + " " + mundo)
    println("Jorge, $hola $mundo")


    var nombre: String = "Jorge Casariego"
    val edad: Int = 30
    var pi: Float = 3.14f
    var numero: Double = 54.123123

    println(nombre)
    println(edad)

    val valor1 = 1980
    val valor2 = 2018
    val resta:String = "La resta de los dos valores es ${valor2 - valor1}"

    println(resta)

}