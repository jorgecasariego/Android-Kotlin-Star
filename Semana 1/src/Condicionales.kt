fun main(args: Array<String>) {
    val resultado = suma(5, 10)

    if (resultado > 10) {
        print("El numero es mayor a 10")
        if (resultado % 2 == 0) {
            print(" y es par")
        } else {
            print(" y es impar")
        }
    } else {
        println("El resultado es menor a 10")
    }

    println()
    convertirNumeroEnLetra(1)

    // Ejercio 1: get trimestre
    getTrimestre(15)

    println()
    // Ejercio 2: get semestre
    getSemestre(15)

    checkTipoParametro("String")
    checkTipoParametro(10)

}

fun checkTipoParametro(any: Any) {
    when(any) {
        is Int -> println("La variable pasada es un Int")
        is String -> println("La variable pasada es un String")
        else -> {
            println("La variable pasada no es un tipo aceptado por la función")
        }
    }
}

fun getTrimestre(mes: Int) {
    when(mes) {
        1,2,3 -> print("Primer Trimestre")
        4,5,6 -> print("Segundo Trimestre")
        7,8,9 -> print("Tercer Trimestre")
        10,11,12 -> print("Cuarto Trimestre")
        else -> print("No corresponde a un mes valido")
    }
}

fun getSemestre(mes: Int) {
    when(mes) {
        in 1..6 -> println("Primer semestre")
        in 7..12 -> println("Segundo Semestre")
    }
}


fun convertirNumeroEnLetra(numero: Int) {
    when(numero) {
        1 -> println("Uno")
        2 -> println("Dos")
        3 -> println("Tres")
        4 -> println("Cuatro")
        5 -> println("Cinco")
        6 -> println("Seis")
        7 -> println("Siete")
        8 -> println("Ocho")
        9 -> println("Nueve")
        else -> {
            println("Es otro numero")
        }
    }
}

fun suma(numero1: Int, numero2: Int): Int {
    return numero1 + numero2
}