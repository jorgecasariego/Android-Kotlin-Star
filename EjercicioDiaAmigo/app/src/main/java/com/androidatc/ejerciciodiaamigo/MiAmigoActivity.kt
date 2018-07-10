package com.androidatc.ejerciciodiaamigo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_mi_amigo.*

class MiAmigoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_amigo)

        val extras = intent.extras ?: return
        val nombre: String = extras.getString("NOMBRE")

        nombreView.text = "Bienvenido $nombre"

        elegirAmigo.setOnClickListener { seleccionarAmigoInvisible() }
        llamarView.setOnClickListener { llamarAmigo() }
        comprarView.setOnClickListener { comprarRegalo() }
        mapaView.setOnClickListener { mostrarShoppings() }
        emailView.setOnClickListener {
            correo()
        }
    }

    private fun correo() {
        val direccionesCorreo = arrayOf("arya@gmail.com", "cersei@hotmail.com", "sansa@altavista.com")

        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Dia de la Amistad")
        intent.putExtra(Intent.EXTRA_TEXT, "Quiero comentarles que sus correos han sido hackeados!")
        intent.putExtra(Intent.EXTRA_EMAIL, direccionesCorreo)

        // Es mejor siempre asegurar que tenemos isntalado alguna aplicación que pueda resolver el
        // intent antes de iniciar la actividad
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun mostrarShoppings() {
        //val i = Intent(Intent.ACTION_VIEW, Uri.parse("geo:-25.284176, -57.564741"));
        //Opcion 2: Muestra un cuadro de busqueda
        val i = Intent(Intent.ACTION_VIEW, Uri.parse("geo:-25.284946, -57.564513?q=Shopping"));

        startActivity(i)
    }

    private fun comprarRegalo() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com"))
        startActivity(intent)
    }

    private fun llamarAmigo() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel: 981332255"))
        startActivity(intent)
    }

    private fun seleccionarAmigoInvisible() {
        val amigos = arrayOf("Andrea", "Silvia", "Carolina", "Jose", "Pepe")
        val numeroAmigoRandom = (Math.random() * 4).toInt()

        nombreAmigoView.text = "Mi amigo invisible es ${amigos.get(numeroAmigoRandom)}"
        elegirAmigo.setImageResource(R.drawable.amigo)

        contenedorBotones.visibility = View.VISIBLE
    }
}
