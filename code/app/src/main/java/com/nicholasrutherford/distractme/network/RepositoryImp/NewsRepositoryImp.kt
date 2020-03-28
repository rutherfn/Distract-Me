package com.nicholasrutherford.distractme.network.RepositoryImp

import com.nicholasrutherford.distractme.data.NewsResponse
import com.nicholasrutherford.distractme.network.NewsApiService
import com.nicholasrutherford.distractme.network.Repository.NewsRepository
import com.nicholasrutherford.distractme.network.RetrofitClient

class NewsRepositoryImp: NewsRepository  {
    private var client: NewsApiService = RetrofitClient.RetroFitClient.retrofitNewsService

    override suspend fun getNewsTopHeadlines(country: String, apiKey: String): NewsResponse {
        return client.getTopHeadlines (country, apiKey)
    }
}