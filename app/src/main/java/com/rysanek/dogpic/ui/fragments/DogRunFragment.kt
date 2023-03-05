package com.rysanek.dogpic.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.rysanek.dogpic.R
import com.rysanek.dogpic.databinding.FragmentDogRunBinding
import com.rysanek.dogpic.ui.fragments.basefragment.DogPicFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class DogRunFragment: DogPicFragment() {

    private var _layout: FragmentDogRunBinding? = null
    private val layout get() = _layout!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layout = FragmentDogRunBinding.inflate(inflater, container, false)

        startRunDogAnimation()

        lifecycleScope.launchWhenResumed {
            delay(2000)
            findNavController().navigate(R.id.action_dogRunFragment_to_allDogBreedsFragment)
        }

        return layout.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _layout = null
    }

    private fun startRunDogAnimation() = Glide.with(this).load(R.drawable.run_dog).into(layout.ivDogRun)

}