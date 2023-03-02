package com.rysanek.dogpic.data.apis

import com.rysanek.dogpic.data.models.AllDogsApiResponse
import com.rysanek.dogpic.data.models.BreedImagesResponse
import com.rysanek.dogpic.data.utils.DataConstants.ALL_BREEDS
import com.rysanek.dogpic.data.utils.DataConstants.BREED_IMAGES
import com.rysanek.dogpic.data.utils.DataConstants.BREED_TYPE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogPicApi {

    @GET(ALL_BREEDS)
    suspend fun getAllBreedsList(): Response<AllDogsApiResponse>

    @GET(BREED_IMAGES)
    suspend fun getBreedImages(@Path(BREED_TYPE) breedType: String) : Response<BreedImagesResponse>

}