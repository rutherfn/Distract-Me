package com.nicholasrutherford.distractme.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.adapters.recyclers.MoreOptions

class More: Fragment() {

    private var mView: View? = null
    private var moreOptionsAdapter: MoreOptions? = null
    private var rvMore: RecyclerView? = null
    private var optionsList: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_more, container, false)
        rvMore = mView!!.findViewById(R.id.rvMore)
        main()
        return mView
    }

    private fun main() {
        optionsList.clear()
        setUpArticleAdapter()
        initOptionsList()
        initAdapter()
    }

    private fun setUpArticleAdapter() {
        rvMore!!.itemAnimator = null
        rvMore!!.layoutManager = LinearLayoutManager(context!!)
    }

    private fun initOptionsList() {
        optionsList.add("• Timer")
        optionsList.add("• Play: Life After This")
        optionsList.add("• Directions")
        optionsList.add("• Terms Of Conditions")
        optionsList.add("• Set Time For Timer")
    }

    private fun initAdapter() {
        moreOptionsAdapter = MoreOptions(context!!, optionsList)
        rvMore!!.adapter = moreOptionsAdapter
    }

}