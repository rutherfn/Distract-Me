package com.nicholasrutherford.distractme.data.responses

import com.google.gson.annotations.SerializedName
import com.nicholasrutherford.distractme.data.Article

data class NewsResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)