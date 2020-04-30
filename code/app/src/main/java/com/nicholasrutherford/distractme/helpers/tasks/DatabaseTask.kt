package com.nicholasrutherford.distractme.helpers.tasks

import android.os.AsyncTask
import com.nicholasrutherford.distractme.data.room.SavedArticlesDatabase
import com.nicholasrutherford.distractme.data.room.SavedArticlesEntity

class DatabaseTask(var db: SavedArticlesDatabase?, private val savedArticleList: ArrayList<SavedArticlesEntity>) : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg params: Void?): Void? {
        savedArticleList.clear()
        db?.savedArticleDao()?.getAll()?.forEach {
            savedArticleList.add(it)
        }
        return null
    }

}