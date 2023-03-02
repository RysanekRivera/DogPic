package com.rysanek.dogpic.data.repositories

import com.rysanek.dogpic.data.models.AllDogsApiResponse
import com.rysanek.dogpic.data.models.BreedImagesResponse
import retrofit2.Response

interface DogPicRepository {

    suspend fun getAllBreeds(): Response<AllDogsApiResponse>

    suspend fun getBreedImages(breedType: String): Response<BreedImagesResponse>

}