package com.nicholasrutherford.distractme.activitys

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.nicholasrutherford.distractme.PrefUtil
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.ViewPagerAdapter
import com.nicholasrutherford.distractme.fragments.Home
import com.nicholasrutherford.distractme.fragments.dialogs.CustomTimerPopup
import com.nicholasrutherford.distractme.fragments.dialogs.TimerPopup
import java.util.*
import kotlin.concurrent.timerTask


class MainActivity : AppCompatActivity(),
Home.RefreshInterface{

    enum class TimerState{
        Stopped,Pause, Running
    }

    private lateinit var timer: CountDownTimer
    private var secondsRemaining: Long = 0
    private var timerState = TimerState.Stopped
    private lateinit var currentTimeLeft: TextView

    private var timerLengthSeconds: Long = 0

    private lateinit var viewPager: ViewPager
    private val setTimerAlert = TimerPopup()
    private val setCustomTimerAlert = CustomTimerPopup()
    private val fm = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.view_pager)
        currentTimeLeft = findViewById(R.id.tvCurrentTimeLeft)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,applicationContext)
        viewPager.adapter = viewPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        Timer().schedule(timerTask {
            println("Timer")
        }, 20000)
        initTimer()
        startTimer()
        timerState = TimerState.Running
    }

    override fun onResume() {
        super.onResume()

        initTimer()
    }

    override fun refreshAdapterFragmentB() {
        viewPager.adapter?.notifyDataSetChanged()
        viewPager.currentItem = 0
    }

    override fun onPause() {
        super.onPause()

        if(timerState == TimerState.Running) {
            timer.cancel()
        }
        else if(timerState == TimerState.Pause) {

        }

        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, this)
        PrefUtil.setSecondsRemaining(secondsRemaining, this)
        PrefUtil.setTimerState(timerState, this)
    }

    private fun initTimer() {
        timerState = PrefUtil.getTimerState(this)

        if(timerState == TimerState.Stopped)
            setNewTimerLength()
        else
            setPreviousTimerLength()

        secondsRemaining = if(timerState == TimerState.Running || timerState == TimerState.Pause)
            PrefUtil.getSecondsRemaining(this)
        else
            timerLengthSeconds

        if(timerState == TimerState.Running) {
            startTimer()
        }
        updateCountDownUI()
        }

    private fun setNewTimerLength(){
        val lengthInMinutes = PrefUtil.getTimerLength(this)
        timerLengthSeconds = (lengthInMinutes * 60L)
    }

    private fun setPreviousTimerLength(){
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(this)
    }

    fun showTimerAlert() {
        setTimerAlert.show(fm, "set timer alert")
    }

    fun showCustomTimerAlert() {
        setCustomTimerAlert.show(fm, "set custom timer alert")
    }

    fun dismissTimerAlert() {
        setTimerAlert.dismiss()
    }

    fun dismissCustomTimerAlert() {
        setCustomTimerAlert.dismiss()
    }

    fun onTimerFinished() {
        timerState = TimerState.Stopped

       // setNewTimerLength()

        PrefUtil.setSecondsRemaining(timerLengthSeconds, this)
        secondsRemaining = timerLengthSeconds

        updateCountDownUI()
    }

    private fun startTimer() {
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {

            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountDownUI()
            }

        }.start()
    }

    @SuppressLint("SetTextI18n")
    private fun updateCountDownUI() {
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        currentTimeLeft.text = "Current Time Remaining: $minutesUntilFinished:${
        if(secondsStr.length == 2) secondsStr
        else "0$secondsStr"}"
    }

    private fun updateButtons(startBtn: Button, pauseBtn: Button, stopButton: Button){
        when (timerState) {
            TimerState.Running ->{
                startBtn.isEnabled = false
                pauseBtn.isEnabled = true
                stopButton.isEnabled = true
            }
            TimerState.Stopped -> {
                startBtn.isEnabled = true
                pauseBtn.isEnabled = false
                stopButton.isEnabled = false
            }
            TimerState.Pause -> {
                startBtn.isEnabled = true
                pauseBtn.isEnabled = false
                stopButton.isEnabled = true
            }
        }
    }

//    fun playTimer(updateTextView: TextView, startBtn: Button, pauseBtn: Button, stopButton: Button) {
//        initTimer(updateTextView, startBtn, pauseBtn, stopButton)
//        startTimer(updateTextView)
//        timerState = TimerState.Running
//        updateButtons(startBtn,pauseBtn, stopButton)
//    }
//
//    fun pauseTimer(startBtn: Button, pauseBtn: Button, stopButton: Button) {
//        timer.cancel()
//        timerState = TimerState.Pause
//        updateButtons(startBtn,pauseBtn, stopButton)
//    }
//
//    fun stopTimer(updateTextView: TextView) {
//        timer.cancel()
//        onTimerFinished(updateTextView)
//    }

}