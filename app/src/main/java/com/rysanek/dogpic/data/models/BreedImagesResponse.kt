package com.rysanek.dogpic.data.models

import com.google.gson.annotations.SerializedName

data class BreedImagesResponse(
    @SerializedName("message") val message: List<String>,
    @SerializedName("status") val status: String?
)
