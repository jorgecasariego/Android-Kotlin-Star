package me.jorgecasariego.materialdesign.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.jorgecasariego.materialdesign.R


class Fragment1 : Fragment() {

    companion object {
        fun newInstance() = Fragment1()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false)
    }


}
