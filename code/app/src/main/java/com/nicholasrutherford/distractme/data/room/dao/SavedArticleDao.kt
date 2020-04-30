package com.nicholasrutherford.distractme.data.room.dao

import androidx.room.*
import com.nicholasrutherford.distractme.data.room.SavedArticlesEntity

@Dao
interface SavedArticleDao {
    @Query("SELECT * FROM saved_articles")
    fun getAll(): List<SavedArticlesEntity>

    @Insert
    fun insertAll(vararg savedArticles: SavedArticlesEntity)

    @Delete
    fun delete(savedArticles: SavedArticlesEntity)

}
