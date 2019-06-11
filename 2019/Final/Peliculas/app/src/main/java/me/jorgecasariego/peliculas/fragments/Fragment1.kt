package me.jorgecasariego.peliculas.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.jorgecasariego.peliculas.R


class Fragment1 : Fragment() {

    companion object {
        fun newInstance() = Fragment1()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fragment1, container, false)
    }


}
