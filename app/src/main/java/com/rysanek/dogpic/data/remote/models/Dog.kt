package com.rysanek.dogpic.data.remote.models

data class Dog(
    val breed: String,
    var subBreeds: List<Dog>? = null,
    var pictureUrls: List<String>? = null
)
