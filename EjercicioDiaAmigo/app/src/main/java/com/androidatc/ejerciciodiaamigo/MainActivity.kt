package com.androidatc.ejerciciodiaamigo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ingresarView.setOnClickListener {
            checkUsuario()
        }
    }

    private fun checkUsuario() {
        val nombre: String = nombreUsuarioView.text.toString()
        val password: String = passwordView.text.toString()

        if(!nombre.isEmpty() && !password.isEmpty()) {
            val i = Intent(this, MiAmigoActivity::class.java)
            i.putExtra("NOMBRE", nombre)
            startActivity(i)
        } else {
            Toast.makeText(this, "Usuario y/o password vacío", Toast.LENGTH_SHORT)
                    .show()
        }
    }
}
