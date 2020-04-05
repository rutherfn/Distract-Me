package com.nicholasrutherford.distractme.network.repository

import com.nicholasrutherford.distractme.data.responses.CountriesResponse
import com.nicholasrutherford.distractme.data.responses.NewsResponse

interface NewsRepository {

    suspend fun getNewsTopHeadlinesByCountry(country: String, apiKey: String): NewsResponse

    suspend fun getCountries() : CountriesResponse

}