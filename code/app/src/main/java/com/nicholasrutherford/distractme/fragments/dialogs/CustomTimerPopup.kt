package com.nicholasrutherford.distractme.fragments.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.recyclers.alerts.TimerCustomAdapt
import kotlin.collections.ArrayList

class CustomTimerPopup: DialogFragment() {

    private var minutesList: MutableList<String> = ArrayList()
    private var mView: View? = null
    private var rvCustomTimer: RecyclerView? = null
    private var customTimerAdaptAdapter: TimerCustomAdapt? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView =  inflater.inflate(R.layout.fragment_dialog_set_custom_timer, container, false)
        rvCustomTimer = mView!!.findViewById(R.id.rvCustomTimer)
        main()
        return mView
    }

    private fun main() {
        minutesList.clear()

        setUpTimeRecycler()
        initAdapter()
    }

    private fun setUpTimeRecycler() {
        rvCustomTimer!!.itemAnimator = null
        rvCustomTimer!!.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initArrayList() {
        for (m in 1..15) minutesList.add(m.toString())
    }

    private fun initAdapter() {
        initArrayList()
        customTimerAdaptAdapter = TimerCustomAdapt(requireContext(),minutesList)
        rvCustomTimer!!.adapter = customTimerAdaptAdapter
    }

}