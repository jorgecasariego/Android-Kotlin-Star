
    fun main(args: Array<String>) {
        val enteros = intArrayOf(1, 2, 3, 4, 5, 6)

        // De la manera larga
        enteros.forEach { item -> print(" ${item * 4}") }

        println()

        // De la manera corta
        enteros.forEach { print(" ${it * 4}") }
    }

