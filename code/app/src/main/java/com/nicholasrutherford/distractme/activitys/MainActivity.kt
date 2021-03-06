package com.nicholasrutherford.distractme.activitys

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.ViewPagerAdapter
import com.nicholasrutherford.distractme.data.room.SavedArticlesDatabase
import com.nicholasrutherford.distractme.data.room.SavedArticlesEntity
import com.nicholasrutherford.distractme.fragments.Home
import com.nicholasrutherford.distractme.fragments.dialogs.CustomTimerPopup
import com.nicholasrutherford.distractme.fragments.dialogs.TimerExtendPopup
import com.nicholasrutherford.distractme.fragments.dialogs.TimerPopup
import com.nicholasrutherford.distractme.helpers.tasks.DatabaseTask
import com.nicholasrutherford.distractme.helpers.tasks.NetworkTask
import com.nicholasrutherford.distractme.helpers.PrefUtil
import com.nicholasrutherford.distractme.helpers.Typeface
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Nick R.
 */

class MainActivity : AppCompatActivity(),
Home.RefreshInterface {

    enum class TimerState{
        Stopped, Pause, Running
    }

    val savedArticleList = ArrayList<SavedArticlesEntity>()
    private var typeface = Typeface()
    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 0
    private var timerState = TimerState.Stopped
    private var secondsRemaining: Long = 0
    private var currentTimeState: Long = 0
    private lateinit var currentTimeLeft: TextView
    private lateinit var viewPager: ViewPager
    private val setTimerAlert = TimerPopup()
    private val setCustomTimerAlert = CustomTimerPopup()
    private val extendTimerAlert = TimerExtendPopup()
    private val fm = supportFragmentManager
    private var db: SavedArticlesDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.view_pager)
        currentTimeLeft = findViewById(R.id.tvCurrentTimeLeft)
        typeface.setTypefaceForBodyRegular(currentTimeLeft, applicationContext)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,applicationContext)
        viewPager.adapter = viewPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        setSharedPrefsBackToEmpty()
        showTimerAlert()
        db = SavedArticlesDatabase(this)
        DatabaseTask(db, savedArticleList).execute()
}

    fun savedArticlesToRoomDb(title: String, desc: String, author: String, sourceName: String, publishedAt: String, imageUrl: String, url: String) {
        GlobalScope.launch {
            db?.savedArticleDao()?.insertAll(SavedArticlesEntity(0, title, desc, author, sourceName, publishedAt, imageUrl, url))
        }
        DatabaseTask(db, savedArticleList).execute()
        networkTaskProgress()
    }

    fun removeArticleInRoomDb(savedArticles: SavedArticlesEntity) {
        GlobalScope.launch {
            db?.savedArticleDao()?.delete(savedArticles)
        }
        DatabaseTask(db, savedArticleList).execute()
        networkTaskProgress()
        viewPager.adapter?.notifyDataSetChanged()
    }

    private fun setSharedPrefsBackToEmpty() {
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val editor = sharedPreference.edit()
        editor.putString("webUrl", "")
        editor.apply()
    }

    override fun onPause() {
        super.onPause()
        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, this)
        PrefUtil.setSecondsRemaining(secondsRemaining, this)
        PrefUtil.setTimerState(timerState, this)
    }

    override fun refreshHomeAdapter() {
        viewPager.adapter?.notifyDataSetChanged()
        viewPager.currentItem = 0
    }

    fun refreshNewsAdapter(webUrl: String) {
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val editor = sharedPreference.edit()
        editor.putString("webUrl",webUrl)
        editor.apply()
        viewPager.adapter?.notifyDataSetChanged()
        viewPager.currentItem = 1
    }

    fun networkTaskProgress() {
        NetworkTask(this).execute()
    }

    fun showTimerAlert() {
        setTimerAlert.show(fm, "set timer alert")
    }

    fun showExtendTimerAlert() {
        extendTimerAlert.show(fm, "extend timer alert")
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

    fun dismissExtendTimerAlert() {
        extendTimerAlert.dismiss()
    }

    private fun initTimer() {
        timerState = PrefUtil.getTimerState(this)

        if (timerState == TimerState.Stopped) {
            setNewTimerLength()
        } else {
            setPreviousTimerLength()
        }

        secondsRemaining = if (timerState == TimerState.Running || timerState == TimerState.Pause) {
            PrefUtil.getSecondsRemaining(this)
        } else {
            timerLengthSeconds
        }

            startTimer()

        updateCountDownUI()

    }

    private fun setNewTimerLength(){
        val lengthInMinutes = PrefUtil.getTimerLength(this)
        timerLengthSeconds = (lengthInMinutes * 60L)
    }

    private fun setPreviousTimerLength(){
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(this)
    }

    fun onTimerFinished() {
        currentTimeLeft.visibility = View.GONE
        timerState = TimerState.Stopped

        setNewTimerLength()

        PrefUtil.setSecondsRemaining(timerLengthSeconds, this)
        secondsRemaining = timerLengthSeconds

        updateCountDownUI()
    }

    private fun startTimer() {
        currentTimeLeft.visibility = View.VISIBLE
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * currentTimeState, 1000) {

            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountDownUI()
                if(secondsRemaining == 0L) {
                    showExtendTimerAlert()
                }
            }
        }.start()
    }

    @SuppressLint("SetTextI18n")
    private fun updateCountDownUI() {
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        currentTimeLeft.text = "Current Time Remaining: $minutesUntilFinished:${if (secondsStr.length == 2) secondsStr else "0" + secondsStr}"
    }

    fun startTimeForTimer(time: Long) {
        currentTimeState = time
        timerState =  TimerState.Running
        initTimer()
    }

    override fun onBackPressed() {
        if(viewPager.currentItem != 0) {
            viewPager.setCurrentItem(0,false)
        } else {
            super.onBackPressed()
        }
    }

}