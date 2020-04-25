package com.nicholasrutherford.distractme.viewholders

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.*
import androidx.preference.PreferenceManager
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
    private val headlinesByCountryTitle : String = mContext.resources.getString(R.string.top_headlines_by_country)
    private val headlinesBySourceTitle : String = mContext.resources.getString(R.string.top_headlines_by_source)
    private val headlinesByCountryAndCategoryTitle : String = mContext.resources.getString(R.string.top_headlines_by_country_and_category)
    private val headlinesBySubjectTitle : String = mContext.resources.getString(R.string.top_headlines_by_subject)
    private val whichCategoryFilterByValue : String = mContext.resources.getString(R.string.which_category_to_filter_by)

    // category's string values
    private val categoryBusiness: String = mContext.resources.getString(R.string.business_category)
    private val categoryEntertainment: String = mContext.resources.getString(R.string.entertainment_category)
    private val categoryGeneral: String = mContext.resources.getString(R.string.general_category)
    private val categoryHealth: String = mContext.resources.getString(R.string.health_category)
    private val categoryScience: String = mContext.resources.getString(R.string.science_category)
    private val categorySports: String = mContext.resources.getString(R.string.sports_category)
    private val categoryTechnology: String = mContext.resources.getString(R.string.technology_category)

    // selected items
    private var countrySelected: String = "ae"
    private var topHeadlineSourceSelected: String = "abc-news"
    private var categorySelected: String = "business"

    // spinner arrays
    private val spinnerCategoryItems = arrayOf(nothingTitle, topHeadlinesTitle, everythingTitle)
    private val spinnerCategoryOfNewsItems = arrayOf(categoryBusiness, categoryEntertainment, categoryGeneral, categoryHealth,
    categoryScience, categorySports, categoryTechnology)
    private val spinnerTopHeadlinesNewsItems = arrayOf(nothingTitle, headlinesByCountryTitle,
        headlinesBySourceTitle, headlinesByCountryAndCategoryTitle,headlinesBySubjectTitle)

    private val typeface = Typeface()

    // Main Filter Layout
    private var tvFilterTitle: TextView = itemView.findViewById(R.id.tvFilterTitle)
    private var spCategoryFilter: Spinner = itemView.findViewById(R.id.spCategoryFilter)

    // Top Headlines Filter By
    private var tvGetTopHeadlineNews: TextView = itemView.findViewById(R.id.tvGetTopHeadlineNews)
    private var spGetTopHeadlineNews: Spinner = itemView.findViewById(R.id.spGetTopHeadlineNews)

    // Top Headlines Filter By Country Only
    private var tvHeadlinesByCountryOnly: TextView = itemView.findViewById(R.id.tvHeadlinesByCountryOnly)
    private var spCountriesOnlyFilter: Spinner = itemView.findViewById(R.id.spCountriesOnlyFilter)
    private var btnConfirmCountryFilter: Button = itemView.findViewById(R.id.btConfirmCountryFilter)

    // Top Headlines Filter By Source
    private var tvHeadlinesSourceCategoryOnly: TextView = itemView.findViewById(R.id.tvHeadlinesSourceCategoryOnly)
    private var spSourceTopHeadlinesFilter: Spinner = itemView.findViewById(R.id.spSourceTopHeadlinesFilter)
    private var btnConfirmSourceTopHeadlinesFilter: Button = itemView.findViewById(R.id.btnConfirmSourceTopHeadlinesFilter)

    // Top headlines filter By Country And Category
    private var tvHeadlinesCountryAndCategory: TextView = itemView.findViewById(R.id.tvHeadlinesCountryAndCategory)
    private var spCountryFilter: Spinner = itemView.findViewById(R.id.spCountryFilter)
    private var tvHeadlinesCategoryAndCountry: TextView = itemView.findViewById(R.id.tvHeadlinesCategoryAndCountry)
    private var spCategoryTopHeadlinesFilter: Spinner = itemView.findViewById(R.id.spCategoryTopHeadlinesFilter)
    private var btnConfirmCategoryAndCountryFilter: Button = itemView.findViewById(R.id.btnConfirmCategoryAndCountryFilter)

    // Top headlines filter by subject
    private var tvHeadlinesBySubject: TextView = itemView.findViewById(R.id.tvHeadlinesBySubject)
    private var etSubjectTopHeadlines: EditText = itemView.findViewById(R.id.etSubjectTopHeadlines)
    private var btnConfirmSubject: Button = itemView.findViewById(R.id.btnConfirmFilterSubject)

    // Filter by everything
    private var tvGetEverythingNews: TextView = itemView.findViewById(R.id.tvGetEverythingNews)
    private var etEverything: EditText = itemView.findViewById(R.id.etEverything)
    private var btnConfirmFilterEverything: Button = itemView.findViewById(R.id.btnConfirmFilterEverything)

    fun main(countriesResponse: CountriesResponse, pos: Int, listOfCountriesNames: ArrayList<String>, listOfSourcesNames: ArrayList<String>, listOfSourceIdNames: ArrayList<String>) {
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(mContext)
        val editor = sharedPreference.edit()
        setTypeface()
        tvFilterTitle.text = whichCategoryFilterByValue
        addItemsListenerToFilterByCategorySpinner()
        topHeadlinesSpinnersListeners(countriesResponse, pos, listOfCountriesNames, listOfSourcesNames, listOfSourceIdNames)
        clickConfirmCategories(editor)
    }

    private fun setTypeface() {
        // header
        typeface.setTypefaceForHeaderBold(tvFilterTitle,mContext)

        // buttons
        typeface.setTypefaceForBodyRegularBold(btnConfirmCategoryAndCountryFilter, mContext)
        typeface.setTypefaceForBodyRegularBold(btnConfirmSourceTopHeadlinesFilter, mContext)
        typeface.setTypefaceForBodyRegularBold(btnConfirmCountryFilter, mContext)
        typeface.setTypefaceForBodyRegularBold(btnConfirmSubject, mContext)
        typeface.setTypefaceForBodyRegularBold(btnConfirmFilterEverything, mContext)

        // sub headers or questions
        typeface.setTypefaceForSubHeaderBold(tvHeadlinesByCountryOnly,mContext)
        typeface.setTypefaceForSubHeaderBold(tvGetTopHeadlineNews, mContext)
        typeface.setTypefaceForSubHeaderBold(tvHeadlinesSourceCategoryOnly, mContext)
        typeface.setTypefaceForSubHeaderBold(tvHeadlinesCountryAndCategory, mContext)
        typeface.setTypefaceForSubHeaderBold(tvHeadlinesCategoryAndCountry, mContext)
        typeface.setTypefaceForSubHeaderBold(tvHeadlinesBySubject, mContext)
        typeface.setTypefaceForSubHeaderBold(tvGetEverythingNews, mContext)
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
                        hideHeadlineCountriesFilterByOnly()
                        hideHeadlinesSourceFilterBy()
                        hideHeadLineCountriesFilterBy()
                        hideHeadlineCategoryFilterBy()
                        hideHeadlineSubjectFilterBy()
                        hideEverythingGrabNews()
                    }
                    parent?.selectedItem.toString() == topHeadlinesTitle -> {
                        showTopHeadlineNews()
                        hideHeadlineCountriesFilterByOnly()
                        hideHeadlinesSourceFilterBy()
                        hideHeadLineCountriesFilterBy()
                        hideHeadlineCategoryFilterBy()
                        hideHeadlineSubjectFilterBy()
                        hideEverythingGrabNews()
                    }
                    parent?.selectedItem.toString() == everythingTitle -> {
                        showEverythingGrabNews()
                        hideTopShowHeadlineNews()
                        hideHeadlineCountriesFilterByOnly()
                        hideHeadlinesSourceFilterBy()
                        hideHeadLineCountriesFilterBy()
                        hideHeadlineCategoryFilterBy()
                        hideHeadlineSubjectFilterBy()
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
                        hideHeadlineCountriesFilterByOnly()
                        hideHeadlinesSourceFilterBy()
                        hideHeadLineCountriesFilterBy()
                        hideHeadlineCategoryFilterBy()
                        hideHeadlineSubjectFilterBy()
                        hideEverythingGrabNews()
                    }
                    parent?.selectedItem.toString() == headlinesByCountryTitle -> {
                        showHeadlineCountriesFilterByOnly()
                        hideHeadlinesSourceFilterBy()
                        hideHeadLineCountriesFilterBy()
                        hideHeadlineCategoryFilterBy()
                        hideHeadlineSubjectFilterBy()
                        hideEverythingGrabNews()
                    }
                    parent?.selectedItem.toString() == headlinesBySourceTitle -> {
                        hideHeadlineCountriesFilterByOnly()
                        showHeadlinesSourceFilterBy()
                        hideHeadLineCountriesFilterBy()
                        hideHeadlineCategoryFilterBy()
                        hideHeadlineSubjectFilterBy()
                        hideEverythingGrabNews()
                    }
                    parent?.selectedItem.toString() == headlinesByCountryAndCategoryTitle -> {
                        hideHeadlineCountriesFilterByOnly()
                        hideHeadlinesSourceFilterBy()
                        showHeadlineCountriesFilterBy()
                        hideHeadlineCategoryFilterBy()
                        hideHeadlineSubjectFilterBy()
                        hideEverythingGrabNews()
                    }
                    parent?.selectedItem.toString() == headlinesBySubjectTitle -> {
                        hideHeadlineCountriesFilterByOnly()
                        hideHeadlinesSourceFilterBy()
                        hideHeadLineCountriesFilterBy()
                        hideHeadlineCategoryFilterBy()
                        showHeadlineSubjectFilterBy()
                        hideEverythingGrabNews()
                    }
                }
            }
        }
    }

    private fun addItemsListenerToCountriesOnlySpinner(countriesResponse: CountriesResponse, pos: Int, listOfCountriesNames: ArrayList<String>) {
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, listOfCountriesNames)
        spCountriesOnlyFilter.adapter = adapter
        spCountriesOnlyFilter.setSelection(0, false)

        spCountriesOnlyFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, listOfSourcesNames)
        spSourceTopHeadlinesFilter.adapter = adapter
        spSourceTopHeadlinesFilter.setSelection(0, false)

        spSourceTopHeadlinesFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing Selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                println(listOfSourceIdNames[0])
                when {
                    parent?.selectedItem.toString() == listOfSourcesNames[position] ->  {
                        topHeadlineSourceSelected = listOfSourceIdNames[position]
                    }
                }
            }

        }
    }

    private fun  addItemsListenerToCountriesSpinner(countriesResponse: CountriesResponse, listOfCountriesNames: ArrayList<String>) {
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, listOfCountriesNames)
        spCountryFilter.adapter = adapter
        spCountryFilter.setSelection(0, false)

        spCountryFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing Selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (countriesResponse.countries[position].name) {
                    parent?.selectedItem.toString() -> {
                        countrySelected = countriesResponse.countries[position].abv
                        showHeadlineCategoryFilterBy()
                    }
                }
            }
        }
    }

    private fun addItemsListenerToCategorySpinner() {
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, spinnerCategoryOfNewsItems )
        spCategoryTopHeadlinesFilter.adapter = adapter

        spCategoryTopHeadlinesFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing Selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                println(position)
                when (spinnerCategoryOfNewsItems[position]) {
                    parent?.selectedItem.toString() -> {
                        when (position) {
                            0 -> {
                                categorySelected = spinnerCategoryOfNewsItems[0]
                            }
                            1 -> {
                                categorySelected = spinnerCategoryOfNewsItems[1]
                            }
                            2 -> {
                                categorySelected = spinnerCategoryOfNewsItems[2]
                            }
                            3 -> {
                                categorySelected = spinnerCategoryOfNewsItems[3]
                            }
                            4 -> {
                                categorySelected = spinnerCategoryOfNewsItems[4]
                            }
                            5 -> {
                                categorySelected = spinnerCategoryOfNewsItems[5]
                            }
                            6 -> {
                                categorySelected = spinnerCategoryOfNewsItems[6]
                            }
                        }
                    }
                }
            }

        }
    }

    private fun topHeadlinesSpinnersListeners(countriesResponse: CountriesResponse, pos: Int, listOfCountriesNames: ArrayList<String>, listOfSourcesNames: ArrayList<String>, listOfSourceIdNames: ArrayList<String>) {
        addItemsToNewsFilterByTopHeadlinesListenerSpinners() // spinner listener for top headlines category
        addItemsListenerToCountriesOnlySpinner(countriesResponse, pos, listOfCountriesNames) // spinner for country only filter by
        addItemsListenerToSourceSpinner(listOfSourcesNames, listOfSourceIdNames) // spinner listener for source top headlines
        addItemsListenerToCountriesSpinner(countriesResponse, listOfCountriesNames) // spinner for country listener for category and country
        addItemsListenerToCategorySpinner() // spinner for categories under filter by category and country
    }

    private fun showTopHeadlineNews() {
        tvGetTopHeadlineNews.visibility = View.VISIBLE
        spGetTopHeadlineNews.visibility = View.VISIBLE
    }

    private fun hideTopShowHeadlineNews() {
        tvGetTopHeadlineNews.visibility = View.GONE
        spGetTopHeadlineNews.visibility = View.GONE
    }

    private fun showHeadlineCountriesFilterByOnly() {
        tvHeadlinesByCountryOnly.visibility = View.VISIBLE
        spCountriesOnlyFilter.visibility = View.VISIBLE
        btnConfirmCountryFilter.visibility = View.VISIBLE
    }

    private fun hideHeadlineCountriesFilterByOnly() {
        tvHeadlinesByCountryOnly.visibility = View.GONE
        spCountriesOnlyFilter.visibility = View.GONE
        btnConfirmCountryFilter.visibility = View.GONE
    }

    private fun showHeadlinesSourceFilterBy() {
        tvHeadlinesSourceCategoryOnly.visibility = View.VISIBLE
        spSourceTopHeadlinesFilter.visibility = View.VISIBLE
        btnConfirmSourceTopHeadlinesFilter.visibility = View.VISIBLE
    }

    private fun hideHeadlinesSourceFilterBy() {
        tvHeadlinesSourceCategoryOnly.visibility = View.GONE
        spSourceTopHeadlinesFilter.visibility = View.GONE
        btnConfirmSourceTopHeadlinesFilter.visibility = View.GONE
    }

    private fun showHeadlineCountriesFilterBy() {
        tvHeadlinesCountryAndCategory.visibility = View.VISIBLE
        spCountryFilter.visibility = View.VISIBLE
    }

    private fun hideHeadLineCountriesFilterBy() {
        tvHeadlinesCountryAndCategory.visibility = View.GONE
        spCountryFilter.visibility = View.GONE
    }

    private fun showHeadlineCategoryFilterBy() {
        tvHeadlinesCategoryAndCountry.visibility = View.VISIBLE
        spCategoryTopHeadlinesFilter.visibility = View.VISIBLE
        btnConfirmCategoryAndCountryFilter.visibility = View.VISIBLE
    }

    private fun hideHeadlineCategoryFilterBy() {
        tvHeadlinesCategoryAndCountry.visibility = View.GONE
        spCategoryTopHeadlinesFilter.visibility = View.GONE
        btnConfirmCategoryAndCountryFilter.visibility = View.GONE
    }

    private fun showHeadlineSubjectFilterBy() {
        tvHeadlinesBySubject.visibility = View.VISIBLE
        etSubjectTopHeadlines.visibility = View.VISIBLE
        btnConfirmSubject.visibility = View.VISIBLE
    }

    private fun hideHeadlineSubjectFilterBy() {
        tvHeadlinesBySubject.visibility = View.GONE
        etSubjectTopHeadlines.visibility = View.GONE
        btnConfirmSubject.visibility = View.GONE
    }

    private fun showEverythingGrabNews() {
        tvGetEverythingNews.visibility = View.VISIBLE
        etEverything.visibility = View.VISIBLE
        btnConfirmFilterEverything.visibility = View.VISIBLE
    }

    private fun hideEverythingGrabNews() {
        tvGetEverythingNews.visibility = View.GONE
        etEverything.visibility = View.GONE
        btnConfirmFilterEverything.visibility = View.GONE
    }

    private fun setTopHeadlineByCountryOnlyFilters(editor: SharedPreferences.Editor) {
        editor.putBoolean("countryFilterByTopHeadlines",true)
        editor.putString("countrySelectedTopHeadlines", countrySelected)
    }

    private fun removeTopHeadlineByCountryOnlyFilters(editor: SharedPreferences.Editor) {
        editor.putBoolean("countryFilterByTopHeadlines",false)
        editor.putString("countrySelectedTopHeadlines", "")
    }

    private fun setTopHeadlineBySourcesFilters(editor: SharedPreferences.Editor) {
        editor.putBoolean("sourceFilterByTopHeadlines",true)
        editor.putString("sourceSelectedTopHeadlines",topHeadlineSourceSelected)
    }

    private fun removeTopHeadlineBySourcesFilters(editor: SharedPreferences.Editor) {
        editor.putBoolean("sourceFilterByTopHeadlines",false)
        editor.putString("sourceSelectedTopHeadlines","")
    }

    private fun removeTopHeadlineByCountryAndCategoriesFilters(editor: SharedPreferences.Editor) {
        editor.putBoolean("countryAndCategoryFilterByTopHeadlines",false)
        editor.putString("countrySelectedTopHeadlinesFilterTwo","")
        editor.putString("categorySelectedTopHeadlinesFilter","")
    }

    private fun setTopHeadlineByCountryAndCategoriesFilters(editor: SharedPreferences.Editor) {
        editor.putBoolean("countryAndCategoryFilterByTopHeadlines",true)
        editor.putString("countrySelectedTopHeadlinesFilterTwo",countrySelected)
        editor.putString("categorySelectedTopHeadlinesFilter",categorySelected)
    }

    private fun removeTopHeadlineSubjectFilters(editor: SharedPreferences.Editor) {
        editor.putBoolean("subjectFilterByTopHeadlines",false)
        editor.putString("subjectSelectedTopHeadlines","")
    }

    private fun setTopHeadlineSubjectFilters(editor: SharedPreferences.Editor) {
        editor.putBoolean("subjectFilterByTopHeadlines",true)
        editor.putString("subjectSelectedTopHeadlines",etSubjectTopHeadlines.text.toString())
    }

    private fun removeEverythingAllNewsBySubjectFilters(editor: SharedPreferences.Editor) {
        editor.putBoolean("everythingGrabAllNewsBy",false)
        editor.putString("everythingActualNewsString","")
    }

    private fun setEverythingAllNewsBySubjectFilters(editor: SharedPreferences.Editor) {
        editor.putBoolean("everythingGrabAllNewsBy",true)
        editor.putString("everythingActualNewsString",etEverything.text.toString())
    }

    private fun countryFilter(editor: SharedPreferences.Editor) {
        setTopHeadlineByCountryOnlyFilters(editor)
        removeTopHeadlineBySourcesFilters(editor)
        removeTopHeadlineByCountryAndCategoriesFilters(editor)
        removeTopHeadlineSubjectFilters(editor)
        removeEverythingAllNewsBySubjectFilters(editor)
        editor.apply()
        (mContext as MainActivity).refreshHomeAdapter()
    }

    private fun sourceTopHeadlinesFilter(editor: SharedPreferences.Editor) {
        setTopHeadlineBySourcesFilters(editor)
        removeTopHeadlineByCountryOnlyFilters(editor)
        removeTopHeadlineByCountryAndCategoriesFilters(editor)
        removeTopHeadlineSubjectFilters(editor)
        removeEverythingAllNewsBySubjectFilters(editor)
        editor.apply()
        (mContext as MainActivity).refreshHomeAdapter()
    }

    private fun categoryAndCountryFilter(editor: SharedPreferences.Editor) {
        removeTopHeadlineByCountryOnlyFilters(editor)
        removeTopHeadlineBySourcesFilters(editor)
        removeTopHeadlineSubjectFilters(editor)
        removeEverythingAllNewsBySubjectFilters(editor)
        setTopHeadlineByCountryAndCategoriesFilters(editor)
        editor.apply()
        (mContext as MainActivity).refreshHomeAdapter()
    }

    private fun subjectFilter(editor: SharedPreferences.Editor) {
        setTopHeadlineSubjectFilters(editor)
        removeTopHeadlineByCountryOnlyFilters(editor)
        removeTopHeadlineBySourcesFilters(editor)
        removeTopHeadlineByCountryAndCategoriesFilters(editor)
        removeEverythingAllNewsBySubjectFilters(editor)
        editor.apply()
        (mContext as MainActivity).refreshHomeAdapter()
    }

    private fun everythingFilter(editor: SharedPreferences.Editor) {
        setEverythingAllNewsBySubjectFilters(editor)
        removeTopHeadlineSubjectFilters(editor)
        removeTopHeadlineByCountryOnlyFilters(editor)
        removeTopHeadlineBySourcesFilters(editor)
        removeTopHeadlineByCountryAndCategoriesFilters(editor)
        editor.apply()
        (mContext as MainActivity).refreshHomeAdapter()
    }


    private fun clickConfirmCategories(editor: SharedPreferences.Editor) {
        btnConfirmCountryFilter.setOnClickListener {
            countryFilter(editor)
        }

        btnConfirmSourceTopHeadlinesFilter.setOnClickListener {
            sourceTopHeadlinesFilter(editor)
        }

        btnConfirmCategoryAndCountryFilter.setOnClickListener {
            categoryAndCountryFilter(editor)
        }

        btnConfirmSubject.setOnClickListener {
            subjectFilter(editor)
        }

        btnConfirmFilterEverything.setOnClickListener {
            everythingFilter(editor)
        }

    }

}