package com.nicholasrutherford.distractme.fragments.dialogs

import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.recyclers.alerts.TimerCustomAdapt
import java.util.*
import kotlin.collections.ArrayList

class CustomTimerPopup: DialogFragment() {

    private var minutesList: MutableList<String> = ArrayList()
    private var secondsList: MutableList<String> = ArrayList()

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
        clearArrayLists()
        setUpTimeRecycler()
        initAdapter()
    }

    private fun clearArrayLists() {
        minutesList.clear()
        secondsList.clear()
    }

    private fun setUpTimeRecycler() {
        rvCustomTimer!!.itemAnimator = null
        rvCustomTimer!!.layoutManager = LinearLayoutManager(context!!)
    }

    private fun initArrayList() {
        for (m in 0..15) minutesList.add(m.toString())
        for(s in 0.. 60) secondsList.add(s.toString())
    }

    private fun initAdapter() {
        initArrayList()
        customTimerAdaptAdapter = TimerCustomAdapt(context!!,minutesList,secondsList)
        rvCustomTimer!!.adapter = customTimerAdaptAdapter
    }

    override fun onResume() {
        super.onResume()
        Objects.requireNonNull(dialog)
                ?.setOnKeyListener { _: DialogInterface?, keyCode: Int, event: KeyEvent ->
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        dialog!!.dismiss()
                        if (event.action != KeyEvent.ACTION_DOWN) return@setOnKeyListener true else { //Hide your keyboard here
                            return@setOnKeyListener true // pretend we've processed it
                        }
                    } else return@setOnKeyListener false // pass on to be processed as normal
                }
    }
}