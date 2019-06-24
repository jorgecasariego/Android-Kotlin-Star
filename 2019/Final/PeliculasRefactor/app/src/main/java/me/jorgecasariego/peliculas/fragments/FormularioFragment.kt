package me.jorgecasariego.peliculas.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_formulario.*
import me.jorgecasariego.peliculas.R
import me.jorgecasariego.peliculas.fragments.dialog.FullscreenDialogFragment
import me.jorgecasariego.peliculas.fragments.dialog.OnOpcionClicked
import me.jorgecasariego.peliculas.fragments.dialog.Opcion


class FormularioFragment : Fragment(), OnOpcionClicked {
    companion object {
        fun newInstance() = FormularioFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_formulario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mostrar_button.setOnClickListener {
            mostrarDialog()
        }
    }

    private fun mostrarDialog() {
        val bundle = Bundle()
        bundle.putString(FullscreenDialogFragment.KEY_1, "Hola Mundo");
        bundle.putInt(FullscreenDialogFragment.KEY_2, 100)

        val dialog = FullscreenDialogFragment.newInstance(this)
        dialog.arguments = bundle

        val ft = fragmentManager?.beginTransaction()
        dialog.show(ft, FullscreenDialogFragment.TAG.toString())
    }

    override fun opcionClickeada(opcion: Opcion) {
        //Toast.makeText(context, "Opcion seleccionada: ${opcion.nombre}", Toast.LENGTH_LONG).show()
        resultado_et.setText(opcion.nombre)
    }



}
