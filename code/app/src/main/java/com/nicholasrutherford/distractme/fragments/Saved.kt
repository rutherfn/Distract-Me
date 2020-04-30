package com.nicholasrutherford.distractme.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.distractme.R
import com.nicholasrutherford.distractme.activitys.MainActivity
import com.nicholasrutherford.distractme.adapters.recyclers.News
import com.nicholasrutherford.distractme.adapters.recyclers.NewsDb
import com.nicholasrutherford.distractme.data.room.SavedArticlesDatabase
import com.nicholasrutherford.distractme.data.room.SavedArticlesEntity
import kotlinx.coroutines.launch

class Saved: Fragment() {

    private var mView: View? = null
    private var savedArticleList: ArrayList<SavedArticlesEntity>? = null
    private var articleSavedAdapter: NewsDb? = null
    private var rvSaved: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_saved, container, false)
        rvSaved = mView!!.findViewById(R.id.rvSaved)
        main()
        return mView
    }

    private fun main() {
        setupSavedArticleAdapter()
        initAdapter()
    }

    private fun setupSavedArticleAdapter() {
        rvSaved!!.itemAnimator = null
        rvSaved!!.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initNewsResponse() {
//        val db = SavedArticlesDatabase(this)
//        lifecycleScope.launch {
//           savedArticleList = db.savedArticleDao().getAll()
//        }
    }

    private fun initAdapter() {
        articleSavedAdapter = NewsDb(requireContext(), (context as MainActivity).savedArticleList)
        rvSaved?.adapter = articleSavedAdapter
    }
}