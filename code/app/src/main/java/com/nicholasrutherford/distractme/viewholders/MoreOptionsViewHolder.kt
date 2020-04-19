package com.nicholasrutherford.distractme.viewholders

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.activitys.MainActivity
import com.nicholasrutherford.distractme.helpers.Typeface

class MoreOptionsViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    private val typeface = Typeface()
    private var tvRowOptions: TextView = itemView.findViewById(R.id.tvRowOptions)
    private var moreCos: ConstraintLayout = itemView.findViewById(R.id.csMoreContent)

    fun main(pos: Int, moreOptions: ArrayList<String>) {
        typeface.setTypefaceForBodyRegularBold(tvRowOptions, mContext)
        tvRowOptions.text = moreOptions[pos]
        tvRowOptions.setOnClickListener {
            if(pos == 0) {
               // (mContext as MainActivity).testSomething()
            }
        }
    }
}
