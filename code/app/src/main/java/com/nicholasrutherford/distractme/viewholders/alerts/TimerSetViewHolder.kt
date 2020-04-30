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

    // string values timers
    private val defaultTimeOne: String = mContext.resources.getString(R.string.default_time_one)
    private val defaultTimeTwo: String = mContext.resources.getString(R.string.default_time_two)
    private val defaultTimeThree: String = mContext.resources.getString(R.string.default_time_three)
    private val defaultTimeFour: String = mContext.resources.getString(R.string.default_time_four)
    private val customTime: String = mContext.resources.getString(R.string.custom_time)

    // values in minutes for setting actual timer
    private var customTimeSetValue: Long = 0
    private val defaultTimeOneValue: Long = 1000
    private val defaultTimeTwoValue: Long = 2000
    private val defaultTimeValueThree: Long = 5000
    private val defaultTimeValueFour: Long = 10000
    private var actualTimeValueSet: Long = 0

    private val typeface = Typeface()
    private var tvLetsGetDistractedTitle: TextView = itemView.findViewById(R.id.tvLetsGetDistractedTitle)
    private var tvHowLongDesc: TextView = itemView.findViewById(R.id.tvHowLongDesc)
    private var tvCurrentTimeSet: TextView = itemView.findViewById(R.id.tvCurrentTimeSet)
    private var spSettingTimer: Spinner = itemView.findViewById(R.id.spSettingTimer)
    private var tvConfirmTime: TextView = itemView.findViewById(R.id.tvConfirmTime)
    private var ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)

    fun main() {
        setTypeface()
        setMinutesAndSecondsValue()
        setUpSettingTimerItems()
        deleteClickListener()
        confirmClickListener()
        setSharedPrefsValuesBackToNull()
    }

    private fun setTypeface() {
        typeface.setTypefaceForHeaderBold(tvLetsGetDistractedTitle, mContext)
        typeface.setTypefaceForBodyRegular(tvHowLongDesc, mContext)
        typeface.setTypefaceForSubHeaderBold(tvCurrentTimeSet, mContext)
        typeface.setTypefaceForBodyRegularBold(tvConfirmTime, mContext)
    }

    private fun setMinutesAndSecondsValue() {
        if(sharedPreference.getString("minutesSet", null) != null) {
            minutesActualValue = sharedPreference.getString("minutesSet",null)!!.toInt()

            setCustomTimeValue()

            if(minutesActualValue <= 9) {
                newTme = "0$minutesActualValue:00"
            }
            if(minutesActualValue > 9 ) {
                newTme = "$minutesActualValue:00"
            }

        } else {
            minutesActualValue = 0
            actualTimeValueSet = 0
        }
    }

    private fun setCustomTimeValue() {
        when (minutesActualValue) {
            1 -> {
                customTimeSetValue = 1000
            }
            2 -> {
                customTimeSetValue = 2000
            }
            3 -> {
                customTimeSetValue = 3000
            }
            4 -> {
                customTimeSetValue = 4000
            }
            5 -> {
                customTimeSetValue = 5000
            }
            6 -> {
                customTimeSetValue = 6000
            }
            7 -> {
                customTimeSetValue = 7000
            }
            8 -> {
                customTimeSetValue = 8000
            }
            9 -> {
                customTimeSetValue = 9000
            }
            10 -> {
                customTimeSetValue = 1000
            }
            11 -> {
                customTimeSetValue = 11000
            }
            12 -> {
                customTimeSetValue = 12000
            }
            13 -> {
                customTimeSetValue = 13000
            }
            14 -> {
                customTimeSetValue = 14000
            }
            15 -> {
                customTimeSetValue = 15000
            }
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
                        actualTimeValueSet = customTimeSetValue
                    }
                    parent?.selectedItem.toString() == defaultTimeOne -> {
                        tvCurrentTimeSet.text = currentTimeSet.plus(defaultTimeOne)
                        actualTimeValueSet = defaultTimeOneValue
                    }
                    parent?.selectedItem.toString() == defaultTimeTwo -> {
                        tvCurrentTimeSet.text = currentTimeSet.plus(defaultTimeTwo)
                        actualTimeValueSet = defaultTimeTwoValue
                    }
                    parent?.selectedItem.toString() == defaultTimeThree -> {
                        tvCurrentTimeSet.text = currentTimeSet.plus(defaultTimeThree)
                        actualTimeValueSet = defaultTimeValueThree
                    }
                    parent?.selectedItem.toString() == defaultTimeFour -> {
                        tvCurrentTimeSet.text = currentTimeSet.plus(defaultTimeFour)
                        actualTimeValueSet = defaultTimeValueFour
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
            mContext.onTimerFinished()
            mContext.startTimeForTimer(actualTimeValueSet)
        }
    }

    private fun setSharedPrefsValuesBackToNull() {
        editor.putString("minutesSet", null)
        editor.putString("secondsSet", null)
        editor.apply()
    }

}