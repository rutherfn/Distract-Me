package com.nicholasrutherford.distractme.viewholders

import android.content.Context
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.activitys.MainActivity
import com.nicholasrutherford.distractme.adapters.recyclers.News
import com.nicholasrutherford.distractme.data.responses.CountriesResponse
import com.nicholasrutherford.distractme.fragments.Home
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

    fun main(countriesResponse: CountriesResponse, pos: Int, listOfCountriesNames: ArrayList<String>) {
        tvFilterTitle.text = whichCategoryFilterByValue
        addItemsToNewsTypeListenerSpinners()
        topHeadlinesSpinnersListeners(countriesResponse, pos, listOfCountriesNames)
        setTypeface()
        btnConfirmCountryFilter.setOnClickListener {
            editor.putBoolean("countryFilterBy",true)
            editor.putString("countrySelected", countrySelected)
            editor.apply()
            (mContext as MainActivity).refreshAdapterFragmentB()
        }
    }

    private fun addItemsToNewsTypeListenerSpinners() {
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, spinnerTopHeadlinesNewsItems)
        spGetTopHeadlineNews.adapter = adapter

        spGetTopHeadlineNews.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when {
                    parent?.selectedItem.toString() == nothingTitle -> {hideHeadlineCountries() }
                    parent?.selectedItem.toString() == headlinesByCountryTitle -> {showHeadlineCountries() }
                    parent?.selectedItem.toString() == headlinesBySourceTitle -> {hideHeadlineCountries()}
                    parent?.selectedItem.toString() == headlinesByCountryAndCategoryTitle -> {hideHeadlineCountries()}
                    parent?.selectedItem.toString() == headlinesBySubjectTitle -> {hideHeadlineCountries()}
                }
            }

        }
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
                        hideHeadlineCountries()
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

    private fun topHeadlinesSpinnersListeners(countriesResponse: CountriesResponse, pos: Int, listOfCountriesNames: ArrayList<String>) {
        addItemsListenerToCountriesSpinner(countriesResponse, pos, listOfCountriesNames)
        addItemsListenerToFilterByCategorySpinner()
    }

    private fun addItemsListenerToCountriesSpinner(countriesResponse: CountriesResponse, pos: Int, listOfCountriesNames: ArrayList<String>) {
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, listOfCountriesNames)
        spCountriesFilter.adapter = adapter

        spCountriesFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing Selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (countriesResponse.countries[position].name) {
                    parent?.selectedItem.toString() -> { countrySelected = countriesResponse.countries[position].abv}
                }
            }

        }
    }

    private fun setTypeface() {
        typeface.setTypefaceForHeaderBold(tvFilterTitle,mContext)
        typeface.setTypefaceForSubHeaderBold(tvHeadlinesCountryCategory,mContext)
        typeface.setTypefaceForSubHeaderBold(tvGetTopHeadlineNews, mContext)
    }

    private fun showTopHeadlineNews() {
        tvGetTopHeadlineNews.visibility = View.VISIBLE
        spGetTopHeadlineNews.visibility = View.VISIBLE
    }

    private fun hideTopShowHeadlineNews() {
        tvGetTopHeadlineNews.visibility = View.GONE
        spGetTopHeadlineNews.visibility = View.GONE
    }

    private fun showHeadlineCountries() {
        tvHeadlinesCountryCategory.visibility = View.VISIBLE
        spCountriesFilter.visibility = View.VISIBLE
        btnConfirmCountryFilter.visibility = View.VISIBLE
    }

    private fun hideHeadlineCountries() {
        tvHeadlinesCountryCategory.visibility = View.GONE
        spCountriesFilter.visibility = View.GONE
        btnConfirmCountryFilter.visibility = View.GONE
    }

}