
    fun extenderString(cadena: String, numero: Int): String {

        // En este ejemplo:
        // this --> cadena
        // it   --> numero
        val another: String.(Int) -> String = {this + it}

        return cadena.another(numero)
    }

    fun main(args: Array<String>) {
        println("Extender la siguiente cadena: ${extenderString("Rusia", 2018)}")
    }

    