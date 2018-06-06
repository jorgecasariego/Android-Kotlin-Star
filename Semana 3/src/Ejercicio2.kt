fun main(args: Array<String>) {
    val concatenarNumero: String.(Int) -> String = {this + ' ' + it}

    println("Paraguay".concatenarNumero(2060))
}