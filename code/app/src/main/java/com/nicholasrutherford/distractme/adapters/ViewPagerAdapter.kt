package com.nicholasrutherford.distractme.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.fragments.*
import java.util.HashMap

class ViewPagerAdapter (private val mFragmentManager: FragmentManager, private val mContext: Context) : FragmentPagerAdapter(mFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragmentTags: MutableMap<Int, String?>
    private val tabTitles = arrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3,
            R.string.tab_text_4
    )

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> getHomeInstance()
            1 -> getWebInstance()
            2 -> getFilterInstance()
            3 -> getSavedInstance()
            else -> getHomeInstance()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(tabTitles[position])
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    private fun getHomeInstance() = Home()

    private fun getWebInstance() = Web()

    private fun getFilterInstance() = Filter()

    private fun getSavedInstance() = Saved()

    init {
        mFragmentTags = HashMap()
    }

}