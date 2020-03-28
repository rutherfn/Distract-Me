package com.nicholasrutherford.distractme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R

class Filter: Fragment() {
    private var mView: View? = null
    private var rvFilter: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_home, container, false)
        rvFilter = mView!!.findViewById(R.id.rvFilter)
        main()
        return mView
    }

    private fun main() {
        // base func for filter goes here
    }
}