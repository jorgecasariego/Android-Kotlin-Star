package me.jorgecasariego.peliculas

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.moview_item.view.*
import me.jorgecasariego.peliculas.models.Model
import me.jorgecasariego.peliculas.network.ImdbApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        val PAGE = 1
        val LANGUAGE = "en-US"
        // Obtener uno registrandose a https://www.themoviedb.org/
        val API_KEY = me.jorgecasariego.peliculas.utils.API_KEY.api
        val CATEGORY = "popular"
    }

    private val imdbApi by lazy {
        ImdbApi.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        obtenerPeliculas()
    }

    private fun obtenerPeliculas() {
        val call = imdbApi.getMovies(CATEGORY, API_KEY, LANGUAGE, PAGE)

        call.enqueue(object: Callback<Model.MovieResults>{
            override fun onFailure(call: Call<Model.MovieResults>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Fallo al obtener Peliculas", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Model.MovieResults>?, response: Response<Model.MovieResults>?) {
                if(response != null) {
                    val peliculas:Model.MovieResults? = response.body()

                    Log.d("TEST", "Cantidad de Peliculas: ${peliculas?.total_results}")

                    val adapter = GroupAdapter<ViewHolder>()

                    peliculas?.results?.forEach {
                        adapter.add(MoviewItem(it))
                    }

                    lista_peliculas.adapter = adapter
                }
            }

        })
    }
}

class MoviewItem(val pelicula: Model.Movie): Item<ViewHolder>() {
    companion object {
        val imageUrlBase = "https://image.tmdb.org/t/p/w500/"
    }
    override fun getLayout(): Int {
        return R.layout.moview_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.nombre_pelicula.text = pelicula.original_title
        viewHolder.itemView.sinopsis_pelicula.text = pelicula.overview

        Picasso.get().load(imageUrlBase + pelicula.poster_path).into(viewHolder.itemView.imagen_pelicula)
    }

}
