package com.nicholasrutherford.distractme.data.responses

import com.google.gson.annotations.SerializedName
import com.nicholasrutherford.distractme.data.AllSources

data class SourcesResponse (
    @SerializedName("status")
    val status: String,
    @SerializedName("sources")
    val sources: List<AllSources>
)