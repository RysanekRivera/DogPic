package com.rysanek.dogpic.domain.mappers

import android.text.Editable
import com.rysanek.dogpic.data.remote.apis.responses.DogApiResponse
import com.rysanek.dogpic.data.remote.models.AllDogsApiResponse
import com.rysanek.dogpic.data.remote.models.Dog
import com.rysanek.dogpic.domain.utils.ExtensionUtils.capitalized
import retrofit2.Response

fun Set<Map.Entry<String, List<String>>>?.toDogsList(): List<Dog> =
    this?.sortedBy { entry -> entry.key }
        ?.map{ entry -> Dog(breed = entry.key.capitalized(), subBreeds = entry.value.map { subBreed -> Dog(subBreed.capitalized()) }) }
        ?.toList() ?: emptyList()

fun Response<AllDogsApiResponse>.processAndMap() = if (isSuccessful && body() != null) DogApiResponse.Success(data = body()?.breedsMap?.entries.toDogsList())
    else DogApiResponse.Error(message = message(), code = code())

fun List<Dog>.filterBreedsBy(text: Editable) = filter { dog ->  dog.breed.startsWith(text.toString(), true) }.sortedBy { dog -> dog.breed }
