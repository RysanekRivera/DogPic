package com.rysanek.dogpic.domain.repositories

import com.rysanek.dogpic.data.apis.DogPicApi
import com.rysanek.dogpic.data.models.AllDogsApiResponse
import com.rysanek.dogpic.data.models.BreedImagesResponse
import com.rysanek.dogpic.data.repositories.DogPicRepository
import retrofit2.Response
import javax.inject.Inject

class DogPicRepositoryImpl @Inject constructor(
    private val api: DogPicApi
): DogPicRepository {

    override suspend fun getAllBreeds(): Response<AllDogsApiResponse> = api.getAllBreedsList()

    override suspend fun getBreedImages(breedType: String): Response<BreedImagesResponse> = api.getBreedImages(breedType)
}