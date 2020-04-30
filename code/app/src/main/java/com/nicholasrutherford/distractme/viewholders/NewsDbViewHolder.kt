package com.nicholasrutherford.distractme.viewholders

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.activitys.MainActivity
import com.nicholasrutherford.distractme.data.room.SavedArticlesEntity
import com.nicholasrutherford.distractme.helpers.Typeface
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class NewsDbViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    private val typeface = Typeface()
    private var ivArticle: CircleImageView = itemView.findViewById(R.id.ivArticle)
    private var tvArticleTitle: TextView = itemView.findViewById(R.id.tvArticleTitle)
    private var tvArticleDesc: TextView = itemView.findViewById(R.id.tvArticleDesc)
    private var tvAuthorTitle: TextView = itemView.findViewById(R.id.tvAuthorTitle)
    private var btnViewArticle: Button = itemView.findViewById(R.id.btnViewArticle)
    private var ivDeleteArticle: ImageView = itemView.findViewById(R.id.ivDeleteArticle)
    private var tvSource: TextView = itemView.findViewById(R.id.tvSource)
    private var tvArticleDate: TextView = itemView.findViewById(R.id.tvArticleDate)

    private var viewMore: String = "View More"

    fun main(savedArticleList: List<SavedArticlesEntity>, pos: Int) {
        setTypeface()
        setData(savedArticleList, pos)
        viewMoreNewsImp(savedArticleList, pos)
        removeArticleFromSaved(savedArticleList, pos)
    }

    private fun setTypeface() {
        typeface.setTypefaceForHeaderBold(tvArticleTitle,mContext)
        typeface.setTypefaceForBodyRegular(tvArticleDesc,mContext)
        typeface.setTypefaceForBodyRegularBold(tvAuthorTitle, mContext)
        typeface.setTypefaceForSubHeaderBold(tvSource,mContext)
        typeface.setTypefaceForSubHeaderBold(tvArticleDate,mContext)
    }

    private fun setData(savedArticleList: List<SavedArticlesEntity>, pos: Int) {
        Picasso.get().load(savedArticleList[pos].imageUrl).into(ivArticle)
        tvArticleTitle.text = savedArticleList[pos].title
        tvArticleDesc.text = savedArticleList[pos].description
        tvAuthorTitle.text = savedArticleList[pos].author
        btnViewArticle.text = viewMore
        tvSource.text = savedArticleList[pos].sourceName
        tvArticleDate.text = savedArticleList[pos].publishedAt
    }

    private fun viewMoreNewsImp(savedArticleList: List<SavedArticlesEntity>, pos: Int) {
        btnViewArticle.setOnClickListener {
            (mContext as MainActivity).refreshNewsAdapter(savedArticleList[pos].url)
        }
    }

    private fun removeArticleFromSaved(savedArticleList: List<SavedArticlesEntity>, pos: Int) {
        ivDeleteArticle.setOnClickListener {
            (mContext as MainActivity).removeArticleInRoomDb(savedArticleList[pos])
        }
    }

}