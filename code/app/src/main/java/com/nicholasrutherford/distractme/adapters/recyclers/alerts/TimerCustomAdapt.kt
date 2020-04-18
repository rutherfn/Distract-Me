package com.nicholasrutherford.distractme.adapters.recyclers.alerts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.viewholders.alerts.TimerCustomSetViewHolder

class TimerCustomAdapt(private val mContext: Context, private val minutesList: MutableList<String>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dialog_set_custom_time, parent, false )
        return TimerCustomSetViewHolder(itemView, mContext)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val alertCustomTimerSetViewHolder = holder as TimerCustomSetViewHolder
        alertCustomTimerSetViewHolder.main(minutesList)
    }

}