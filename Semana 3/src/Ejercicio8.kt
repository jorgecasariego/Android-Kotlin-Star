/**
    Crear una clase Empleado con 2 parámetros:
    Numero de empleado del tipo Int
    Datos personales del tipo DatosPersonales
    Datos Personales tiene dos parámetros:
    nombre del tipo String
    teléfono del tipo Int (El Empleado podría no tener teléfono)
    Crear dos empleados: uno con teléfono y otro sin teléfono e imprimir luego si el empleado tiene o no teléfono
 */

data class DatosPersonales(val nombre: String, val telefono: Int?)
data class Empleado(val numeroEmpleado: Int, val datosPersonales: DatosPersonales?)

fun main(args: Array<String>) {
    var empleado1:Empleado = Empleado(123, DatosPersonales("Jorge", null))
    var empleado2:Empleado = Empleado(543, DatosPersonales("Laura", 987665544))


    if (empleado1.datosPersonales?.telefono != null) {
        print("El telefono de ${empleado1.datosPersonales?.nombre} es ${empleado1.datosPersonales?.telefono}")
    } else {
        println("EL usuario ${empleado1.datosPersonales?.nombre} no tiene telefono")
    }

}