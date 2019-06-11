package me.jorgecasariego.peliculas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.moview_item.view.*
import me.jorgecasariego.peliculas.database.PeliculasDatabase
import me.jorgecasariego.peliculas.fragments.Fragment1
import me.jorgecasariego.peliculas.fragments.Fragment2
import me.jorgecasariego.peliculas.fragments.Fragment3
import me.jorgecasariego.peliculas.models.Model
import me.jorgecasariego.peliculas.network.ImdbApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnFavoriteClicked, NavigationView.OnNavigationItemSelectedListener {

    override fun eliminarPelicula(pelicula: Model.Movie) {
        Toast.makeText(this, "Eliminar pelicula ${pelicula.title} del listado", Toast.LENGTH_LONG).show()
    }

    override fun agregarSacarPeliculaFavorita(pelicula: Model.Movie) {
        val resultado = peliculasDatabase.insertarPeliculaFavorita(pelicula)

        //Toast.makeText(this, "Pelicula favorita guardada ($resultado)", Toast.LENGTH_LONG).show()
        Snackbar.make(contenedor, "Pelicula favorita guardada ($resultado)", Snackbar.LENGTH_INDEFINITE).setAction("Cantidad") {
            val peliculasGuardadas = peliculasDatabase.mostrarPeliculasFavoritas()
            Toast.makeText(this, "Cantidad de peliculas favoritas: " + peliculasGuardadas.size, Toast.LENGTH_LONG).show()
        }.show()
    }

    companion object {
        val PAGE = 1
        val LANGUAGE = "en-US"
        // Obtener uno registrandose a https://www.themoviedb.org/
        val API_KEY = me.jorgecasariego.peliculas.utils.API_KEY.api
        val CATEGORY = "popular"
    }

    var page = 0

    lateinit var peliculasDatabase: PeliculasDatabase

    // 1. Usando lazy lo que hacemos es inicializar la variable imdbApi recien una vez
    //    que se empiece a usar (en obtenerPeliculas() )
    private val imdbApi by lazy {
        ImdbApi.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2. LLamamos al API para obtener las peliculas
        obtenerPeliculas()

        peliculasDatabase = PeliculasDatabase(this)

        // 2019-1
        nav_view.setNavigationItemSelectedListener(this)

        // 2019-2
        setSupportActionBar(my_toolbar as Toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_nav_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_seccion_1 -> mostrarFragment(Fragment1.newInstance())
            R.id.menu_seccion_2 -> mostrarFragment(Fragment2.newInstance())
            R.id.menu_seccion_3 -> mostrarFragment(Fragment3.newInstance())
            else -> {
                return true
            }
        }

        item.isChecked = true
        drawer.closeDrawers()

        return true
    }


    private fun mostrarFragment(fragment: Fragment) {

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.contenedor, fragment)
                .commit()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.ver_favoritos -> {
                val intent = Intent(this, FavoritasActivity::class.java)
                startActivity(intent)
            }
            android.R.id.home -> drawer.openDrawer(GravityCompat.START)
        }


        return true
    }

    private fun obtenerPeliculas() {
        progressBar.visibility = View.VISIBLE
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
                        adapter.add(MoviewItem(it, this@MainActivity, this@MainActivity))
                    }

                    lista_peliculas.adapter = adapter
                }

                progressBar.visibility = View.GONE
            }

        })
    }
}

interface OnFavoriteClicked {
    fun agregarSacarPeliculaFavorita(pelicula: Model.Movie)
    fun eliminarPelicula(pelicula: Model.Movie)
}

class MoviewItem(val pelicula: Model.Movie,
                 val context: Context,
                 val listener: OnFavoriteClicked): Item<ViewHolder>() {
    companion object {
        val imageUrlBase = "https://image.tmdb.org/t/p/w500/"
    }
    override fun getLayout(): Int {
        return R.layout.moview_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.nombre_pelicula.text = pelicula.original_title
        viewHolder.itemView.sinopsis_pelicula.text = pelicula.overview

        if(pelicula.isFavorite) {
            viewHolder.itemView.favorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_red))
        } else {
            viewHolder.itemView.favorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_white))
        }


        viewHolder.itemView.favorite.setOnClickListener {
            if(pelicula.isFavorite) {
                viewHolder.itemView.favorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_white))
            } else {
                viewHolder.itemView.favorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_red))
            }

            pelicula.isFavorite = !pelicula.isFavorite

            listener.agregarSacarPeliculaFavorita(pelicula)
        }

        viewHolder.itemView.setOnLongClickListener {
            listener.eliminarPelicula(pelicula)
            true
        }

        Picasso.get().load(imageUrlBase + pelicula.poster_path).into(viewHolder.itemView.imagen_pelicula)
    }

}
