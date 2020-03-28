package com.nicholasrutherford.distractme.viewholders

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.data.NewsResponse
import com.nicholasrutherford.distractme.helpers.Typeface
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class NewsHomeViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    private val typeface = Typeface()
    private var ivArticle: CircleImageView = itemView.findViewById(R.id.ivArticle)
    private var tvArticleTitle: TextView = itemView.findViewById(R.id.tvArticleTitle)
    private var tvArticleDesc: TextView = itemView.findViewById(R.id.tvArticleDesc)

    fun main(newsResponse: NewsResponse, pos: Int) {
        setTypeFace()
        Picasso.get().load(newsResponse.articles[pos].urlToImage).into(ivArticle)
        tvArticleTitle.text = newsResponse.articles[pos].title
        tvArticleDesc.text = newsResponse.articles[pos].description
    }

    private fun setTypeFace() {
        typeface.setTypefaceForHeaderBold(tvArticleTitle,mContext)
        typeface.setTypefaceForBodyRegular(tvArticleDesc,mContext)
    }

}