package com.nicholasrutherford.distractme.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.recyclers.News
import com.nicholasrutherford.distractme.network.repositoryImp.NewsRepositoryImp
import kotlinx.coroutines.Dispatchers

class Home : Fragment() {
    private val sharedPreference by lazy { context?.getSharedPreferences("NewsSharedPreferences", Context.MODE_PRIVATE) }
    private var articleAdapter: News? = null
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
        showTopHeadlines()
    }

    private fun setUpArticleAdapter() {
        rvHomes!!.itemAnimator = null
        rvHomes!!.layoutManager = LinearLayoutManager(context!!)
    }

    private val newsTopHeadlines = liveData(Dispatchers.IO) {
        val result = repository.getNewsTopHeadlinesByCountry("us", "92d8c9e8d1a44be58676ee20051e3c77")
        emit(result)
    }

    private fun showTopHeadlines() {
        if(articleAdapter == null) {
            newsTopHeadlines.observe(viewLifecycleOwner, Observer {
                articleAdapter = News(context!!, it)
                rvHomes!!.adapter = articleAdapter
            })
        } else {
                updateTopHeadlineCountry()
                rvHomes!!.adapter = articleAdapter
                println("Here is the articles")
        }
    }

    private fun updateTopHeadlineCountry() {
        val newsTopHeadlineByCountry = liveData(Dispatchers.IO) {
            val result = sharedPreference?.getString("countrySelected", "")?.let {
                repository.getNewsTopHeadlinesByCountry(
                    it, "92d8c9e8d1a44be58676ee20051e3c77")
            }
            emit(result)
        }
        newsTopHeadlineByCountry.observe(viewLifecycleOwner, Observer {
            it?.let { it1 -> articleAdapter?.update(it1) }
        })
    }

    interface RefreshInterface {
        fun refreshAdapterFragmentB() {}
    }

}