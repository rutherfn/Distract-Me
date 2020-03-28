package com.nicholasrutherford.distractme.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.fragments.Home
import java.util.HashMap

class ViewPagerAdapter (private val mFragmentManager: FragmentManager, private val mContext: Context) : FragmentPagerAdapter(mFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragmentTags: MutableMap<Int, String?>
    private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_3,
        R.string.tab_text_4,
        R.string.tab_text_5
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> getHomeInstance()
            else -> getHomeInstance()
        }
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
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