package com.rysanek.dogpic.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rysanek.dogpic.R
import com.rysanek.dogpic.databinding.SingleDogPicBinding

class BreedPicturesRecycleViewAdapter: RecyclerView.Adapter<BreedPicturesRecycleViewAdapter.BreedPicturesViewHolder>() {

    private val imageUrls = mutableListOf<String>()

    class BreedPicturesViewHolder(private val layout: SingleDogPicBinding): RecyclerView.ViewHolder(layout.root) {

        companion object {
            fun from(parent: ViewGroup) : BreedPicturesViewHolder {
                val layout = LayoutInflater.from(parent.context)
                val root = SingleDogPicBinding.inflate(layout)
                return BreedPicturesViewHolder(root)
            }
        }

        fun bind(url: String) {
            if (url.isNotEmpty()) {
                Glide.with(layout.imageView)
                    .load(url)
                    .placeholder(R.drawable.dog_pic)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(layout.imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedPicturesViewHolder {
        return BreedPicturesViewHolder.from(parent)
    }

    override fun getItemCount() = imageUrls.size

    override fun onBindViewHolder(holder: BreedPicturesViewHolder, position: Int) {
        holder.bind(imageUrls[position])
    }

    fun setDogsList(newUrls: List<String>) {
        ListsDiffUtil(imageUrls, newUrls).calculateDiff(this)
        imageUrls.clear()
        imageUrls.addAll(newUrls)
    }

}