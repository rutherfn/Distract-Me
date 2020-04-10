package com.nicholasrutherford.distractme.adapters.recyclers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.data.responses.NewsResponse
import com.nicholasrutherford.distractme.viewholders.NewsHomeViewHolder

class News(private val mContext: Context, private var newsResponse: NewsResponse) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.article_home_layout, parent, false)
        return NewsHomeViewHolder(itemView,mContext)
    }

    override fun getItemCount(): Int {
        return newsResponse.articles.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val newsHomeViewHolder = holder as NewsHomeViewHolder
        newsHomeViewHolder.main(newsResponse,position)
        newsHomeViewHolder.ivArticle.setOnClickListener {
        }
    }

    fun update(newNewsResponse: NewsResponse) {
        newsResponse = newNewsResponse
        notifyDataSetChanged()
    }

}