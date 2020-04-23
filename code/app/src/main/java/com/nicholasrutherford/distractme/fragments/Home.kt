package com.nicholasrutherford.distractme.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.recyclers.News
import com.nicholasrutherford.distractme.network.repositoryImp.NewsRepositoryImp
import kotlinx.coroutines.Dispatchers

class Home : Fragment() {
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
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
        var editor = sharedPreference.edit()
        setUpArticleAdapter()
        showTopHeadlines(sharedPreference)
    }

    private fun setUpArticleAdapter() {
        rvHomes!!.itemAnimator = null
        rvHomes!!.layoutManager = LinearLayoutManager(requireContext())
    }

    private val newsTopHeadlines = liveData(Dispatchers.IO) {
        val result = repository.getNewsTopHeadlinesByCountry("us", "92d8c9e8d1a44be58676ee20051e3c77")
        emit(result)
    }

    private fun initAdapter(sharedPreferences: SharedPreferences) {
        newsTopHeadlines.observe(viewLifecycleOwner, Observer {
            articleAdapter = News(requireContext(), it)
            rvHomes!!.adapter = articleAdapter
        })
    }

    private fun showTopHeadlines(sharedPreference: SharedPreferences) {
        if (articleAdapter == null) {
            initAdapter(sharedPreference)
        } else {
            when {
                sharedPreference.getBoolean("countryFilterByTopHeadlines", false) -> { // working
                    updateTopHeadlineCountry(sharedPreference)
                    rvHomes!!.adapter = articleAdapter
                }
                sharedPreference.getBoolean("sourceFilterByTopHeadlines", false) -> { // working
                    updateTopHeadlineSources(sharedPreference)
                    rvHomes!!.adapter = articleAdapter
                }
                sharedPreference.getBoolean("countryAndCategoryFilterByTopHeadlines", false) -> { // wprking
                    updateTopHeadlinesCountryAndCategory(sharedPreference)
                    rvHomes!!.adapter = articleAdapter
                }
                sharedPreference.getBoolean("subjectFilterByTopHeadlines", false) -> {
                    updateTopHeadlineBySubject(sharedPreference)
                    rvHomes!!.adapter = articleAdapter
                }
                sharedPreference.getBoolean("everythingGrabAllNewsBy",false) -> {
                    updateEverythingAllBySubject(sharedPreference)
                    rvHomes!!.adapter = articleAdapter
                }
                else -> {
                    initAdapter(sharedPreference)
                    println(sharedPreference.getBoolean("countryFilterByTopHeadlines",false))
                }
            }
        }
    }

    private fun updateTopHeadlineCountry(sharedPreference: SharedPreferences) {
        val newsTopHeadlineByCountry = liveData(Dispatchers.IO) {
            val result = sharedPreference.getString("countrySelectedTopHeadlines", "")?.let {
                repository.getNewsTopHeadlinesByCountry(
                    it, "92d8c9e8d1a44be58676ee20051e3c77")
            }
            emit(result)
        }
        newsTopHeadlineByCountry.observe(viewLifecycleOwner, Observer {
            it?.let { it1 ->
                    articleAdapter?.update(it1)
            }
        })
    }

    private fun updateTopHeadlineSources(sharedPreference: SharedPreferences) {
        val newsTopHeadlineBySources = liveData(Dispatchers.IO) {
            val result = sharedPreference.getString("sourceSelectedTopHeadlines", "")?.let {
                repository.getTopHeadlinesBySource(
                    it,
                    "92d8c9e8d1a44be58676ee20051e3c77")
            }
            emit(result)
        }
        newsTopHeadlineBySources.observe(viewLifecycleOwner, Observer {
            it?.let { it1 -> articleAdapter?.update(it1) }
        })
    }

    private fun updateTopHeadlinesCountryAndCategory(sharedPreference: SharedPreferences) {
        val newsTopHeadlinesCountryAndCategory = liveData(Dispatchers.IO) {
            val result = sharedPreference.getString("countrySelectedTopHeadlinesFilterTwo", "")?.let {
                repository.getTopHeadlinesByCountryAndCategory(
                    it,
                    sharedPreference.getString("categorySelectedTopHeadlinesFilter","")!!,
                    "92d8c9e8d1a44be58676ee20051e3c77")
            }
            emit(result)
        }
        newsTopHeadlinesCountryAndCategory.observe(viewLifecycleOwner, Observer {
            it?.let { it1 -> articleAdapter?.update(it1) }
        })
    }

    private fun updateTopHeadlineBySubject(sharedPreference: SharedPreferences) {
        val newsTopHeadlinesSubject = liveData(Dispatchers.IO) {
            val result = sharedPreference.getString("subjectSelectedTopHeadlines", "")?.let {
                repository.getTopHeadlinesBySubject(it,"92d8c9e8d1a44be58676ee20051e3c77" )
            }
            emit(result)
        }
        newsTopHeadlinesSubject.observe(viewLifecycleOwner, Observer {
            it?.let { it1 -> articleAdapter?.update(it1) }
        })
    }

    private fun updateEverythingAllBySubject(sharedPreference: SharedPreferences) {
        val everythingAllBySubject = liveData(Dispatchers.IO) {
            val result = sharedPreference.getString("everythingActualNewsString", "")?.let {
                repository.getEverything(it,"92d8c9e8d1a44be58676ee20051e3c77" )
            }
            emit(result)
        }
        everythingAllBySubject.observe(viewLifecycleOwner, Observer {
            it?.let { it1 -> articleAdapter?.update(it1) }
        })
    }

    interface RefreshInterface {
        fun refreshHomeAdapter() {}
    }

}