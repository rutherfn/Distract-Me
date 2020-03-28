package com.nicholasrutherford.distractme.network.RepositoryImp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nicholasrutherford.distractme.data.NewsResponse
import com.nicholasrutherford.distractme.network.NewsApiService
import com.nicholasrutherford.distractme.network.Repository.NewsRepository
import com.nicholasrutherford.distractme.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepositoryImp: NewsRepository  {
    private var client: NewsApiService = RetrofitClient.RetroFitClient.retrofitNewsService

    override fun getNewsTopHeadlines(country: String, apiKey: String): LiveData<NewsResponse> {
        val liveData = MutableLiveData<NewsResponse>()

        client.getTopHeadlines(country, apiKey).enqueue(object:
            Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {

                    // When data is available, populate LiveData
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return liveData
    }
}