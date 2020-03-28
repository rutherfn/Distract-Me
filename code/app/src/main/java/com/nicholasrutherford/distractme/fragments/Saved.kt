package com.nicholasrutherford.distractme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R

class Saved: Fragment() {

    private var mView: View? = null
    private var rvSaved: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_home, container, false)
        rvSaved = mView!!.findViewById(R.id.rvSaved)
        main()
        return mView
    }

    private fun main() {
        // based func for saved news goes here
    }
}