package com.nicholasrutherford.distractme.network

import com.nicholasrutherford.distractme.data.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") location: String, @Query("apiKey") apiKey:String):
            NewsResponse

}