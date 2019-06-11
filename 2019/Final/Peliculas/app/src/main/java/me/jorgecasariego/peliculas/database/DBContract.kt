package me.jorgecasariego.peliculas.database

/**
 val vote_count: Int,
val id: Int,
val title: String,
val popularity: Double,
val poster_path: String,
val original_language: String,
val original_title: String,
val overview: String,
val release_date: String,
var isFavorite: Boolean
 */
object DBContract {

    // Inner class que define el contenido de la tabla de peliculas
    class PeliculasEntry {
        companion object {
            val NOMBRE_TABLA = "peliculas"
            val ID_PELICULA = "id_pelicula"
            val POSTER_PATH = "poster_path"
            val TITULO_PELICULA = "original_title"
            val DESCRIPCION_PELICULA = "overview"
            val RELEASE_DATE = "release_date"
            val ES_FAVORITA = "es_favorita"
        }
    }
}