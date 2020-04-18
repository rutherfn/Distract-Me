package com.nicholasrutherford.distractme.viewholders

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.activitys.MainActivity

class TimerDistractViewHolder(itemView: View, private val mContext: Context) :
    RecyclerView.ViewHolder(itemView) {

    private var tvTimer: TextView = itemView.findViewById(R.id.tvTimer)
    private var btnEdit: Button = itemView.findViewById(R.id.btnEdit)
    private var btnPlay: Button = itemView.findViewById(R.id.btnPlay)
    private var btnPause: Button = itemView.findViewById(R.id.btnPause)
    private var btnStop: Button = itemView.findViewById(R.id.btnStop)


    private var secondsRemaining = 0L


    fun main() {
        playTimerClickListenerFunc()
    }

    private fun playTimerClickListenerFunc() {
        btnPlay.setOnClickListener {
            btnPlay.isEnabled = true
         //   (mContext as MainActivity).playTimer(tvTimer, btnPlay, btnPause, btnStop)
        }
    }

    private fun pauseTimerClickListenerFunc() {
        btnPause.setOnClickListener {
          //  (mContext as MainActivity).pauseTimer(btnPlay, btnPause, btnStop)
        }
    }

    private fun stopTimerClickListenerFuc() {
        btnStop.setOnClickListener {
           // (mContext as MainActivity).stopTimer(tvTimer)
        }
    }

    private fun editTimerClickListenerFunc() {
        btnEdit.setOnClickListener {

        }
    }
}