package com.nicholasrutherford.distractme.fragments.dialogs

import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.recyclers.alerts.TimerSet
import java.util.*

class SetTimer : DialogFragment() {

    private var mView: View? = null
    private var rvSetTimer: RecyclerView? = null
    private var timerSetAdapter: TimerSet? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_dialog_set_timer, container, false)
        rvSetTimer = mView!!.findViewById(R.id.rvSetTimer)
        main()
        return mView
    }

    private fun main() {
        setUpTimerRecycler()
        initAdapter()
    }

    private fun setUpTimerRecycler() {
        rvSetTimer!!.itemAnimator = null
        rvSetTimer!!.layoutManager = LinearLayoutManager(context!!)
    }

    private fun initAdapter() {
        timerSetAdapter = TimerSet(context!!)
        rvSetTimer!!.adapter = timerSetAdapter
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