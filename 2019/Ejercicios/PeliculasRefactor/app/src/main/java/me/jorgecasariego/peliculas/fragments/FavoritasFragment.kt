package me.jorgecasariego.peliculas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import me.jorgecasariego.peliculas.R
import me.jorgecasariego.peliculas.`interface`.OnFavoriteClicked
import me.jorgecasariego.peliculas.database.PeliculasDatabase
import me.jorgecasariego.peliculas.models.Model
import me.jorgecasariego.peliculas.models.MoviewItem


class FavoritasFragment : Fragment(), OnFavoriteClicked {

    companion object {
        fun newInstance() = FavoritasFragment()
    }

    override fun eliminarPelicula(pelicula: Model.Movie) {
        val resultado = peliculasDatabase.eliminarPelicula(pelicula)
        Toast.makeText(activity, "Eliminando pelicula ${pelicula.title}. ($resultado)", Toast.LENGTH_LONG).show()

        getPeliculasFavoritas()
    }

    override fun agregarSacarPeliculaFavorita(pelicula: Model.Movie) {
        if (!pelicula.isFavorite) {
            val resultado = peliculasDatabase.eliminarPelicula(pelicula)
            Toast.makeText(activity, "Eliminando pelicula ${pelicula.title}. ($resultado)", Toast.LENGTH_LONG).show()
            getPeliculasFavoritas()
        }
    }

    lateinit var peliculasDatabase: PeliculasDatabase
    var peliculasFiltradas: List<Model.Movie> = arrayListOf()
    var peliculas: List<Model.Movie> = arrayListOf()
    lateinit var peliculasFavoritas: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favoritas, container, false)

        peliculasDatabase = PeliculasDatabase(requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        peliculasFavoritas = view.findViewById(R.id.lista_peliculas_favoritas)
        getPeliculasFavoritas()
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        setHasOptionsMenu(true)
//
//    }

//    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        menu?.clear()
//        inflater?.inflate(R.menu.menu_favoritos, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//
//    }

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//
//        if(item?.itemId == R.id.favoritos) {
//            peliculasFiltradas = peliculas.filter {
//                it.isFavorite == true
//            }
//
//        } else if(item?.itemId == R.id.todos) {
//            peliculasFiltradas = peliculas
//        }
//
//        mostrarPeliculas()
//
//        return super.onOptionsItemSelected(item)
//    }

    private fun getPeliculasFavoritas() {
        peliculasFiltradas = peliculasDatabase.mostrarPeliculasFavoritas()
        peliculas = peliculasFiltradas

        mostrarPeliculas()
    }

    private fun mostrarPeliculas() {
        val adapter = GroupAdapter<ViewHolder>()
        peliculasFiltradas.forEach {
            adapter.add(MoviewItem(it, requireContext(), this@FavoritasFragment))
        }

        peliculasFavoritas.adapter = adapter
    }
}
