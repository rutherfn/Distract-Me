package com.nicholasrutherford.distractme.viewholders

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.data.responses.CountriesResponse
import com.nicholasrutherford.distractme.helpers.Typeface

class FilterByViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    // string values
    private val nothingValue: String = mContext.resources.getString(R.string.nothing)
    private val topHeadlinesValue: String = mContext.resources.getString(R.string.top_headlines)
    private val everythingValue : String = mContext.resources.getString(R.string.everything)
    private val sourcesValue : String = mContext.resources.getString(R.string.sources)
    private val whichCategoryFilterByValue : String = mContext.resources.getString(R.string.which_category_to_filter_by)

    // spinner arrays
    private val spinnerItems = arrayOf(nothingValue, topHeadlinesValue, everythingValue, sourcesValue)


    private val typeface = Typeface()
    private var tvFilterTitle: TextView = itemView.findViewById(R.id.tvFilterTitle)
    private var tvGetTopHeadlineNews: TextView = itemView.findViewById(R.id.tvGetTopHeadlineNews)
    private var spCategoryFilter: Spinner = itemView.findViewById(R.id.spCategoryFilter)
    private var spCountriesFilter: Spinner = itemView.findViewById(R.id.spCountriesFilter)
    private var spGetTopHeadlineNews: Spinner = itemView.findViewById(R.id.spGetTopHeadlineNews)
    private var tvHeadlinesCountryCategory: TextView = itemView.findViewById(R.id.tvHeadlinesCountryCategory)

    fun main(countriesResponse: CountriesResponse, pos: Int, listOfCountriesNames: ArrayList<String>) {
        tvFilterTitle.text = whichCategoryFilterByValue
        addItemsListenerToFilterByCategorySpinner()
        addItemsToNewsTypeListenerSpinners()
        addItemsListenerToCountriesSpinner(countriesResponse, pos, listOfCountriesNames)
        setTypeface()
    }

    private fun addItemsListenerToFilterByCategorySpinner() {
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spCategoryFilter.adapter = adapter

        spCategoryFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing was selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when {
                    parent?.selectedItem.toString() == nothingValue -> {
                        hideTopShowHeadlineNews()
                    }
                    parent?.selectedItem.toString() == topHeadlinesValue -> {
                        showTopHeadlineNews()
                    }
                    parent?.selectedItem.toString() == everythingValue -> {
                        hideTopShowHeadlineNews()
                    }
                    parent?.selectedItem.toString() == sourcesValue -> {
                        hideTopShowHeadlineNews()
                    }
                }
            }
        }
    }

    private fun addItemsToNewsTypeListenerSpinners() {

    }

    private fun addItemsListenerToCountriesSpinner(countriesResponse: CountriesResponse, pos: Int, listOfCountriesNames: ArrayList<String>) {
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, listOfCountriesNames)
        spCountriesFilter.adapter = adapter

        spCountriesFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing Selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (countriesResponse.countries[pos].name) {
                    parent?.selectedItem.toString() -> {

                    }
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
    }

    private fun hideHeadlineCountries() {
        tvHeadlinesCountryCategory.visibility = View.GONE
        spCountriesFilter.visibility = View.GONE
    }

}