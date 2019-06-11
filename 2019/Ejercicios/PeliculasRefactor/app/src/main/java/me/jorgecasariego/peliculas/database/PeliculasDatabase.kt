package me.jorgecasariego.peliculas.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import me.jorgecasariego.peliculas.models.Model

class PeliculasDatabase(val context: Context):
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)  {

    override fun onCreate(db: SQLiteDatabase?) {
        Toast.makeText(context, "Creando la nueva base de datos", Toast.LENGTH_SHORT).show()
        db?.execSQL(SQL_CREATE_PELICULAS_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Toast.makeText(context, "Actualizando base de datos", Toast.LENGTH_SHORT).show()
        db?.execSQL(SQL_DROP_TABLE)
        onCreate(db)
    }

    fun insertarPeliculaFavorita(pelicula: Model.Movie): Boolean {
        val db = writableDatabase
        val contenidoPelicula = ContentValues()

        contenidoPelicula.put(DBContract.PeliculasEntry.ID_PELICULA, pelicula.id)
        contenidoPelicula.put(DBContract.PeliculasEntry.POSTER_PATH, pelicula.poster_path)
        contenidoPelicula.put(DBContract.PeliculasEntry.TITULO_PELICULA, pelicula.title)
        contenidoPelicula.put(DBContract.PeliculasEntry.DESCRIPCION_PELICULA, pelicula.overview)
        contenidoPelicula.put(DBContract.PeliculasEntry.RELEASE_DATE, pelicula.release_date)
        contenidoPelicula.put(DBContract.PeliculasEntry.ES_FAVORITA, pelicula.isFavorite)

        //val idNota = db.insert(DBContract.PeliculasEntry.NOMBRE_TABLA, null, contenidoPelicula)
        val idNota = db.insertWithOnConflict(
                DBContract.PeliculasEntry.NOMBRE_TABLA,
                null,
                contenidoPelicula, SQLiteDatabase.CONFLICT_IGNORE)

        if(idNota.toInt() == -1) {
            //Toast.makeText(context, "Actualizando favorito", Toast.LENGTH_LONG).show()
            db.update(DBContract.PeliculasEntry.NOMBRE_TABLA,
                    contenidoPelicula,
                    "${DBContract.PeliculasEntry.ID_PELICULA}=?",
                    arrayOf(pelicula.id.toString()))
        }

        return idNota > -1
    }

    fun mostrarPeliculasFavoritas(): ArrayList<Model.Movie> {
        val peliculas = ArrayList<Model.Movie>()
        val db = writableDatabase
        val query = "SELECT * FROM ${DBContract.PeliculasEntry.NOMBRE_TABLA}"

        var cursor: Cursor?

        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLException) {
            db.execSQL(SQL_CREATE_PELICULAS_ENTRIES)
            return ArrayList<Model.Movie>()
        }

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val idPelicula = cursor.getInt(cursor.getColumnIndex(DBContract.PeliculasEntry.ID_PELICULA))
                val posterPath = cursor.getString(cursor.getColumnIndex(DBContract.PeliculasEntry.POSTER_PATH))
                val titulo = cursor.getString(cursor.getColumnIndex(DBContract.PeliculasEntry.TITULO_PELICULA))
                val descripcion = cursor.getString(cursor.getColumnIndex(DBContract.PeliculasEntry.DESCRIPCION_PELICULA))
                val release = cursor.getString(cursor.getColumnIndex(DBContract.PeliculasEntry.RELEASE_DATE))
                // 1 = true - 0 = false
                val favorita = cursor.getInt(cursor.getColumnIndex(DBContract.PeliculasEntry.ES_FAVORITA)) == 1

                val pelicula = Model.Movie(0, idPelicula,titulo,0.0,posterPath,
                        "en",titulo,descripcion,release,favorita)
                peliculas.add(pelicula)
                cursor.moveToNext()
            }
        }
        return peliculas
    }

    fun eliminarPelicula(pelicula: Model.Movie): Int {
        val db = writableDatabase
        return db.delete(DBContract.PeliculasEntry.NOMBRE_TABLA,
                "${DBContract.PeliculasEntry.ID_PELICULA}=?",
                arrayOf(pelicula.id.toString()))
    }


    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "PELICULAS.db"

        // CREATE TABLE notas (id_notas INTEGER PRIMARY KEY AUTOINCREMENT, descripcion_nota TEXT)
        private val SQL_CREATE_PELICULAS_ENTRIES =
                "CREATE TABLE " + DBContract.PeliculasEntry.NOMBRE_TABLA + " ( " +
                        DBContract.PeliculasEntry.ID_PELICULA + " INTEGER PRIMARY KEY, " +
                        DBContract.PeliculasEntry.POSTER_PATH + " TEXT," +
                        DBContract.PeliculasEntry.TITULO_PELICULA + " TEXT," +
                        DBContract.PeliculasEntry.DESCRIPCION_PELICULA + " TEXT," +
                        DBContract.PeliculasEntry.RELEASE_DATE + " TEXT," +
                        DBContract.PeliculasEntry.ES_FAVORITA + " INTEGER )"

        private val SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + DBContract.PeliculasEntry.NOMBRE_TABLA

    }
}