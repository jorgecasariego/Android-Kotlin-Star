package android.atc.mispaginasinternet

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_resultado.*

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        supportActionBar?.title = "Resultado"

        val mensaje:String = intent.getStringExtra("Mensaje")

        Snackbar.make(resultado, "$mensaje", Snackbar.LENGTH_INDEFINITE).show()
    }
}
