package com.nicholasrutherford.distractme.viewholders

import android.content.Context
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.activitys.MainActivity
import com.nicholasrutherford.distractme.data.responses.CountriesResponse
import com.nicholasrutherford.distractme.helpers.Typeface

class FilterByViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    // string values
    private val nothingTitle: String = mContext.resources.getString(R.string.nothing)
    private val topHeadlinesTitle: String = mContext.resources.getString(R.string.top_headlines)
    private val everythingTitle : String = mContext.resources.getString(R.string.everything)
    private val sourcesTitle : String = mContext.resources.getString(R.string.sources)
    private val headlinesByCountryTitle : String = mContext.resources.getString(R.string.top_headlines_by_country)
    private val headlinesBySourceTitle : String = mContext.resources.getString(R.string.top_headlines_by_source)
    private val headlinesByCountryAndCategoryTitle : String = mContext.resources.getString(R.string.top_headlines_by_country_and_category)
    private val headlinesBySubjectTitle : String = mContext.resources.getString(R.string.top_headlines_by_subject)
    private val whichCategoryFilterByValue : String = mContext.resources.getString(R.string.which_category_to_filter_by)

    // shared prefs
    private val sharedPreference by lazy { mContext.getSharedPreferences("NewsSharedPreferences", Context.MODE_PRIVATE) }
    private var editor = sharedPreference.edit()

    // selected items
    private var countrySelected: String = ""
    private var topHeadlineSourceSelected: String = ""

    // spinner arrays
    private val spinnerCategoryItems = arrayOf(nothingTitle, topHeadlinesTitle, everythingTitle, sourcesTitle)
    private val spinnerTopHeadlinesNewsItems = arrayOf(nothingTitle, headlinesByCountryTitle,
        headlinesBySourceTitle, headlinesByCountryAndCategoryTitle,headlinesBySubjectTitle)


    private val typeface = Typeface()

    private var tvFilterTitle: TextView = itemView.findViewById(R.id.tvFilterTitle)
    private var tvGetTopHeadlineNews: TextView = itemView.findViewById(R.id.tvGetTopHeadlineNews)
    private var spCategoryFilter: Spinner = itemView.findViewById(R.id.spCategoryFilter)
    private var spCountriesFilter: Spinner = itemView.findViewById(R.id.spCountriesFilter)
    private var spGetTopHeadlineNews: Spinner = itemView.findViewById(R.id.spGetTopHeadlineNews)
    private var tvHeadlinesCountryCategory: TextView = itemView.findViewById(R.id.tvHeadlinesCountryCategory)
    private var btnConfirmCountryFilter: Button = itemView.findViewById(R.id.btConfirmCountryFilter)

    // Top Headlines Filter By Source
    private var tvHeadlinesSourceCategory: TextView = itemView.findViewById(R.id.tvHeadlinesSourceCategory)
    private var spSourceTopHeadlinesFilter: Spinner = itemView.findViewById(R.id.spSourceTopHeadlinesFilter)
    private var btnConfirmSourceTopHeadlinesFilter: Button = itemView.findViewById(R.id.btnConfirmSourceTopHeadlinesFilter)

    fun main(countriesResponse: CountriesResponse, pos: Int, listOfCountriesNames: ArrayList<String>, listOfSourcesNames: ArrayList<String>, listOfSourceIdNames: ArrayList<String>) {
        tvFilterTitle.text = whichCategoryFilterByValue
        addItemsListenerToFilterByCategorySpinner()
        topHeadlinesSpinnersListeners(countriesResponse, pos, listOfCountriesNames, listOfSourcesNames, listOfSourceIdNames)
        setTypeface()
        clickConfirmCategoryFilter()
    }

    private fun addItemsListenerToFilterByCategorySpinner() {
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, spinnerCategoryItems)
        spCategoryFilter.adapter = adapter

        spCategoryFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing was selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when {
                    parent?.selectedItem.toString() == nothingTitle -> {
                        hideTopShowHeadlineNews()
                        hideHeadlineCountriesFilterBy()
                    }
                    parent?.selectedItem.toString() == topHeadlinesTitle -> {
                        showTopHeadlineNews()
                    }
                    parent?.selectedItem.toString() == everythingTitle -> {
                        hideTopShowHeadlineNews()
                    }
                    parent?.selectedItem.toString() == sourcesTitle -> {
                        hideTopShowHeadlineNews()
                    }
                }
            }
        }
    }

    private fun addItemsToNewsFilterByTopHeadlinesListenerSpinners() {
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, spinnerTopHeadlinesNewsItems)
        spGetTopHeadlineNews.adapter = adapter

        spGetTopHeadlineNews.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when {
                    parent?.selectedItem.toString() == nothingTitle -> {
                        hideHeadlineCountriesFilterBy()
                        hideHeadlinesSourceFilterBy()
                    }
                    parent?.selectedItem.toString() == headlinesByCountryTitle -> {
                        showHeadlineCountriesFilterBy()
                        hideHeadlinesSourceFilterBy()
                    }
                    parent?.selectedItem.toString() == headlinesBySourceTitle -> {
                        hideHeadlineCountriesFilterBy()
                        showHeadlinesSourceFilterBy()
                    }
                    parent?.selectedItem.toString() == headlinesByCountryAndCategoryTitle -> {
                        hideHeadlineCountriesFilterBy()
                        hideHeadlinesSourceFilterBy()
                    }
                    parent?.selectedItem.toString() == headlinesBySubjectTitle -> {
                        hideHeadlineCountriesFilterBy()
                        hideHeadlinesSourceFilterBy()
                    }
                }
            }

        }
    }

    private fun addItemsListenerToCountriesSpinner(countriesResponse: CountriesResponse, pos: Int, listOfCountriesNames: ArrayList<String>) {
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, listOfCountriesNames)
        spCountriesFilter.adapter = adapter
        spCountriesFilter.setSelection(0, false)
        spCountriesFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing Selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (countriesResponse.countries[position].name) {
                        parent?.selectedItem.toString() -> {
                            countrySelected = countriesResponse.countries[position].abv
                        }
                    }
            }
        }
    }

    private fun addItemsListenerToSourceSpinner(listOfSourcesNames: ArrayList<String>, listOfSourceIdNames: ArrayList<String>) {
        println(listOfSourcesNames[0])
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, listOfSourcesNames)
        spSourceTopHeadlinesFilter.adapter = adapter
        spSourceTopHeadlinesFilter.setSelection(0, false)
        spSourceTopHeadlinesFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing Selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when {
                    parent?.selectedItem.toString() == listOfSourcesNames[position] ->  {
                        topHeadlineSourceSelected = listOfSourceIdNames[position]
                    }
                }
            }

        }
    }

    private fun topHeadlinesSpinnersListeners(countriesResponse: CountriesResponse, pos: Int, listOfCountriesNames: ArrayList<String>, listOfSourcesNames: ArrayList<String>, listOfSourceIdNames: ArrayList<String>) {
        addItemsToNewsFilterByTopHeadlinesListenerSpinners()
        addItemsListenerToCountriesSpinner(countriesResponse, pos, listOfCountriesNames)
        addItemsListenerToSourceSpinner(listOfSourcesNames, listOfSourceIdNames)
    }


    private fun setTypeface() {
        typeface.setTypefaceForHeaderBold(tvFilterTitle,mContext)
        typeface.setTypefaceForSubHeaderBold(tvHeadlinesCountryCategory,mContext)
        typeface.setTypefaceForSubHeaderBold(tvGetTopHeadlineNews, mContext)
        typeface.setTypefaceForSubHeaderBold(tvHeadlinesSourceCategory, mContext)
    }

    private fun showTopHeadlineNews() {
        tvGetTopHeadlineNews.visibility = View.VISIBLE
        spGetTopHeadlineNews.visibility = View.VISIBLE
    }

    private fun hideTopShowHeadlineNews() {
        tvGetTopHeadlineNews.visibility = View.GONE
        spGetTopHeadlineNews.visibility = View.GONE
    }

    private fun showHeadlineCountriesFilterBy() {
        tvHeadlinesCountryCategory.visibility = View.VISIBLE
        spCountriesFilter.visibility = View.VISIBLE
        btnConfirmCountryFilter.visibility = View.VISIBLE
    }

    private fun hideHeadlineCountriesFilterBy() {
        tvHeadlinesCountryCategory.visibility = View.GONE
        spCountriesFilter.visibility = View.GONE
        btnConfirmCountryFilter.visibility = View.GONE
    }

    private fun showHeadlinesSourceFilterBy() {
        tvHeadlinesSourceCategory.visibility = View.VISIBLE
        spSourceTopHeadlinesFilter.visibility = View.VISIBLE
        btnConfirmSourceTopHeadlinesFilter.visibility = View.VISIBLE
    }

    private fun hideHeadlinesSourceFilterBy() {
        tvHeadlinesSourceCategory.visibility = View.GONE
        spSourceTopHeadlinesFilter.visibility = View.GONE
        btnConfirmSourceTopHeadlinesFilter.visibility = View.GONE
    }

    private fun setTopHeadlineByCountryFilters() {
        editor.putBoolean("countryFilterByTopHeadlines",true)
        editor.putString("countrySelectedTopHeadlines", countrySelected)
    }

    private fun removeTopHeadlineByCountryFilters() {
        editor.putBoolean("countryFilterByTopHeadlines",false)
        editor.putString("countrySelectedTopHeadlines", "")
    }

    private fun setTopHeadlineBySourcesFilter() {
        editor.putBoolean("sourceFilterByTopHeadlines",true)
        editor.putString("sourceSelectedTopHeadlines",topHeadlineSourceSelected)
    }

    private fun removeTopHeadlineBySourcesFilter() {
        editor.putBoolean("sourceFilterByTopHeadlines",false)
        editor.putString("sourceSelectedTopHeadlines","")
    }

    private fun clickConfirmCategoryFilter() {
        btnConfirmCountryFilter.setOnClickListener {
            setTopHeadlineByCountryFilters()
            removeTopHeadlineBySourcesFilter()
            editor.apply()
            (mContext as MainActivity).refreshAdapterFragmentB()
        }
        btnConfirmSourceTopHeadlinesFilter.setOnClickListener {
            setTopHeadlineBySourcesFilter()
            removeTopHeadlineByCountryFilters()
            editor.apply()
            (mContext as MainActivity).refreshAdapterFragmentB()
        }
        //
    }

}