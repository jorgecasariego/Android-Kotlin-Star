package com.androidatc.ejerciciosintent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.androidatc.ejerciciosintent.R.id.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //1. Recibo los datos del Login y muestro en pantalla su nombre
        val extras = intent.extras ?: return
        val nombre = extras.getString(MainActivity.NOMBRE)

        usuario.text = "Hola $nombre"

        // 2. Manejamos el click de la imagen
        elegirAmigo.setOnClickListener { seleccionarAmigoInvisble() }

        // 3. Comprar
        comprar.setOnClickListener { comprarRegalo() }

        // 4. LLamar
        llamar.setOnClickListener { realizarLLamada() }

        // 5. email
        email.setOnClickListener { escribirCorreo() }

        // 6. Mostrar map
        mapa.setOnClickListener { mostrarLugares() }

        // 7. implementar el logout y enviamos datos de vuelta al MainActivity
        signout.setOnClickListener {
            val despedida = Intent()
            despedida.putExtra(MainActivity.NOMBRE, "Gracias por participar $nombre !!!")
            //Enviamos el dato a la actividad que nos llamo con resultado OK y retornamos el dato
            setResult(Activity.RESULT_OK, despedida)
            finish()
        }

    }

    private fun mostrarLugares() {
        //Opcion 1: El mapa se centra en la long y lat pasadas
        //val i = Intent(Intent.ACTION_VIEW, Uri.parse("geo:-25.284176, -57.564741"));

        //Opcion 2: Muestra un cuadro de busqueda
        //val i = Intent(Intent.ACTION_VIEW, Uri.parse("geo:-25.284946, -57.564513?q=Shopping"));

        //Opcion 3: Muestra un marcador en la direccion exacta
        val i = Intent(Intent.ACTION_VIEW, Uri.parse("geo:-25.284176, -57.564741?q=Palma y 15 de agosto"))

        if (i.resolveActivity(packageManager) != null) {
            startActivity(i)
        }


    }

    private fun escribirCorreo() {
        val direccionesCorreo = arrayOf("arya@gmail.com", "cersei@hotmail.com", "sansa@altavista.com")

        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Dia de la Amistad");
        intent.putExtra(Intent.EXTRA_TEXT, "Quiero comentarles que sus correos han sido hackeados!");
        intent.putExtra(Intent.EXTRA_EMAIL, direccionesCorreo);

        // Es mejor siempre asegurar que tenemos isntalado alguna aplicación que pueda resolver el
        // intent antes de iniciar la actividad
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun seleccionarAmigoInvisble() {
        val amigos = arrayOf("Daenerys Targaryen", "Missandei", "Jorah Mormont", "Tyrion Lannister")

        val numeroElegido = (Math.random() * 4).toInt()
        amigo.text = "Tu amigo invisible es ${amigos.get(numeroElegido)}"
        amigo.visibility = View.VISIBLE
        datosAmigoContainer.visibility = View.VISIBLE
        elegirAmigo.setImageResource(R.drawable.amigo)

    }

    private fun realizarLLamada() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel: 555-1234"))
        startActivity(intent)
    }

    private fun comprarRegalo() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com"))
        startActivity(intent)
    }
}
