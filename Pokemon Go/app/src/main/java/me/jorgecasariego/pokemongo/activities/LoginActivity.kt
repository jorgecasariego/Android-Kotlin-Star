package me.jorgecasariego.pokemongo.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import me.jorgecasariego.pokemongo.R

class LoginActivity : AppCompatActivity() {

    val misPreferencias = "MisPreferencias"
    val NOMBRE = "nombre"
    val PASSWORD = "password"
    val EMPTY = ""

    // 3. Obtenemos la instancia de sharedPreferences
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences(misPreferencias, Context.MODE_PRIVATE)

        // 1. Manejar los clicks
        registrarse.setOnClickListener { registrarUsuario() }
        ingresar.setOnClickListener { iniciarSesion() }
    }

    // 6. Iniciamos session y comprobamos si es un usuario registrado
    private fun iniciarSesion() {
        val nombreUsuario = nombre.text.toString()
        val passwordUsuario = password.text.toString()

        if(nombreUsuario.isEmpty() || passwordUsuario.isEmpty()) {
            Toast.makeText(this, "El usuario o la contraseña no pueden quedar vacios", Toast.LENGTH_SHORT).show()
        } else {
            if (sharedPreferences?.getString(NOMBRE, EMPTY) != EMPTY &&
                    sharedPreferences?.getString(PASSWORD, EMPTY) != EMPTY) {
                gotoMainActivity()
            } else {
                Toast.makeText(this, "Usuario y/o contraseña no valida", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 2. Registrar usuario guardadno nombre y contraseña
    private fun registrarUsuario() {
        val nombreUsuario = nombre.text.toString()
        val passwordUsuario = password.text.toString()

        if(nombreUsuario.isEmpty() || passwordUsuario.isEmpty()) {
            Toast.makeText(this, "El usuario o la contraseña no pueden quedar vacios", Toast.LENGTH_SHORT).show()
        } else {
            // 4. Guardamos los datos del usuario en SharedPreferences
            val editor = sharedPreferences?.edit()
            editor?.putString(NOMBRE, nombreUsuario)
            editor?.putString(PASSWORD, passwordUsuario)
            editor?.apply()

            gotoMainActivity()
        }
    }

    private fun gotoMainActivity() {
        //5. Vamos al MainActivity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
