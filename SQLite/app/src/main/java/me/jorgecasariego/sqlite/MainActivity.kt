package me.jorgecasariego.sqlite

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import me.jorgecasariego.sqlite.database.NotasDbHelper
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var notasDbHelper: NotasDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notasDbHelper = NotasDbHelper(this)

        guardar_button.setOnClickListener { guardarNotas() }
        leer_button.setOnClickListener { leerNotas() }
        limpiar_button.setOnClickListener { limpiarNotas() }
    }

    private fun guardarNotas() {
        val textoIngresado = nota_ingresada.text.toString()

        val sdf = SimpleDateFormat("dd/mm/yyyy hh:mm:ss")
        val fechaActual = sdf.format(Date())

        val resultado = notasDbHelper.insertarNota(textoIngresado, fechaActual)

        if(resultado) {
            Toast.makeText(this, "Nota guardada exitosamente", Toast.LENGTH_SHORT).show()
            nota_ingresada.setText("")
        } else {
            Toast.makeText(this, "Error al guardar nota", Toast.LENGTH_SHORT).show()
        }
    }

    private fun leerNotas() {
        val notas = notasDbHelper.mostrarNotas()

        if(notas.size > 0) {
            val notasConcatenadas = StringBuilder()

            notas.forEach { notasConcatenadas.append(it.nota).append("\n").append(it.fecha).append("\n\n") }

            resultado_textview.text = notasConcatenadas.toString()
            resultado_textview.setTextColor(Color.BLACK)
            resultado_textview.gravity = Gravity.LEFT
        }  else {
            resultado_textview.text = "No hay notas guardadas"
            resultado_textview.setTextColor(Color.RED)
            resultado_textview.gravity = Gravity.CENTER
        }
    }

    private fun limpiarNotas() {


        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Notas")
        builder.setMessage("Estas seguro de borras todas las notas?")
        builder.setPositiveButton("Sí") {  dialog, which ->
            val cantidadNotas = notasDbHelper.borrarNotas()
            if(cantidadNotas != -1) {
                Toast.makeText(this, "Se han borrado $cantidadNotas notas con exito", Toast.LENGTH_SHORT).show()
                resultado_textview.text = ""
            } else {
                Toast.makeText(this, "Ha ocurrido un error al borrar las notas", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(this, "que bien!", Toast.LENGTH_SHORT).show()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()


    }
}
