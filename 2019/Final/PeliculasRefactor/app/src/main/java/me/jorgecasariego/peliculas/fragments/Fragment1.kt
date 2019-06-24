package me.jorgecasariego.peliculas.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_fragment1.*
import me.jorgecasariego.peliculas.R
import me.jorgecasariego.peliculas.`interface`.OnFavoriteClicked
import me.jorgecasariego.peliculas.database.PeliculasDatabase
import me.jorgecasariego.peliculas.models.Model
import me.jorgecasariego.peliculas.models.MoviewItem
import me.jorgecasariego.peliculas.network.ImdbApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Fragment1 : Fragment(), OnFavoriteClicked {

    companion object {
        val PAGE = 1
        val LANGUAGE = "en-US"
        // Obtener uno registrandose a https://www.themoviedb.org/
        val API_KEY = me.jorgecasariego.peliculas.utils.API_KEY.api
        val CATEGORY = "popular"

        fun newInstance() = Fragment1()
    }

    var page = 0
    lateinit var peliculasDatabase: PeliculasDatabase
    // 1. Usando lazy lo que hacemos es inicializar la variable imdbApi recien una vez
    //    que se empiece a usar (en obtenerPeliculas() )
    private val imdbApi by lazy {
        ImdbApi.create()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fragment1, container, false)

        peliculasDatabase = PeliculasDatabase(requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. LLamamos al API para obtener las peliculas
        obtenerPeliculas()

    }

    private fun obtenerPeliculas() {
        progressBar.visibility = View.VISIBLE
        val call = imdbApi.getMovies(CATEGORY, API_KEY, LANGUAGE, PAGE)

        call.enqueue(object: Callback<Model.MovieResults> {
            override fun onFailure(call: Call<Model.MovieResults>?, t: Throwable?) {
                Toast.makeText(activity, "Fallo al obtener Peliculas", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Model.MovieResults>?, response: Response<Model.MovieResults>?) {
                if(response != null) {
                    val peliculas:Model.MovieResults? = response.body()

                    Log.d("TEST", "Cantidad de Peliculas: ${peliculas?.total_results}")

                    val adapter = GroupAdapter<ViewHolder>()

                    peliculas?.results?.forEach {
                        adapter.add(MoviewItem(it, requireContext(), this@Fragment1))
                    }

                    listado_items.adapter = adapter
                }

                progressBar.visibility = View.GONE
            }

        })
    }

    // Metodos utilizados cuando hacemos click sobre una pelicula
    override fun eliminarPelicula(pelicula: Model.Movie) {
        Toast.makeText(requireActivity(), "Eliminar pelicula ${pelicula.title} del listado", Toast.LENGTH_LONG).show()
    }

    override fun agregarSacarPeliculaFavorita(pelicula: Model.Movie) {
        val resultado = peliculasDatabase.insertarPeliculaFavorita(pelicula)

        //Toast.makeText(this, "Pelicula favorita guardada ($resultado)", Toast.LENGTH_LONG).show()
        Snackbar.make(listado_items, "Pelicula favorita guardada ($resultado)", Snackbar.LENGTH_INDEFINITE).setAction("Cantidad") {
            val peliculasGuardadas = peliculasDatabase.mostrarPeliculasFavoritas()
            Toast.makeText(requireContext(), "Cantidad de peliculas favoritas: " + peliculasGuardadas.size, Toast.LENGTH_LONG).show()
        }.show()
    }

}
