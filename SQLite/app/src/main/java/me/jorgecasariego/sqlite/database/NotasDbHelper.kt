package me.jorgecasariego.sqlite.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import me.jorgecasariego.sqlite.Nota

class NotasDbHelper(val context: Context):
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        Toast.makeText(context, "Creando la nueva base de datos", Toast.LENGTH_SHORT).show()
        db?.execSQL(SQL_CREATE_NOTAS_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Toast.makeText(context, "Actualizando base de datos", Toast.LENGTH_SHORT).show()
        db?.execSQL(SQL_DROP_TABLE)
        onCreate(db)
    }

    fun insertarNota(textoIngresado: String, fechaActual: String): Boolean {
        val db = writableDatabase
        val contenidoNota = ContentValues()
        contenidoNota.put(DBContract.NotasEntry.DESCRIPCION_NOTA, textoIngresado)
        contenidoNota.put(DBContract.NotasEntry.FECHA_NOTA, fechaActual)
        val idNota = db.insert(DBContract.NotasEntry.NOMBRE_TABLA, null, contenidoNota)

        return idNota > -1
    }

    fun mostrarNotas(): ArrayList<Nota> {
        val notas = ArrayList<Nota>()
        val db = writableDatabase
        val query = "SELECT * FROM ${DBContract.NotasEntry.NOMBRE_TABLA}"

        var cursor: Cursor?

        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLException) {
            db.execSQL(SQL_CREATE_NOTAS_ENTRIES)
            return ArrayList<Nota>()
        }

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val fecha = cursor.getString(cursor.getColumnIndex(DBContract.NotasEntry.FECHA_NOTA))
                val descripcion = cursor.getString(cursor.getColumnIndex(DBContract.NotasEntry.DESCRIPCION_NOTA))

                val nota = Nota(descripcion, fecha)
                notas.add(nota)
                cursor.moveToNext()
            }
        }
        return notas

    }

    fun borrarNotas(): Int {
        val db = writableDatabase

        return db.delete(DBContract.NotasEntry.NOMBRE_TABLA, null, null)
    }


    // Paso 1
    companion object {
        // Version
        val DATABASE_VERSION = 2
        //Nombre de la base de datos
        val DATABASE_NAME = "notas.db"

        // CREATE TABLE notas (id_notas INTEGER PRIMARY KEY AUTOINCREMENT, descripcion_nota TEXT)
        private val SQL_CREATE_NOTAS_ENTRIES =
                "CREATE TABLE " + DBContract.NotasEntry.NOMBRE_TABLA + " ( " +
                        DBContract.NotasEntry.ID_NOTA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        DBContract.NotasEntry.DESCRIPCION_NOTA + " TEXT," +
                        DBContract.NotasEntry.FECHA_NOTA + " TEXT)"

        private val SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + DBContract.NotasEntry.NOMBRE_TABLA
    }
}