open class Persona(val nombre: String, val edad: Int) {
    open fun imprimirDatos() {
        println("Nombre: $nombre")
        println("Edad: $edad")
    }
}

class Empleado(nombre: String, edad: Int, var sueldo: Double): Persona(nombre, edad) {
    override fun imprimirDatos() {
        super.imprimirDatos()
        println("Sueldo: $sueldo")
    }

    fun pagaImpuestos() {
        if(sueldo > 100000000) {
            println("EL usuario $nombre debe pagar impuestos")
        } else {
            println("El usuario $nombre no debe pagar impuestos con su sueldo de $sueldo")
        }
    }
}

fun main(args: Array<String>) {
    val persona1 = Persona("Jorge", 35)

    persona1.imprimirDatos()

    val empleado = Empleado("Pedro", 24, 180000.0)
    empleado.imprimirDatos()
    empleado.pagaImpuestos()

}