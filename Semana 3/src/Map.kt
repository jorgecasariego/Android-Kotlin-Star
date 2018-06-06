fun main(args: Array<String>) {
    val paises: Map<String, Int> = mapOf(
            Pair("Paraguay", 7000000),
            Pair("Brasil", 200000000),
            Pair("Argentina", 42000000)
    )

    println("Paises")
    println(paises)

    // Imprimir clave y valor del map
    for((key, value) in paises) {
        println("Clave: $key - Valor: $value")
    }

    // Tamaño de la colección
    println("El tamaño de la coleccion es ${paises.size}")

    // Cantidad de habitantes de Paraguay
    println("La cantidad de habitantes de Paraguay es ${paises["Paraguay"]} y de Argentina es ${paises["Argentina"]}")

    // Imprimir la cantidad de habitantes de los 3 paises
    var suma = 0
    paises.forEach { suma += it.value }

    println("la cantidad de habitantes es $suma")

    val rolandGarros: Map<String,Int> = mapOf(
            "Nadal" to 30,
            "Federer" to 37,
            "Sharapova" to 28
    )
}