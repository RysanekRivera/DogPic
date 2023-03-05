package com.rysanek.dogpic.data.remote.apis.responses

sealed class DogApiResponse<T> {

    data class Success<T>(val data: T): DogApiResponse<T>()
    data class Error<T>(val message: String?, val code: Int?): DogApiResponse<T>()

}