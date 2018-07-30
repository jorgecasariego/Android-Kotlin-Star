package me.jorgecasariego.pokemongo.models

object Model {
    data class Pokemon(
            val url: String,
            val name: String)

    data class PokemonInfo(
            val count: Int,
            val previous: String,
            val results: ArrayList<Pokemon>)
}


