package com.nicholasrutherford.distractme.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.ViewPagerAdapter
import com.nicholasrutherford.distractme.fragments.Home
import com.nicholasrutherford.distractme.fragments.dialogs.CustomTimerPopup
import com.nicholasrutherford.distractme.fragments.dialogs.TimerPopup
import java.util.*
import kotlin.concurrent.timerTask


class MainActivity : AppCompatActivity(),
Home.RefreshInterface{

    private lateinit var viewPager: ViewPager
    private val setTimerAlert = TimerPopup()
    private val setCustomTimerAlert = CustomTimerPopup()
    private val fm = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.view_pager)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,applicationContext)
        viewPager.adapter = viewPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        Timer().schedule(timerTask {
            println("Timer")
        }, 20000)
        showTimerAlert()
    }

    override fun refreshAdapterFragmentB() {
        viewPager.adapter?.notifyDataSetChanged()
        viewPager.currentItem = 0
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

}