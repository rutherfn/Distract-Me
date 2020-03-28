package com.nicholasrutherford.distractme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.Recyclers.News
import com.nicholasrutherford.distractme.network.RepositoryImp.NewsRepositoryImp

class Home : Fragment() {
    private val repository: NewsRepositoryImp = NewsRepositoryImp()
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
        showFirstTodo()
    }

    private fun setUpArticleAdapter() {
        rvHomes!!.itemAnimator = null
        rvHomes!!.layoutManager = LinearLayoutManager(context!!)
    }

    private fun showFirstTodo() {
        repository.getNewsTopHeadlines("us", "92d8c9e8d1a44be58676ee20051e3c77").observe(viewLifecycleOwner, Observer {
            val articleAdapter = News(context!!,it)
            rvHomes!!.adapter = articleAdapter
        })
    }
}