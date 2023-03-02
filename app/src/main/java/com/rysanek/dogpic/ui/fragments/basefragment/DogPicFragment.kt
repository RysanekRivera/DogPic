package com.rysanek.dogpic.ui.fragments.basefragment

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.rysanek.customviews.ColorConstraintLayout
import com.rysanek.customviews.ColorTextView
import com.rysanek.dogpic.R
import com.rysanek.dogpic.ui.activities.baseactivity.DogPicActivity

abstract class DogPicFragment: Fragment() {

    fun showLoadingSpinner() = activity?.let { (activity as DogPicActivity).showLoadingSpinner() }

    fun hideLoadingSpinner() = activity?.let { (activity as DogPicActivity).hideLoadingSpinner() }

    fun setupNavBar(title: String? = null, onBackPressed : () -> Unit) {
        requireActivity().findViewById<ConstraintLayout>(R.id.backArrow).setOnClickListener {
            onBackPressed.invoke()
        }

        title?.let {
            requireActivity().findViewById<ColorConstraintLayout>(R.id.titleContainer).visibility = View.VISIBLE
            requireActivity().findViewById<ColorTextView>(R.id.title).text = title
        } ?: run {
            requireActivity().findViewById<ColorConstraintLayout>(R.id.titleContainer).visibility = View.GONE
        }
    }

    fun setupNavBarNoBackPress(title: String? = null) {
        requireActivity().findViewById<ConstraintLayout>(R.id.backArrow)?.visibility = View.GONE

        title?.let {
            requireActivity().findViewById<ColorConstraintLayout>(R.id.titleContainer).visibility = View.VISIBLE
            requireActivity().findViewById<ColorTextView>(R.id.title).text = title
        } ?: run {
            requireActivity().findViewById<ColorConstraintLayout>(R.id.titleContainer).visibility = View.GONE
        }
    }

    fun setCurrentWindowColors(statusBarColor: Int? = null, windowColor: Int? = null) {
        statusBarColor?.let { color -> requireActivity().window?.statusBarColor = color }
        windowColor?.let { color -> requireActivity().window?.setBackgroundDrawable(ColorDrawable(color)) }
    }

}