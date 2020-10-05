package com.rcacao.localsearch.view.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.rcacao.localsearch.data.model.Provider
import com.rcacao.localsearch.databinding.ItemProviderBinding

class ProvidersAdapter : RecyclerView.Adapter<ProvidersAdapter.ProviderViewHolder>(), Filterable {

    var providers: List<Provider> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderViewHolder {
        val binding: ItemProviderBinding =
            ItemProviderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProviderViewHolder(binding)
    }

    override fun getItemCount(): Int = providers.size

    override fun onBindViewHolder(holder: ProviderViewHolder, position: Int) {
        holder.binding.provider = providers[position]
    }

    class ProviderViewHolder(val binding: ItemProviderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

}