class Car(val brand: String, var model: String = "", var year: Int, var isDriving: Boolean = false) {

    fun isRecent() = this.year >= 2015
}

fun main(args: Array<String>) {
    var car = Car("Toyota", "Corolla", 2018)

    println("El auto es ${car.brand}, modelo: ${car.model} y año ${car.year}")
    println("Es un auto nuevo? ${car.isRecent()}")
    println("Esta manejando? ${car.isDriving}")

    var otroAuto = Car("Ford", "Fiesta", 1999, true)
    println("El auto es ${otroAuto.brand}, modelo: ${otroAuto.model} y año ${otroAuto.year}")
    println("Es un auto nuevo? ${otroAuto.isRecent()}")
    println("Esta manejando? ${otroAuto.isDriving}")

    var tercerAuto = Car("Nissan", year=1999)
    tercerAuto.model = "Patrol"
}