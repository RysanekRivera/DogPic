package com.rysanek.dogpic.data.apis.responses

sealed class DogApiResponse {

    data class Success<T>(val data: T): DogApiResponse()
    data class Error<T>(val message: String): DogApiResponse()
}