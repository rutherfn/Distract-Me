package com.nicholasrutherford.distractme.adapters.recyclers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.data.room.SavedArticlesEntity
import com.nicholasrutherford.distractme.viewholders.NewsDbViewHolder

class NewsDb(private val mContext: Context, private var savedArticleList: List<SavedArticlesEntity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.article_home_layout, parent, false)
        return NewsDbViewHolder(itemView,mContext)
    }

    override fun getItemCount(): Int {
        return savedArticleList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val newsDbViewHolder = holder as NewsDbViewHolder
        newsDbViewHolder.main(savedArticleList, position)
    }

}