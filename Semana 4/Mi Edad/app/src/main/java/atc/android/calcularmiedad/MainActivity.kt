package atc.android.calcularmiedad

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        calcularEdadView.setOnClickListener {
//            val yearOfBirth: Int = yearView.text.toString().toInt()
//            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
//            val miEdad = currentYear - yearOfBirth
//            ageView.text = "Tu edad es $miEdad"
//        }
    }

    fun calcularMiEdad(view: View) {
        val yearOfBirth: Int = yearView.text.toString().toInt()
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val miEdad = currentYear - yearOfBirth
        ageView.text = "Tu edad es $miEdad"
    }
}
