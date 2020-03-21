package com.nicholasrutherford.distractme.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nicholasrutherford.distractme.fragments.Home
import java.util.HashMap

class ViewPager (private val mFragmentManager: FragmentManager) : FragmentPagerAdapter(mFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragmentTags: MutableMap<Int, String?> = TODO()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> getHomeInstance()
            else -> getHomeInstance()
        }
    }

    override fun getCount(): Int {
        return 1
    }

    private fun getHomeInstance() = Home()

    override fun instantiateItem(container: ViewGroup, position: Int): Any { // init a fragment.
        val `object` = super.instantiateItem(container, position)

        if (`object` is Fragment) {
            val tag = `object`.tag
            mFragmentTags[position] = tag
        }
        return `object`
    }

    fun getFragment(position: Int): Fragment? { // get fragment by tag
        var fragment: Fragment? = null
        val tag = mFragmentTags[position]

        if (tag != null) {
            fragment = mFragmentManager.findFragmentByTag(tag)
        }
        return fragment
    }

    init {
        mFragmentTags = HashMap()
    }

}