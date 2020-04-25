package com.nicholasrutherford.distractme.viewholders.alerts

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.activitys.MainActivity
import com.nicholasrutherford.distractme.helpers.Typeface

class TimerCustomSetViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    private val typeface = Typeface()
    private var ivDeleteCustom: ImageView = itemView.findViewById(R.id.ivDeleteCustom)
    private var tvMinutes: TextView = itemView.findViewById(R.id.tvMinutes)
    private var spMinutes: Spinner = itemView.findViewById(R.id.spMinutes)
    private var btnConfirmCustomTime: Button = itemView.findViewById(R.id.btnConfirmCustomTime)
    private val sharedPreference by lazy { mContext.getSharedPreferences("NewsSharedPreferences", Context.MODE_PRIVATE) }
    private var editor = sharedPreference.edit()
    private var minutes: String = ""

    fun main(minutesList: MutableList<String>) {
        setTypeface()
        addItemsToMinutesSpinner(minutesList)
        confirmClickListener()
        closeClickListener()
    }

    private fun setTypeface() {
        typeface.setTypefaceForHeaderBold(tvMinutes, mContext)
        typeface.setTypefaceForBodyRegular(btnConfirmCustomTime, mContext)
    }

    private fun addItemsToMinutesSpinner(minutesList: MutableList<String>) {
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, minutesList)
        spMinutes.adapter = adapter

        spMinutes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing was selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                minutes = parent?.selectedItem.toString()
            }

        }
    }

    private fun showTimeCantBeSetToZeroAlert() {
        (mContext as MainActivity).dismissCustomTimerAlert()
            val dialogBuilder = AlertDialog.Builder(mContext)

            dialogBuilder.setMessage("Sorry please add a non zero time. Please press OK to try again.")
                    .setCancelable(false)
                    .setPositiveButton("OK") { dialog, _ -> dialog.cancel()
                        mContext.showCustomTimerAlert()
                    }

        val alert = dialogBuilder.create()
            alert.setTitle("No Custom Time Added")
            alert.show()
    }

    private fun confirmClickListener() {
        btnConfirmCustomTime.setOnClickListener {
            if(minutes == "0") {
                showTimeCantBeSetToZeroAlert()
            } else {
                editor.putString("minutesSet", minutes)
                editor.apply()
                (mContext as MainActivity).dismissCustomTimerAlert()
                mContext.showTimerAlert()
            }
        }
    }

    private fun closeClickListener() {
        ivDeleteCustom.setOnClickListener {
            (mContext as MainActivity).dismissCustomTimerAlert()
            mContext.showTimerAlert()
        }
    }

}