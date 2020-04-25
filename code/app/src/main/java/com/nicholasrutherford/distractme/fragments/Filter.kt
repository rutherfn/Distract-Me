package com.nicholasrutherford.distractme.fragments

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
import com.nicholasrutherford.distractme.adapters.recyclers.FilterBy
import com.nicholasrutherford.distractme.network.repositoryImp.NewsRepositoryImp
import kotlinx.coroutines.Dispatchers

class Filter: Fragment() {

    private var listOfCountriesNames: ArrayList<String> = ArrayList()
    private var listOfSourcesIdNames: ArrayList<String> = ArrayList()
    private var listOfSourcesNames: ArrayList<String> = ArrayList()
    private var filterByAdapter: FilterBy? = null
    private val repository: NewsRepositoryImp = NewsRepositoryImp()
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
        initCountriesAndSetAdapter()
        initSources()
    }

    private val grabCountries = liveData(Dispatchers.IO) {
        val result = repository.getCountries()
        emit(result)
    }

    private val grabAllSources = liveData(context = Dispatchers.IO) {
        val result = repository.getAllSources("92d8c9e8d1a44be58676ee20051e3c77")
        emit(result)
    }

    private fun setUpArticleAdapter() {
        rvFilter!!.itemAnimator = null
        rvFilter!!.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initCountriesAndSetAdapter() {
        grabCountries.observe(viewLifecycleOwner, Observer { it ->
            if(listOfCountriesNames.size > 0) {
                listOfCountriesNames.clear()
            }

            for(i in it.countries.iterator()) {
                listOfCountriesNames.add(i.name)
            }

            filterByAdapter = FilterBy(requireContext(), it, listOfCountriesNames, listOfSourcesNames, listOfSourcesIdNames)
            rvFilter!!.adapter = filterByAdapter
        })
    }

    private fun initSources() {
        grabAllSources.observe(viewLifecycleOwner, Observer {
            if(listOfSourcesIdNames.size > 0) {
                listOfSourcesIdNames.clear()
            }
            if(listOfSourcesNames.size > 0) {
                listOfSourcesNames.clear()
            }
            for(i in it.sources.iterator()) {
                listOfSourcesIdNames.add(i.id)
                listOfSourcesNames.add(i.name)
            }
        })
    }


}