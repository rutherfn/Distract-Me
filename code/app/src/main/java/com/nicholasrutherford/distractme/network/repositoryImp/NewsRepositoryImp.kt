package com.nicholasrutherford.distractme.network.repositoryImp

import com.nicholasrutherford.distractme.data.responses.CountriesResponse
import com.nicholasrutherford.distractme.data.responses.NewsResponse
import com.nicholasrutherford.distractme.network.NewsApiService
import com.nicholasrutherford.distractme.network.repository.NewsRepository
import com.nicholasrutherford.distractme.network.RetrofitClient

class NewsRepositoryImp: NewsRepository  {
    private var client: NewsApiService = RetrofitClient.RetroFitClient.retrofitNewsService
    private var clientCountries: NewsApiService = RetrofitClient.RetroFitClient.retrofitCountriesService

    override suspend fun getNewsTopHeadlines(country: String, apiKey: String): NewsResponse {
        return client.getTopHeadlines (country, apiKey)
    }

    override suspend fun getCountries(): CountriesResponse {
        return clientCountries.getCountries()
    }
}