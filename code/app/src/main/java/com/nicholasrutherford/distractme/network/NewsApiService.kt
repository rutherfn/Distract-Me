package com.nicholasrutherford.distractme.network

import com.nicholasrutherford.distractme.data.responses.CountriesResponse
import com.nicholasrutherford.distractme.data.responses.NewsResponse
import com.nicholasrutherford.distractme.data.responses.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlinesByCountry(@Query("country") location: String, @Query("apiKey") apiKey:String):
            NewsResponse

    @GET("top-headlines")
    suspend fun getTopHeadlinesBySource(@Query("sources") sources: String,
                                        @Query("apiKey") apiKey: String):
            NewsResponse

    @GET("top-headlines")
    suspend fun getTopHeadlinesByCountryAndCategory(@Query("country") sources: String,
                                                    @Query("category") category: String,
                                                    @Query("apiKey") apiKey: String):
            NewsResponse

    @GET("top-headlines")
    suspend fun getTopHeadlinesBySubject(@Query("q")subject: String,
                                        @Query("apiKey") apiKey: String):
            NewsResponse

    @GET("sources")
    suspend fun getAllSources(@Query("apiKey") apiKey: String):
            SourcesResponse

    @GET("b/5e871d0c4f5f49640ba3375b")
    suspend fun getCountries(): CountriesResponse

    @GET("everything")
    suspend fun getEverything(@Query("q")subject: String,
                               @Query("apiKey") apiKey: String):
            NewsResponse

}