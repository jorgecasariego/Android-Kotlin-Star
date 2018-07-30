package me.jorgecasariego.pokemongo.network

import me.jorgecasariego.pokemongo.models.Model
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokemonApi {

    @GET("pokemon/")
    fun getPokemones(): Call<Model.PokemonInfo>

    companion object {
        val BASE_URL = "https://pokeapi.co/api/v2/"

        fun create(): PokemonApi {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(PokemonApi::class.java)
        }
    }
}