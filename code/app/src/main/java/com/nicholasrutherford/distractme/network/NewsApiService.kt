package com.nicholasrutherford.distractme.network

import com.nicholasrutherford.distractme.data.responses.CountriesResponse
import com.nicholasrutherford.distractme.data.responses.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") location: String, @Query("apiKey") apiKey:String):
            NewsResponse

    @GET("b/5e871d0c4f5f49640ba3375b")
    suspend fun getCountries(): CountriesResponse

}