package com.rysanek.dogpic.domain.usecases

import com.rysanek.dogpic.domain.mappers.processAndMap
import com.rysanek.dogpic.domain.repositories.DogPicRepositoryImpl
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBreedImages @Inject constructor(
    private val repository: DogPicRepositoryImpl
) {

    suspend fun fetchBreedImages(breedType: String) = flow { emit(repository.getBreedImages(breedType)) }
        .map { response -> response.processAndMap() }

}