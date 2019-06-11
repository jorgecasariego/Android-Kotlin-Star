package me.jorgecasariego.peliculas.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.jorgecasariego.peliculas.R

class Fragment3 : Fragment() {

    companion object {
        fun newInstance() = Fragment3()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment3, container, false)
    }


}
