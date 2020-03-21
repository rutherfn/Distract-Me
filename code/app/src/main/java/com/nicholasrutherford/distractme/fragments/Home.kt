package com.nicholasrutherford.distractme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.Recyclers.Article
import kotlinx.android.synthetic.main.fragment_home.*

class Home : Fragment() {

    private var mView: View? = null
    private var articleAdapter: Article? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_home, container, false)
        main()
        return mView
    }

    private fun main() {
        setUpArticleAdapter()
    }

    private fun setUpArticleAdapter() {
        rvHome.itemAnimator = null
        rvHome.layoutManager = LinearLayoutManager(context)
        rvHome.adapter = articleAdapter
    }
}