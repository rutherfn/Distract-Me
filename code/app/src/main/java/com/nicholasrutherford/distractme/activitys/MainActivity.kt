package com.nicholasrutherford.distractme.activitys

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.ViewPagerAdapter
import com.nicholasrutherford.distractme.fragments.Home
import com.nicholasrutherford.distractme.fragments.dialogs.SetTimer
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity(),
Home.RefreshInterface{

    private lateinit var viewPager: ViewPager
    private val setTimerAlert = SetTimer()
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
        setTimerAlert.show(fm, "set timer alert")
    }

    override fun refreshAdapterFragmentB() {
        viewPager.adapter?.notifyDataSetChanged()
        viewPager.currentItem = 0
    }
}