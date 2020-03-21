package com.nicholasrutherford.distractme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.Recyclers.Article

class Home : Fragment() {
    private var mView: View? = null
    private var rvHomes: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_home, container, false)
        rvHomes = mView!!.findViewById(R.id.rvHome)
        main()
        return mView
    }

    private fun main() {
        setUpArticleAdapter()
    }

    private fun setUpArticleAdapter() {
        val articleAdapter = Article(context!!)
        rvHomes!!.itemAnimator = null
        rvHomes!!.layoutManager = LinearLayoutManager(context!!)
        rvHomes!!.adapter = articleAdapter
    }

    fun newInstance(title: String): Fragment {
        val fragment = Home()
        val args = Bundle()
        args.putString("title", title)
        fragment.arguments = args
        return fragment
    }
}