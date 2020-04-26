package com.nicholasrutherford.distractme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.recyclers.WebNews

class Web: Fragment() {
    private var webAdapter: WebNews? = null
    private var mView: View? = null
    private var rvWeb: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_web, container, false)
        rvWeb = mView!!.findViewById(R.id.rvWeb)
        main()
        return mView
    }

    private fun main() {
        setUpWebArticleAdapt()
        initAdapter()
    }

    private fun setUpWebArticleAdapt() {
        rvWeb!!.itemAnimator = null
        rvWeb!!.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initAdapter() {
        webAdapter = WebNews(requireContext(), "weburltestingthis")
        rvWeb!!.adapter = webAdapter
    }
}