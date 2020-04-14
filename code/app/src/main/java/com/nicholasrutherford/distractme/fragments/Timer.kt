package com.nicholasrutherford.distractme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.recyclers.TimerDistract

class Timer: Fragment() {

    private var mView: View? = null
    private var rvTimer: RecyclerView? = null
    private var timerDistractAdapter: TimerDistract? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_timer, container, false)
        rvTimer = mView!!.findViewById(R.id.rvTimer)
        main()
        return mView
    }

    private fun main() {
        setUpArticleAdapter()
        initAdapter()
    }

    private fun setUpArticleAdapter() {
        rvTimer!!.itemAnimator = null
        rvTimer!!.layoutManager = LinearLayoutManager(context!!)
    }

    private fun initAdapter() {
        timerDistractAdapter = TimerDistract(context!!)
        rvTimer!!.adapter = timerDistractAdapter
    }

}