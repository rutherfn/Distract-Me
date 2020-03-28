package com.nicholasrutherford.distractme.network.Repository

import androidx.lifecycle.LiveData
import com.nicholasrutherford.distractme.data.NewsResponse

interface NewsRepository {

    fun getNewsTopHeadlines(country: String, apiKey: String): LiveData<NewsResponse>

}