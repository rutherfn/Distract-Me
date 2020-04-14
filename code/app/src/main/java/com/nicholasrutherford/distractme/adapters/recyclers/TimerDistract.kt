package com.nicholasrutherford.distractme.adapters.recyclers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.viewholders.TimerDistractViewHolder

class TimerDistract(private val mContext: Context) :  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.timer_main, parent,false)
        return TimerDistractViewHolder(itemView, mContext)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val timerDistractViewHolder = holder as TimerDistractViewHolder
        timerDistractViewHolder.main()
    }


}