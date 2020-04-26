package com.nicholasrutherford.distractme.adapters.recyclers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.viewholders.WebNewsViewHolder

class WebNews(private val mContext: Context, private val webUrl: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_webview_layout, parent, false)
        return WebNewsViewHolder(itemView, mContext, webUrl)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val webNewsViewHolder = holder as WebNewsViewHolder
        webNewsViewHolder.main()
    }

}