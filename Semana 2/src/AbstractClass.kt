abstract class Operacion(val valor1: Double, val valor2: Double) {
    protected var resultado: Int = 0

    abstract fun operar()

    fun imprimir() {
        println("El resultado de la operacion es $resultado")
    }
}

class Suma(valor1: Double, valor2: Double): Operacion(valor1, valor2) {
    override fun operar() {
        resultado = (valor1 + valor2).toInt()
    }

}

class Resta(valor1: Double, valor2: Double):Operacion(valor1, valor2) {
    override fun operar() {
        resultado = (valor1 - valor2).toInt()
    }

}

fun main(args: Array<String>) {

    val suma = Suma(4.0, 5.0)
    suma.operar()
    suma.imprimir()

    val resta = Resta(10.toDouble(), 5.toDouble())
    resta.operar()
    resta.imprimir()
}