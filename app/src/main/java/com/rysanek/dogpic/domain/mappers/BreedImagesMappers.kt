package com.rysanek.dogpic.domain.mappers

import com.rysanek.dogpic.data.remote.apis.responses.DogApiResponse
import com.rysanek.dogpic.data.remote.models.BreedImagesResponse
import retrofit2.Response

fun Response<BreedImagesResponse>.processAndMap(): DogApiResponse<List<String>> =
    if (isSuccessful && body() != null) DogApiResponse.Success(data = body()!!.breedImagesUrls)
    else DogApiResponse.Error(message = message(), code = code())
