package com.nicholasrutherford.distractme.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_articles")
data class SavedArticlesEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int,

        @ColumnInfo(name = "title") var title: String,
        @ColumnInfo(name = "description") var description: String,
        @ColumnInfo(name = "author") var author: String,
        @ColumnInfo(name = "sourceName") var sourceName: String,
        @ColumnInfo(name = "publishedAt") var publishedAt: String,
        @ColumnInfo(name = "imageUrl") var imageUrl: String,
        @ColumnInfo(name = "url") var url: String
)