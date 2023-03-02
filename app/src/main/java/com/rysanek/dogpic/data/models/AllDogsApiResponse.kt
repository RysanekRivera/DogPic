package com.rysanek.dogpic.data.models

import com.google.gson.annotations.SerializedName

data class AllDogsApiResponse(
    @SerializedName("message") val message: Map<String, List<String>>?,
    @SerializedName("status") val status: String?
)