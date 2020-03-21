package com.nicholasrutherford.distractme.data

data class Articles(
    val sources: Sources,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)