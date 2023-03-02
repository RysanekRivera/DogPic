package com.rysanek.dogpic.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rysanek.dogpic.data.models.Dog
import com.rysanek.dogpic.domain.eventhandlers.NetworkEventHandler
import com.rysanek.dogpic.domain.usecases.GetAllBreeds
import com.rysanek.dogpic.domain.usecases.GetBreedImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
                .collect { dogsList ->
                    _allBreeds.emit(dogsList)
                    event.onSuccess()

                }
        }
    }

    fun getBreedPictures() {
        viewModelScope.launch {
            event.onLoading()
            getBreedImages.fetchBreedImages(currentDog.breed)
                .catch { e ->
                    event.onError(e.message)
                }
                .collect { pictureUrls ->
                    currentDog.pictureUrls = pictureUrls
                    event.onSuccess()
                }
        }
    }


    fun responseToNetworkEvents(
        onSuccess: () -> Unit = {},
        onError: (String?) -> Unit = {},
        onLoading: () -> Unit = {},
        onIdle: () -> Unit = {}
    ) {
        event.setOnSuccess(onSuccess)
        event.setOnError(onError)
        event.setOnLoading(onLoading)
        event.setOnIdle(onIdle)
    }

}