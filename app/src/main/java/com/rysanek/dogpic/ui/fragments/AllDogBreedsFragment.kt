package com.rysanek.dogpic.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rysanek.customviews.theme.ThemeManager.currentTheme
import com.rysanek.customviews.utils.ColorExtUtils.toColor
import com.rysanek.dogpic.R
import com.rysanek.dogpic.databinding.FragmentAllDogBreedsBinding
import com.rysanek.dogpic.domain.mappers.filterBreedsBy
import com.rysanek.dogpic.ui.adapters.AllDogsRecycleViewAdapter
import com.rysanek.dogpic.ui.fragments.basefragment.DogPicFragment
import com.rysanek.dogpic.ui.viewmodels.DogPicViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllDogBreedsFragment : DogPicFragment() {

    companion object { private val TAG = this::class.simpleName }

    private var _layout: FragmentAllDogBreedsBinding? = null
    private val layout get() = _layout!!

    private val viewModel: DogPicViewModel by activityViewModels()
    private val adapter: AllDogsRecycleViewAdapter by lazy { AllDogsRecycleViewAdapter() }
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowColors()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layout = FragmentAllDogBreedsBinding.inflate(inflater, container, false)

        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavBarNoBackPress(getString(R.string.all_breeds))

        setupRecyclerView()

        setupSearchBar()

        collectAllBreedsAndHandleNetworkEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _layout = null
    }

    private fun setWindowColors() {
        setCurrentWindowColors(
            statusBarColor = currentTheme.buttonBgColor.toColor(),
            windowColor = currentTheme.backgroundColor.toColor()
        )
    }

    private fun setupRecyclerView() {

        layout.rvAllDogBreeds.adapter = adapter

        adapter.setOnItemClicked { dog ->
            viewModel.currentDog = dog
            findNavController().navigate(R.id.action_allDogBreedsFragment_to_breedPicturesFragment)
        }
    }

    private fun setupSearchBar() {

        layout.allBreedsSearchBar.edtClearXImage.setOnClickListener {
            layout.allBreedsSearchBar.edtSearchBar.text?.clear()
        }

        layout.allBreedsSearchBar.edtSearchBar.doAfterTextChanged { editable ->
            if (editable?.toString().isNullOrEmpty()) {
                adapter.setDogsList(viewModel.allBreeds.value)
            } else {
                searchJob?.cancel()
                searchJob = CoroutineScope(Main).launch {
                    adapter.setDogsList(viewModel.allBreeds.value.filterBreedsBy(text = editable!!))
                }

            }
        }
    }

    private fun collectAllBreedsAndHandleNetworkEvents() {
        lifecycleScope.launchWhenStarted {

            viewModel.responseToNetworkEvents(
                onLoading = { showLoadingSpinner() },
                onSuccess = { hideLoadingSpinner() },
                onError = { message ->
                    Log.e(TAG, "Error Downloading: $message")
                    hideLoadingSpinner()
                },
                onIdle = { hideLoadingSpinner() }
            )

            viewModel.allBreeds.collectLatest { dogsList -> adapter.setDogsList(dogsList) }
        }
    }

}