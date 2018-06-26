package com.androidatc.fragmentsestaticos


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class SegundoFragment : Fragment() {

    companion object {
        fun newInstance(): SegundoFragment {
            return SegundoFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_segundo, container, false)
    }


}
