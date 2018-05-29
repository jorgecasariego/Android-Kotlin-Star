interface Aeronave {
    fun volar()

    fun definicion() {
        println("Una aeronave es cualquier objeto capaz e navegar por los aires")
    }
}

class Helicoptero: Aeronave {
    override fun volar() {
        println("Volando un helicoptero")
    }
}

class Avion: Aeronave {
    override fun volar() {
        println("Volando un Avion")
    }
}

fun main(args: Array<String>) {
    val helicoptero = Helicoptero()
    val avion = Avion()

    helicoptero.volar()
    helicoptero.volar()
}