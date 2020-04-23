package com.nicholasrutherford.distractme.network.repositoryImp

import com.nicholasrutherford.distractme.data.responses.CountriesResponse
import com.nicholasrutherford.distractme.data.responses.NewsResponse
import com.nicholasrutherford.distractme.data.responses.SourcesResponse
import com.nicholasrutherford.distractme.network.NewsApiService
import com.nicholasrutherford.distractme.network.repository.NewsRepository
import com.nicholasrutherford.distractme.network.RetrofitClient

class NewsRepositoryImp: NewsRepository  {

    private var client: NewsApiService = RetrofitClient.RetroFitClient.retrofitNewsService
    private var clientCountries: NewsApiService = RetrofitClient.RetroFitClient.retrofitCountriesService

    override suspend fun getNewsTopHeadlinesByCountry(country: String, apiKey: String): NewsResponse {
        return client.getTopHeadlinesByCountry(country, apiKey)
    }

    override suspend fun getTopHeadlinesBySource(source: String, apiKey: String): NewsResponse {
        return client.getTopHeadlinesBySource(source, apiKey)
    }

    override suspend fun getTopHeadlinesByCountryAndCategory(country: String, category: String, apiKey: String): NewsResponse {
        return client.getTopHeadlinesByCountryAndCategory(country, category, apiKey)
    }

    override suspend fun getTopHeadlinesBySubject(subject: String, apiKey: String): NewsResponse {
        return client.getTopHeadlinesBySubject(subject, apiKey)
    }

    override suspend fun getAllSources(apiKey: String): SourcesResponse {
        return client.getAllSources(apiKey)
    }

    override suspend fun getCountries(): CountriesResponse {
        return clientCountries.getCountries()
    }

    override suspend fun getEverything(subject: String, apiKey: String): NewsResponse {
        return client.getEverything(subject, apiKey)
    }
}