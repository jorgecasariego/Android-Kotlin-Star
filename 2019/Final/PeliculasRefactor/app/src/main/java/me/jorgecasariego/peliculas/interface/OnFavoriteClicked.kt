package me.jorgecasariego.peliculas.`interface`

import me.jorgecasariego.peliculas.models.Model

interface OnFavoriteClicked {
    fun agregarSacarPeliculaFavorita(pelicula: Model.Movie)
    fun eliminarPelicula(pelicula: Model.Movie)
}