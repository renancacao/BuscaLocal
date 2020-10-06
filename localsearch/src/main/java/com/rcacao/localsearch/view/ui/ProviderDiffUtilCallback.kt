package com.rcacao.localsearch.view.ui

import androidx.recyclerview.widget.DiffUtil
import com.rcacao.localsearch.data.model.Provider

class ProviderDiffUtilCallback(
    private var oldItems: List<Provider>,
    private var newItems: List<Provider>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size
    override fun getNewListSize(): Int = newItems.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].id == newItems[newItemPosition].id
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition].name == newItems[newItemPosition].name

}
