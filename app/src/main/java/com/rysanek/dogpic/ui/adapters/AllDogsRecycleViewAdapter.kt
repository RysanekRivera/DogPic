package com.rysanek.dogpic.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rysanek.dogpic.data.models.Dog
import com.rysanek.dogpic.databinding.SingleDogBreedBinding
import java.util.Locale

class AllDogsRecycleViewAdapter: RecyclerView.Adapter<AllDogsRecycleViewAdapter.AllDogsViewHolder>() {

    private val dogsList = mutableListOf<Dog>()
    private var onItemClicked : (Dog) -> Unit = {}

    class AllDogsViewHolder(private val layout: SingleDogBreedBinding): RecyclerView.ViewHolder(layout.root) {

        companion object {
            fun from(parent: ViewGroup) : AllDogsViewHolder {
                val layout = LayoutInflater.from(parent.context)
                val root = SingleDogBreedBinding.inflate(layout)
                return AllDogsViewHolder(root)
            }
        }

        fun bind(dog: Dog, onClick: (Dog) -> Unit) {
            layout.tvDogBreedTitle.text = dog.breed.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
            layout.root.setOnClickListener { onClick(dog) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllDogsViewHolder {
        return AllDogsViewHolder.from(parent)
    }

    override fun getItemCount() = dogsList.size

    override fun onBindViewHolder(holder: AllDogsViewHolder, position: Int) {
        holder.bind(dogsList[position], onItemClicked)
    }

    fun setOnItemClicked(onClick : (Dog) -> Unit) {
        onItemClicked = onClick
    }

    fun setDogsList(newDogsList: List<Dog>) {
        ListsDiffUtil(dogsList, newDogsList).calculateDiff(this)
        dogsList.clear()
        dogsList.addAll(newDogsList)
    }

}