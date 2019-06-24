package me.jorgecasariego.peliculas.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.dialog_listado.*
import kotlinx.android.synthetic.main.item_opcion.view.*
import me.jorgecasariego.peliculas.R

class FullscreenDialogFragment : DialogFragment() {

    lateinit var listener: OnOpcionClicked

    companion object {
        val TAG = 1000
        val KEY_1 = "KEY_1"
        val KEY_2 = "KEY_2"

        fun newInstance(listener: OnOpcionClicked): FullscreenDialogFragment {
            val fragment = FullscreenDialogFragment()
            fragment.listener = listener
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_listado, container, false)

        arguments?.let {
            val key1 = it.getString(KEY_1)
            val key2 = it.getInt(KEY_2)

            Toast.makeText(context, "Se recibe: $key1 y $key2", Toast.LENGTH_LONG).show()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cerrar_dialog_button.setOnClickListener { dismiss() }

        mostrarListadoOpciones()
    }

    private fun mostrarListadoOpciones() {
        val adapter = GroupAdapter<ViewHolder>()
        adapter.add(RecyclerItem(this, Opcion(1, "Opcion 1"), listener))
        adapter.add(RecyclerItem(this, Opcion(2, "Opcion 2"), listener))
        adapter.add(RecyclerItem(this, Opcion(3, "Opcion 3"), listener))
        adapter.add(RecyclerItem(this, Opcion(4, "Opcion 4"), listener))

        listado_items.adapter = adapter


    }

    // Una vez que el dialogo ha sido creado, ya podemos usar el metodo getDialog() y setear el ancho
    // y alto de la ventana al ancho y alto del dispositivo
    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT

            dialog.window.setLayout(width, height)
        }
    }
}

data class Opcion(val id: Int, val nombre: String)

interface OnOpcionClicked {
    fun opcionClickeada(opcion: Opcion)
}

class RecyclerItem(private val view: FullscreenDialogFragment, private val opcion: Opcion, private val listener: OnOpcionClicked) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.opcion_texto.text = opcion.nombre

        viewHolder.itemView.setOnClickListener {
            listener.opcionClickeada(opcion)
            view.dismiss()
        }
    }

    override fun getLayout(): Int = R.layout.item_opcion
}