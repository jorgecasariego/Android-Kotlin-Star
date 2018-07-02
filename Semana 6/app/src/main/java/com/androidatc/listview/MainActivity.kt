package com.androidatc.listview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // 1. Datos
    val elementos = arrayOf("ELemento 1", "elemento 2", "Eelemento 3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2. Creamos un ArrayAdapter y pasamos los datos de 1
        val adapter: ListAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elementos)

        // 3. Asociar nuestra lista con adapter creado en 2
        mainLista.adapter = adapter

        mainLista.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "Click en ${elementos[position]}", Toast.LENGTH_LONG).show()
        }
    }
}
