package com.androidatc.ejerciciosintent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val NOMBRE: String = "Nombre"
        val REQUEST_CODE: Int = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ingresarView.setOnClickListener {
            checkUsuario()
        }
    }

    private fun checkUsuario() {
        val nombre: String = nombreView.text.toString()
        val password: String = passwordView.text.toString()

        if (!nombre.isEmpty() && !password.isEmpty()) {
            val i = Intent(this, SecondActivity::class.java)
            i.putExtra(NOMBRE, nombre)

            startActivityForResult(i, REQUEST_CODE)
        } else {
            Toast.makeText(this, "El nombre y/o la contraseña no pueden quedar vacías",
                    Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val datos = data?.extras

                    if (datos != null) {
                        val mensaje = datos.getString(NOMBRE)
                        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
