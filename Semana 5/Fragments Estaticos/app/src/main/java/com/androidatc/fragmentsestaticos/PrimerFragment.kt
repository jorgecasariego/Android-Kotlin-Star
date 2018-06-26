package com.androidatc.fragmentsestaticos


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PrimerFragment : Fragment() {

    companion object {
        fun newInstances(): PrimerFragment {
            return PrimerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_primer, container, false)
    }


}
