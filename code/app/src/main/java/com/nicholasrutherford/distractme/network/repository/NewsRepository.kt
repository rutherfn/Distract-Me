package com.nicholasrutherford.distractme.network.repository

import com.nicholasrutherford.distractme.data.responses.CountriesResponse
import com.nicholasrutherford.distractme.data.responses.NewsResponse
import com.nicholasrutherford.distractme.data.responses.SourcesResponse

interface NewsRepository {

    suspend fun getNewsTopHeadlinesByCountry(country: String, apiKey: String): NewsResponse

    suspend fun getTopHeadlinesBySource(source: String, apiKey: String) : NewsResponse

    suspend fun getTopHeadlinesByCountryAndCategory(country: String, category: String, apiKey: String) : NewsResponse

    suspend fun getTopHeadlinesBySubject(subject: String, apiKey: String) : NewsResponse

    suspend fun getAllSources(apiKey: String) : SourcesResponse

    suspend fun getCountries() : CountriesResponse

    suspend fun getEverything(subject: String, apiKey: String) : NewsResponse

}