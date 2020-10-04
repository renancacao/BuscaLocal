package com.rcacao.localsearch.view.ui

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("bind:separator")
fun setSeparator(view: RecyclerView, orientation: Int) {
    val dividerItemDecoration = DividerItemDecoration(view.context, orientation)
    view.addItemDecoration(dividerItemDecoration)
}

@BindingAdapter("bind:isVisible")
fun setVisibility(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}


