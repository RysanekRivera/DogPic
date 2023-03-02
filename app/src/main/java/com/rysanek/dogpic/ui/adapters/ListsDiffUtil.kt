package com.rysanek.dogpic.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Calculates the difference between two lists and updates the UI only where necessary.
 * This is more efficient than notifyDataSetChanged as it only populates the changed fields
 * instead of populating all fields every time.
 * @param [oldList] the previous list
 * @param [newList] the new or changed list
 */
class ListsDiffUtil<T>(private val oldList: List<T>, private val newList: List<T>): DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList [newItemPosition]

    /**
     * Handles calculating the difference between two lists and updating the UI accordingly.
     * @param adapter The [RecyclerView.Adapter] to which the changes will dispatch to.
     * This is more efficient than notifyDataSetChanged as it only binds and redraws the fields that are
     * modified.
     */
    fun <R: RecyclerView.ViewHolder> calculateDiff(adapter: RecyclerView.Adapter<R>) =
        DiffUtil.calculateDiff(this).dispatchUpdatesTo(adapter)
}