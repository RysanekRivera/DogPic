package com.rysanek.dogpic.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rysanek.dogpic.databinding.FragmentBreedPicturesBinding
import com.rysanek.dogpic.ui.adapters.BreedPicturesRecycleViewAdapter
import com.rysanek.dogpic.ui.fragments.basefragment.DogPicFragment
import com.rysanek.dogpic.ui.viewmodels.DogPicViewModel
import java.util.Locale

class BreedPicturesFragment : DogPicFragment() {

    private var _layout: FragmentBreedPicturesBinding? = null
    private val layout get() = _layout!!

    private val adapter: BreedPicturesRecycleViewAdapter by lazy { BreedPicturesRecycleViewAdapter() }
    private val viewModel: DogPicViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layout = FragmentBreedPicturesBinding.inflate(layoutInflater, container, false)

        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navBarSetup()

        setupRecyclerView()

        handleNetworkEvents()

        if (viewModel.currentDog.pictureUrls?.isNotEmpty() == true) {
            adapter.setDogsList(viewModel.currentDog.pictureUrls!!)
        } else {
            viewModel.getBreedPictures()
        }

    }

    private fun handleNetworkEvents() {
        lifecycleScope.launchWhenStarted {

            viewModel.responseToNetworkEvents(
                onLoading = { showLoadingSpinner() },
                onSuccess = {

                    if (viewModel.currentDog.pictureUrls.isNullOrEmpty())
                        layout.tvNoPics.visibility = View.VISIBLE
                    else {
                        adapter.setDogsList(viewModel.currentDog.pictureUrls!!)
                    }

                    hideLoadingSpinner()
                },
                onError = { message ->

                    Log.e("BreedPicturesFrag", "Error Downloading: $message")

                    if (viewModel.currentDog.pictureUrls.isNullOrEmpty())
                        layout.tvNoPics.visibility = View.VISIBLE
                    else {
                        adapter.setDogsList(viewModel.currentDog.pictureUrls!!)
                    }

                    hideLoadingSpinner()

                },
                onIdle = { hideLoadingSpinner() }
            )

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _layout = null
    }

    private fun setupRecyclerView() {
        layout.rvBreedPictures.adapter = adapter
    }

    private fun navBarSetup() {
        layout.breedTitleContainer.visibility = View.VISIBLE
        layout.breedTitle.visibility = View.VISIBLE
        layout.breedTitle.text = viewModel.currentDog.breed.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        layout.breedBackArrow.setOnClickListener { findNavController().popBackStack() }
    }
}