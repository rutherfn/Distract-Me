package com.nicholasrutherford.distractme.viewholders

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.activitys.WebViewActivity
import com.nicholasrutherford.distractme.adapters.recyclers.News
import com.nicholasrutherford.distractme.data.responses.NewsResponse
import com.nicholasrutherford.distractme.fragments.Home
import com.nicholasrutherford.distractme.helpers.Typeface
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class NewsHomeViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    private val typeface = Typeface()
    var ivArticle: CircleImageView = itemView.findViewById(R.id.ivArticle)
    private var tvArticleTitle: TextView = itemView.findViewById(R.id.tvArticleTitle)
    private var tvArticleDesc: TextView = itemView.findViewById(R.id.tvArticleDesc)
    private var tvAuthorTitle: TextView = itemView.findViewById(R.id.tvAuthorTitle)
    private var btnViewArticle: Button = itemView.findViewById(R.id.btnViewArticle)
    private var ivStar: ImageView = itemView.findViewById(R.id.ivStar)
    private var tvSource: TextView = itemView.findViewById(R.id.tvSource)
    private var tvArticleDate: TextView = itemView.findViewById(R.id.tvArticleDate)

    fun main(newsResponse: NewsResponse, pos: Int) {
        setTypeface()
        Picasso.get().load(newsResponse.articles[pos].urlToImage).placeholder(R.drawable.news).into(ivArticle)
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
          //  startWebViewActivity(newsResponse, pos)
        }
    }

    private fun startWebViewActivity(newsResponse: NewsResponse, pos: Int) {
        val intent = Intent(mContext, WebViewActivity::class.java)
        intent.putExtra("url", newsResponse.articles[pos].url)
        mContext.startActivity(intent)
    }

}