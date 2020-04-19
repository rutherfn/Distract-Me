package com.nicholasrutherford.distractme.adapters.recyclers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.viewholders.MoreOptionsViewHolder

class MoreOptions(private val mContext: Context,
                  private val optionsList: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.more_content, parent, false)
        return MoreOptionsViewHolder(itemView, mContext)
    }

    override fun getItemCount(): Int {
        return optionsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val moreOptionsViewHolder = holder as MoreOptionsViewHolder
        moreOptionsViewHolder.main(position, optionsList)
    }

}