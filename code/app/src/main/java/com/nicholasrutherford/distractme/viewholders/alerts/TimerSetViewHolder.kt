package com.nicholasrutherford.distractme.viewholders.alerts

import android.content.Context
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.activitys.MainActivity
import com.nicholasrutherford.distractme.helpers.Typeface


class TimerSetViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    private val currentTimeSet: String = "Current Time Set: "
    private val sharedPreference by lazy { mContext.getSharedPreferences("NewsSharedPreferences", Context.MODE_PRIVATE) }
    private var editor = sharedPreference.edit()
    private var newTme: String = ""

    private var minutesActualValue: Int = 0
    private var secondsActualValue: Int = 0



    // string values timers
    private val defaultTimeOne: String = mContext.resources.getString(R.string.default_time_one)
    private val defaultTimeTwo: String = mContext.resources.getString(R.string.default_time_two)
    private val defaultTimeThree: String = mContext.resources.getString(R.string.default_time_three)
    private val defaultTimeFour: String = mContext.resources.getString(R.string.default_time_four)
    private val customTime: String = mContext.resources.getString(R.string.custom_time)

    // spinner setting timer array

    private val typeface = Typeface()
    private var tvLetsGetDistractedTitle: TextView = itemView.findViewById(R.id.tvLetsGetDistractedTitle)
    private var tvHowLongDesc: TextView = itemView.findViewById(R.id.tvHowLongDesc)
    private var tvCurrentTimeSet: TextView = itemView.findViewById(R.id.tvCurrentTimeSet)
    private var spSettingTimer: Spinner = itemView.findViewById(R.id.spSettingTimer)
    private var tvConfirmTime: TextView = itemView.findViewById(R.id.tvConfirmTime)
    private var tvDontShowMeAgain: TextView = itemView.findViewById(R.id.tvDontShowMeAgain)
    private var ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)

    fun main() {
        setTypeface()
        setMinutesAndSecondsValue()
        setUpSettingTimerItems()
        deleteClickListener()
        confirmClickListener()
        doNotRemindMeListener()
        setSharedPrefsValuesBackToNull()
    }

    private fun setTypeface() {
        typeface.setTypefaceForHeaderBold(tvLetsGetDistractedTitle, mContext)
        typeface.setTypefaceForBodyRegular(tvHowLongDesc, mContext)
        typeface.setTypefaceForSubHeaderBold(tvCurrentTimeSet, mContext)
        typeface.setTypefaceForBodyRegularBold(tvConfirmTime, mContext)
        typeface.setTypefaceForBodyRegularBold(tvDontShowMeAgain, mContext)
    }

    private fun setMinutesAndSecondsValue() {
        if(sharedPreference.getString("minutesSet", null) != null && sharedPreference.getString("secondsSet",null) != null) {
            minutesActualValue = sharedPreference.getString("minutesSet",null)!!.toInt()
            secondsActualValue = sharedPreference.getString("secondsSet", null)!!.toInt()

            if(minutesActualValue <= 9) {
                newTme = "0$minutesActualValue:$secondsActualValue"
            }
            if(secondsActualValue <= 9) {
                newTme = "$minutesActualValue:0$secondsActualValue"
            }
            if(minutesActualValue <= 9 && secondsActualValue <= 9) {
                newTme = "0$minutesActualValue:0$secondsActualValue"
            }
            if(minutesActualValue > 9 && secondsActualValue > 9) {
                newTme = "$minutesActualValue:$secondsActualValue"
            }

        } else {
            minutesActualValue = 0
            secondsActualValue = 0
        }
    }

    private fun setUpSettingTimerItems() {
        if(newTme != "") {
            val spinnerSettingTimerItems = arrayOf(newTme,defaultTimeOne, defaultTimeTwo, defaultTimeThree, defaultTimeFour, customTime)
            addItemsToSettingTimerSpinner(spinnerSettingTimerItems)
        } else {
            val spinnerSettingTimerItems = arrayOf(defaultTimeOne, defaultTimeTwo, defaultTimeThree, defaultTimeFour, customTime)
            addItemsToSettingTimerSpinner(spinnerSettingTimerItems)
        }

    }

    private fun addItemsToSettingTimerSpinner(spinnerSettingTimerItems: Array<String>) {
        val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, spinnerSettingTimerItems)
        spSettingTimer.adapter = adapter

        spSettingTimer.onItemSelectedListener = object :  AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("Nothing was selected")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when {
                    parent?.selectedItem.toString() == newTme -> {
                        tvCurrentTimeSet.text = currentTimeSet.plus(newTme)
                    }
                    parent?.selectedItem.toString() == defaultTimeOne -> {
                        tvCurrentTimeSet.text = currentTimeSet.plus(defaultTimeOne)
                    }
                    parent?.selectedItem.toString() == defaultTimeTwo -> {
                        tvCurrentTimeSet.text = currentTimeSet.plus(defaultTimeTwo)
                    }
                    parent?.selectedItem.toString() == defaultTimeThree -> {
                        tvCurrentTimeSet.text = currentTimeSet.plus(defaultTimeThree)
                    }
                    parent?.selectedItem.toString() == defaultTimeFour -> {
                        tvCurrentTimeSet.text = currentTimeSet.plus(defaultTimeFour)
                    }
                    parent?.selectedItem.toString() == customTime -> {
                        (mContext as MainActivity).dismissTimerAlert()
                        mContext.showCustomTimerAlert()

                    }
                }
            }

        }
    }

    private fun deleteClickListener() {
        ivDelete.setOnClickListener {
            (mContext as MainActivity).dismissTimerAlert()
        }
    }

    private fun confirmClickListener() {
        tvConfirmTime.setOnClickListener {
            (mContext as MainActivity).dismissTimerAlert()
        }
    }

    private fun doNotRemindMeListener() {
        tvDontShowMeAgain.setOnClickListener {
            (mContext as MainActivity).dismissTimerAlert()
        }
    }

    private fun setSharedPrefsValuesBackToNull() {
        editor.putString("minutesSet", null)
        editor.putString("secondsSet", null)
        editor.apply()
    }
}