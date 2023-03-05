package com.rysanek.dogpic.data.remote.models

import com.google.gson.annotations.SerializedName

data class AllDogsApiResponse(
    @SerializedName("message") val breedsMap: Map<String, List<String>>?,
    @SerializedName("status") val status: String?
)