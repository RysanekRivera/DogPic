package com.rysanek.dogpic.data.models

data class Dog(
    val breed: String,
    var subBreeds: List<String>? = null,
    var pictureUrls: List<String>? = null
)
