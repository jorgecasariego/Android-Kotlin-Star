package com.androidatc.listview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.androidatc.listview.adapters.MiAdapter
import com.androidatc.listview.modelos.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // 1. Datos
    val comidas = arrayListOf<Item>(
            Item(1, "Chipa", "https://s3-sa-east-1.amazonaws.com/assets.abc.com.py/2015/12/08/ana-delgado-y-elvira-gimenez-ofrecieron-sin-parar-chipa-barrero-calentito-en-las-cercanias-de-la-basilica-_769_573_1311317.jpg"),
            Item(2, "Sopa Paraguaya", "http://www.marandudigital.com.py/imagenes/SopaParaguaya.JPG"),
            Item(3, "Asado", "https://ugc.kn3.net/i/origin/http://video1.clubenet.net/rotas/imagens/large/img-4987-6386.jpg")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainLista.layoutManager = LinearLayoutManager(this)
        mainLista.adapter = MiAdapter(comidas, {item ->
            Toast.makeText(this, "Click en ${item.titulo}", Toast.LENGTH_SHORT).show()
        })

    }
}
