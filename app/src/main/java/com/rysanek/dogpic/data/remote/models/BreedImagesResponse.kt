package com.rysanek.dogpic.data.remote.models

import com.google.gson.annotations.SerializedName

data class BreedImagesResponse(
    @SerializedName("message") val breedImagesUrls: List<String>,
    @SerializedName("status") val status: String?
)
