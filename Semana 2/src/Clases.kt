class Car(val brand: String, val model: String = "", var year: Int, var isDriving: Boolean = false) {

    init {
        println("Instancia de Car creada")
        println("el modelo del auto creado es $model")
    }

    fun isRecent() = this.year >= 2015
}

fun main(args: Array<String>) {
    var toyoraCar = Car("Toyota", "Corola", 2018)

    println("Detalle corola")
    println("Brand: ${toyoraCar.brand} - modelo: ${toyoraCar.model} - year: ${toyoraCar.year} - isDriving: ${toyoraCar.isDriving}")

    println()

    var fiat = Car("fiat", year = 2001)
    println("Detalle Fiat")
    println("Brand: ${fiat.brand} - modelo: ${fiat.model} - year: ${fiat.year} - isDriving: ${fiat.isDriving}")

}