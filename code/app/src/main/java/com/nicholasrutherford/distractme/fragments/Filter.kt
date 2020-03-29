package com.nicholasrutherford.distractme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.recyclers.FilterBy

class Filter: Fragment() {
    private var filterByAdapter: FilterBy? = null
    private var mView: View? = null
    private var rvFilter: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_filter, container, false)
        rvFilter = mView!!.findViewById(R.id.rvFilter)
        main()
        return mView
    }

    private fun main() {
        setUpArticleAdapter()
    }

    private fun setUpArticleAdapter() {
        rvFilter!!.itemAnimator = null
        rvFilter!!.layoutManager = LinearLayoutManager(context!!)
        filterByAdapter = FilterBy(context!!)
        rvFilter!!.adapter = filterByAdapter
    }
}