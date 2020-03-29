package com.nicholasrutherford.distractme.adapters.recyclers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.data.responses.CountriesResponse
import com.nicholasrutherford.distractme.viewholders.FilterByViewHolder

class FilterBy(private val mContext: Context, private var countriesResponse: CountriesResponse, private var listOfCountriesNames: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.filter_layout, parent, false)
        return FilterByViewHolder(itemView,mContext)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val filterByViewHolder = holder as FilterByViewHolder
        filterByViewHolder.main(countriesResponse,position, listOfCountriesNames)
    }
}