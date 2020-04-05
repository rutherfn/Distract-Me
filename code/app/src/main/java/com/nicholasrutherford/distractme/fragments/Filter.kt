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
        showCountries()
    }

    private val grabCountries = liveData(Dispatchers.IO) {
        val result = repository.getCountries()
        emit(result)
    }

    private fun setUpArticleAdapter() {
        rvFilter!!.itemAnimator = null
        rvFilter!!.layoutManager = LinearLayoutManager(context!!)
    }

    fun showCountries() {
        grabCountries.observe(viewLifecycleOwner, Observer {
            if(listOfCountriesNames.size > 0) {
                listOfCountriesNames.clear()
            }
            for(i in it.countries.iterator()) {
                listOfCountriesNames.add(i.name)
            }
            filterByAdapter = FilterBy(context!!, it, listOfCountriesNames)
            rvFilter!!.adapter = filterByAdapter
        })
    }


}