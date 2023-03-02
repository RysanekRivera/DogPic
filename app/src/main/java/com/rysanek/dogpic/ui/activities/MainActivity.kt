package com.rysanek.dogpic.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import com.rysanek.dogpic.databinding.ActivityMainBinding
import com.rysanek.dogpic.ui.activities.baseactivity.DogPicActivity
import com.rysanek.dogpic.ui.viewmodels.DogPicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DogPicActivity() {

    private lateinit var layout: ActivityMainBinding
    private val viewModel: DogPicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layout = ActivityMainBinding.inflate(layoutInflater)

        setContentView(layout.root)

        viewModel.getAllDogBreeds()
    }

}