package me.jorgecasariego.peliculas.network

import me.jorgecasariego.peliculas.models.Model
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ImdbApi {

    @GET("/3/movie/{category}")
    fun getMovies(
          @Path("category") category: String,
          @Query("api_key") apiKey: String,
          @Query("language") language: String,
          @Query("page") page: Int
    ): Call<Model.MovieResults>

    companion object {
        val BASE_URL = "https://api.themoviedb.org"

        fun create(): ImdbApi {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(ImdbApi::class.java)
        }
    }
}