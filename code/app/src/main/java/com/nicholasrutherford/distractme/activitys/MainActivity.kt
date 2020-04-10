package com.nicholasrutherford.distractme.activitys

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.lifecycle.whenCreated
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.ViewPagerAdapter
import com.nicholasrutherford.distractme.adapters.recyclers.News
import com.nicholasrutherford.distractme.fragments.Filter
import com.nicholasrutherford.distractme.fragments.Home
import com.nicholasrutherford.distractme.network.repositoryImp.NewsRepositoryImp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity(),
Home.RefreshInterface{

    private var home: Home? = null
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.view_pager)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,applicationContext)
        viewPager.adapter = viewPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    override fun refreshAdapterFragmentB() {
        viewPager.adapter?.notifyDataSetChanged()
        viewPager.currentItem = 0
    }

}