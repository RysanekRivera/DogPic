package com.rysanek.dogpic.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rysanek.dogpic.data.remote.apis.responses.DogApiResponse
import com.rysanek.dogpic.data.remote.models.Dog
import com.rysanek.dogpic.domain.eventhandlers.NetworkEventHandler
import com.rysanek.dogpic.domain.mappers.formulateErrorMessage
import com.rysanek.dogpic.domain.usecases.GetAllBreeds
import com.rysanek.dogpic.domain.usecases.GetBreedImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DogPicViewModel @Inject constructor(
    private val event: NetworkEventHandler,
    private val getAllBreeds: GetAllBreeds,
    private val getBreedImages: GetBreedImages
) : ViewModel() {

    private var _allBreeds: MutableStateFlow<List<Dog>> = MutableStateFlow(mutableListOf())
    val allBreeds get() = _allBreeds.asStateFlow()

    lateinit var currentDog: Dog

    fun getAllDogBreeds() {
        viewModelScope.launch {

            event.onLoading()

            getAllBreeds.getAllBreeds()
                .catch { e ->
                    event.onError(e.message)
                }
                .onCompletion {
                    event.onIdle()
                }
                .collect { response ->
                    _allBreeds.emit(
                        when (response) {
                            is DogApiResponse.Success -> { response.data }
                            is DogApiResponse.Error -> {
                                event.onError(message = formulateErrorMessage(code = response.code, message = response.message))
                                emptyList()
                            }
                        }
                    )

                    event.onSuccess()
                }
        }
    }

    fun getBreedPictures() {
        viewModelScope.launch {
            event.onLoading()
            getBreedImages.fetchBreedImages(currentDog.breed.lowercase(Locale.ROOT))
                .catch { e -> event.onError(e.message) }
                .onCompletion { event.onIdle() }
                .collect { response ->
                    currentDog.pictureUrls = when (response) {
                        is DogApiResponse.Success -> { response.data }
                        is DogApiResponse.Error -> {
                            event.onError(message = formulateErrorMessage(response.code, response.message))
                            emptyList()
                        }
                    }

                    event.onSuccess()
                }
        }
    }

    fun responseToNetworkEvents(
        onSuccess: () -> Unit = {},
        onError: (message: String?) -> Unit = { },
        onLoading: () -> Unit = {},
        onIdle: () -> Unit = {}
    ) {
        event.setOnSuccess(onSuccess)
        event.setOnError(onError)
        event.setOnLoading(onLoading)
        event.setOnIdle(onIdle)
    }

}