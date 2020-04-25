package com.nicholasrutherford.distractme.viewholders.alerts

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.activitys.MainActivity
import com.nicholasrutherford.distractme.helpers.Typeface

class TimerExtendViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    private val typeface = Typeface()
    private var tvTimesUp: TextView = itemView.findViewById(R.id.tvTimesUp)
    private var tvContinueOrNot: TextView = itemView.findViewById(R.id.tvContinueOrNot)
    private var tvYesExtend: TextView = itemView.findViewById(R.id.tvYesExtend)
    private var tvNoExtend: TextView = itemView.findViewById(R.id.tvNoExtend)
    private var ivExtendDelete: ImageView = itemView.findViewById(R.id.ivExtendDelete)

    fun main() {
        setTypeface()
        closeClickListener()
        extendTimeClickListener()
        closeTimeClickListener()
    }

    private fun setTypeface() {
        typeface.setTypefaceForHeaderBold(tvTimesUp, mContext)
        typeface.setTypefaceForBodyRegular(tvContinueOrNot, mContext)
        typeface.setTypefaceForSubHeaderRegular(tvYesExtend, mContext)
        typeface.setTypefaceForSubHeaderRegular(tvNoExtend, mContext)
    }

    private fun dismissApp() {
        (mContext as MainActivity).dismissExtendTimerAlert()
        mContext.finish()
    }

    private fun closeClickListener() {
        ivExtendDelete.setOnClickListener {
            dismissApp()
        }
    }

    private fun extendTimeClickListener() {
        tvYesExtend.setOnClickListener {
            (mContext as MainActivity).dismissExtendTimerAlert()
            mContext.showTimerAlert()
        }
    }

    private fun closeTimeClickListener() {
        tvNoExtend.setOnClickListener {
            dismissApp()
        }
    }
}