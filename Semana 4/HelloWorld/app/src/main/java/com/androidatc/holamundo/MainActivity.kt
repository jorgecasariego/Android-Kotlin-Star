package com.androidatc.holamundo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calcularView.setOnClickListener {
            calcularEdad(yearView.text.toString().toInt())
        }

    }


    fun calcularEdad(year: Int) {

        val resultado: Int = 2018 - year

        resultadoView.text = "Tu edad es $resultado"
    }


}
