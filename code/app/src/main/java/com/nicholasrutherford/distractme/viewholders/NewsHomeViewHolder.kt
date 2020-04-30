package com.nicholasrutherford.distractme.viewholders

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

    // string values
    private var articleTitle: String = ""
    private var articleDesc: String = ""
    private var articleAuthor: String = ""
    private var articleSources: String = ""
    private var articlePublishedDate: String = ""
    private var articleImage: String = ""
    private var articleViewMoreUrl: String = ""
    private var viewMore: String = "View More"

    fun main(newsResponse: NewsResponse, pos: Int) {
        setTypeface()
        setData(newsResponse, pos)
        initDataIntoLayout()
        viewMoreNewsImp()
        likeArticleClickFunc(newsResponse, pos)
    }

    private fun setTypeface() {
        typeface.setTypefaceForHeaderBold(tvArticleTitle,mContext)
        typeface.setTypefaceForBodyRegular(tvArticleDesc,mContext)
        typeface.setTypefaceForBodyRegularBold(tvAuthorTitle, mContext)
        typeface.setTypefaceForSubHeaderBold(tvSource,mContext)
        typeface.setTypefaceForSubHeaderBold(tvArticleDate,mContext)
    }

    private fun setData(newsResponse: NewsResponse, pos: Int) {
        articleTitle = if(newsResponse.articles[pos].title.isNullOrEmpty()) {
            "Unknown Title"
        } else {
            newsResponse.articles[pos].title
        }

        articleDesc = if(newsResponse.articles[pos].description.isNullOrEmpty()) {
            "Unknown Desc..."
        } else {
            newsResponse.articles[pos].description
        }

        articleAuthor = if(newsResponse.articles[pos].author.isNullOrEmpty()) {
            "Unknown Author"
        } else {
            newsResponse.articles[pos].author
        }

        articleSources = if(newsResponse.articles[pos].source.name.isNullOrEmpty()) {
            "Unknown Source"
        } else {
            "Source: ".plus(newsResponse.articles[pos].source.name)
        }

        articlePublishedDate = if(newsResponse.articles[pos].publishedAt.isNullOrEmpty()) {
            "Unknown Published Date"
        } else {
            "Published Date: ".plus(newsResponse.articles[pos].publishedAt)
        }

        articleImage = if(newsResponse.articles[pos].urlToImage.isNullOrEmpty()) {
            "https://www.thevisateam.com/assets/uploads/placeholder-image.png"
        } else {
            newsResponse.articles[pos].urlToImage
        }

        articleViewMoreUrl = if(newsResponse.articles[pos].urlToImage.isNullOrEmpty()) {
            "https://baymard.com/ecommerce-search/benchmark/page-types/no-search-results-page"
        } else {
            newsResponse.articles[pos].url
        }

    }

    private fun initDataIntoLayout() {
        tvArticleTitle.text = articleTitle
        tvArticleDesc.text = articleDesc
        tvAuthorTitle.text = articleAuthor
        tvSource.text = articleSources
        tvArticleDate.text = articlePublishedDate
        Picasso.get().load(articleImage).into(ivArticle)
        btnViewArticle.text = viewMore
    }

    private fun likeArticleClickFunc(newsResponse: NewsResponse, pos: Int) {
        ivStar.setOnClickListener {
            (mContext as MainActivity).savedArticlesToRoomDb(articleTitle, articleDesc, articleAuthor,
                    articleSources,articlePublishedDate, articleImage, articleViewMoreUrl)
            val toast = Toast.makeText(mContext, "Article Has Been Saved For You!", Toast.LENGTH_LONG)
            toast.show()
        }
    }

    private fun viewMoreNewsImp() {
        btnViewArticle.setOnClickListener {
            (mContext as MainActivity).refreshNewsAdapter(articleViewMoreUrl)
        }
    }

}