package com.androidatc.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener{mostrarGrupo(PrimerFragment.newInstance())}
        button2.setOnClickListener{mostrarGrupo(SegundoFragment.newInstance())}

    }

    private fun mostrarGrupo(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.contenedor, fragment)
                .commit()

    }
}
