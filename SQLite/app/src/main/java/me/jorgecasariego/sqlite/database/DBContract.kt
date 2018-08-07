package me.jorgecasariego.sqlite.database

object DBContract {

    // Inner class que define el contenido de la tabla de notas
    class NotasEntry {
        companion object {
            val NOMBRE_TABLA = "notas"
            val ID_NOTA = "id_nota"
            val DESCRIPCION_NOTA = "descripcion_nota"
            val FECHA_NOTA = "fecha_nota"
        }
    }
}