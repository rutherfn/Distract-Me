package com.nicholasrutherford.distractme.adapters.recyclers.alerts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.viewholders.alerts.TimerExtendViewHolder

class TimerExtendAdapt(private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dialog_times_up, parent, false)
        return TimerExtendViewHolder(itemView, mContext)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val alertTimerExtendViewHolder = holder as TimerExtendViewHolder
        alertTimerExtendViewHolder.main()
    }

}