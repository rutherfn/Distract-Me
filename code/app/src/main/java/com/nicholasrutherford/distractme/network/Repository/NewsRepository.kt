package com.nicholasrutherford.distractme.network.Repository

import com.nicholasrutherford.distractme.data.NewsResponse

interface NewsRepository {

    suspend fun getNewsTopHeadlines(country: String, apiKey: String): NewsResponse

}