package com.nicholasrutherford.distractme.data.Endpoints

import com.nicholasrutherford.distractme.data.Articles

data class TopHeadlines(
    val status: String,
    val totalResults: String,
    val articles: List<Articles>
)