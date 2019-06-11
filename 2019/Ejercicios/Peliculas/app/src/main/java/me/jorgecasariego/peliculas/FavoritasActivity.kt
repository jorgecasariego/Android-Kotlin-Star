package me.jorgecasariego.peliculas

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_favoritas.*
import me.jorgecasariego.peliculas.database.PeliculasDatabase
import me.jorgecasariego.peliculas.models.Model

class FavoritasActivity : AppCompatActivity(), OnFavoriteClicked {
    override fun eliminarPelicula(pelicula: Model.Movie) {
        val resultado = peliculasDatabase.eliminarPelicula(pelicula)
        Toast.makeText(this, "Eliminando pelicula ${pelicula.title}. ($resultado)", Toast.LENGTH_LONG).show()

        getPeliculasFavoritas()
    }

    override fun agregarSacarPeliculaFavorita(pelicula: Model.Movie) {
        val resultado = peliculasDatabase.insertarPeliculaFavorita(pelicula)
        Toast.makeText(this, "Pelicula favorita guardada ($resultado)", Toast.LENGTH_LONG).show()
    }

    lateinit var peliculasDatabase: PeliculasDatabase
    var peliculasFiltradas: List<Model.Movie> = arrayListOf()
    var peliculas: List<Model.Movie> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritas)

        peliculasDatabase = PeliculasDatabase(this)
        getPeliculasFavoritas()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_favoritos, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == R.id.favoritos) {
            peliculasFiltradas = peliculas.filter {
                it.isFavorite == true
            }

        } else if(item?.itemId == R.id.todos) {
            peliculasFiltradas = peliculas
        }

        mostrarPeliculas()

        return super.onOptionsItemSelected(item)
    }

    private fun getPeliculasFavoritas() {
        peliculasFiltradas = peliculasDatabase.mostrarPeliculasFavoritas()
        peliculas = peliculasFiltradas

        mostrarPeliculas()
    }

    private fun mostrarPeliculas() {
        val adapter = GroupAdapter<ViewHolder>()
        peliculasFiltradas.forEach {
            adapter.add(MoviewItem(it, this, this@FavoritasActivity))
        }

        lista_peliculas_favoritas.adapter = adapter
    }
}
