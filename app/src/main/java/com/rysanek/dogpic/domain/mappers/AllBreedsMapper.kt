package com.rysanek.dogpic.domain.mappers

import com.rysanek.dogpic.data.models.Dog

fun  Set<Map.Entry<String, List<String>>>?.toDogsList(): List<Dog>{

    return this?.sortedBy { entry -> entry.key }?.map{ entry -> Dog(breed = entry.key, subBreeds = entry.value) }?.toList() ?: emptyList()

}