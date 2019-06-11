package me.jorgecasariego.peliculas.models

import android.content.Context
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.moview_item.view.*
import me.jorgecasariego.peliculas.R
import me.jorgecasariego.peliculas.`interface`.OnFavoriteClicked

class MoviewItem(val pelicula: Model.Movie,
                 val context: Context,
                 val listener: OnFavoriteClicked) : Item<ViewHolder>() {
    companion object {
        val imageUrlBase = "https://image.tmdb.org/t/p/w500/"
    }

    override fun getLayout(): Int {
        return R.layout.moview_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.nombre_pelicula.text = pelicula.original_title
        viewHolder.itemView.sinopsis_pelicula.text = pelicula.overview

        if (pelicula.isFavorite) {
            viewHolder.itemView.favorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_red))
        } else {
            viewHolder.itemView.favorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_white))
        }


        viewHolder.itemView.favorite.setOnClickListener {
            if (pelicula.isFavorite) {
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