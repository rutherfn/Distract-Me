package com.nicholasrutherford.distractme.viewholders

import android.content.Context
import android.opengl.Visibility
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.helpers.Typeface

class FilterByViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    // string values
    private val topHeadlinesValue: String = mContext.resources.getString(R.string.top_headlines)
    private val everythingValue : String = mContext.resources.getString(R.string.everything)
    private val sourcesValue : String = mContext.resources.getString(R.string.sources)
    private val whichCategoryFilterByValue : String = mContext.resources.getString(R.string.which_category_to_filter_by)

    // spinner arrays
    private val spinnerItems = arrayOf(topHeadlinesValue,everythingValue, sourcesValue)


    private val typeface = Typeface()
    private var tvFilterTitle: TextView = itemView.findViewById(R.id.tvFilterTitle)
    private var spCategoryFilter: Spinner = itemView.findViewById(R.id.spCategoryFilter)
    private var tvHeadlinesCountryCategory: TextView = itemView.findViewById(R.id.tvHeadlinesCountryCategory)

    fun main(pos: Int) {
        tvFilterTitle.text = whichCategoryFilterByValue
        addItemsToSpinner()
        setTypeface()
    }

    private fun addItemsToSpinner() {
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spCategoryFilter.adapter = adapter

        spCategoryFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing was selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when {
                    parent?.selectedItem.toString() == topHeadlinesValue -> {
                        println("Top headlines")
                    }
                    parent?.selectedItem.toString() == everythingValue -> {
                        // func here
                    }
                    parent?.selectedItem.toString() == sourcesValue -> {

                    }
                }
            }
        }
    }

    private fun setTypeface() {
        typeface.setTypefaceForHeaderBold(tvFilterTitle,mContext)
        typeface.setTypefaceForSubHeaderBold(tvHeadlinesCountryCategory,mContext)
    }

    private fun showHeadline() {

    }

    private fun hideHeadline() {

    }

}