
    fun main(args: Array<String>) {
        /*
        *   ¿Cómo haríamos si quisiéramos crear un lambda que multiplique el
        *   parametro pasado por 100 y que luego retorne el valor pero en String?
        * */

        val multiplicarPor100 = {
            numero: Int -> numero * 100

            numero.toString()
        }

        val enteroToString: String = multiplicarPor100(100)
        println("El valor es $enteroToString")
    }