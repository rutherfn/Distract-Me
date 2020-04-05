package com.nicholasrutherford.distractme.network

import com.nicholasrutherford.distractme.data.responses.CountriesResponse
import com.nicholasrutherford.distractme.data.responses.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlinesByCountry(@Query("country") location: String, @Query("apiKey") apiKey:String):
            NewsResponse

    @GET("top-headlines")
    suspend fun getTopHeadlinesBySource(@Query("sources") sources: String,
                                        @Query("apiKey") apiKey: String)

    @GET("b/5e871d0c4f5f49640ba3375b")
    suspend fun getCountries(): CountriesResponse

}