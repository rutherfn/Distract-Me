package com.nicholasrutherford.distractme.viewholders

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.activitys.MainActivity
import com.nicholasrutherford.distractme.data.responses.NewsResponse
import com.nicholasrutherford.distractme.helpers.Typeface
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class NewsHomeViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    private val typeface = Typeface()
    private var ivArticle: CircleImageView = itemView.findViewById(R.id.ivArticle)
    private var tvArticleTitle: TextView = itemView.findViewById(R.id.tvArticleTitle)
    private var tvArticleDesc: TextView = itemView.findViewById(R.id.tvArticleDesc)
    private var tvAuthorTitle: TextView = itemView.findViewById(R.id.tvAuthorTitle)
    private var btnViewArticle: Button = itemView.findViewById(R.id.btnViewArticle)
    private var ivStar: ImageView = itemView.findViewById(R.id.ivStar)
    private var tvSource: TextView = itemView.findViewById(R.id.tvSource)
    private var tvArticleDate: TextView = itemView.findViewById(R.id.tvArticleDate)

    fun main(newsResponse: NewsResponse, pos: Int) {
        setTypeface()

       // if(!newsResponse.articles[pos].urlToImage.isNullOrEmpty()) {
        //    Picasso.get().load(newsResponse.articles[pos].urlToImage).into(ivArticle)
       // } else {
            Picasso.get().load("https://www.thevisateam.com/assets/uploads/placeholder-image.png").into(ivArticle)
       // }

        tvArticleTitle.text = newsResponse.articles[pos].title
        tvArticleDesc.text = newsResponse.articles[pos].description
        tvAuthorTitle.text = newsResponse.articles[pos].author
        btnViewArticle.text = "View More"
        tvSource.text = "Source: ".plus(newsResponse.articles[pos].source.name)
        tvArticleDate.text = "Published Date: ".plus(newsResponse.articles[pos].publishedAt)
        viewMoreNewsImp(newsResponse, pos)
    }

    private fun setTypeface() {
        typeface.setTypefaceForHeaderBold(tvArticleTitle,mContext)
        typeface.setTypefaceForBodyRegular(tvArticleDesc,mContext)
        typeface.setTypefaceForBodyRegularBold(tvAuthorTitle, mContext)
        typeface.setTypefaceForSubHeaderBold(tvSource,mContext)
        typeface.setTypefaceForSubHeaderBold(tvArticleDate,mContext)
    }

    private fun viewMoreNewsImp(newsResponse: NewsResponse, pos: Int) {
        btnViewArticle.setOnClickListener {
            (mContext as MainActivity).refreshNewsAdapter(newsResponse.articles[pos].url)
        }
    }

}