
    fun imprimirPares(cadenaNumeros: IntArray, fn: (Int) -> Boolean) {
        //Por cada elemento de la cadena de numeros vamos a llamar a fn()
        for (elemento in cadenaNumeros) {
            if(fn(elemento)) {
                print(" $elemento")
            }
        }
    }

    fun main(args: Array<String>) {
        val cadenaNumeros = IntArray(10)

        for(i in cadenaNumeros.indices) {
            cadenaNumeros[i] = (Math.random() * 100).toInt()
        }

        cadenaNumeros.forEach { numero ->
            print(" $numero")
        }

        println()

        imprimirPares(cadenaNumeros){ x ->
            x % 3 == 0
        }
    }