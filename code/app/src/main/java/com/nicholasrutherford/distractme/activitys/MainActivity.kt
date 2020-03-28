package com.nicholasrutherford.distractme.activitys

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,applicationContext)
        viewPager.adapter = viewPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

}