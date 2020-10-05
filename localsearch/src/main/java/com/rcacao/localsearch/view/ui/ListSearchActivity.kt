package com.rcacao.localsearch.view.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.rcacao.localsearch.R
import com.rcacao.localsearch.databinding.ActivityListSearchBinding
import com.rcacao.localsearch.view.viewmodel.ProvidersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListSearchActivity : AppCompatActivity() {

    private val viewModel: ProvidersListViewModel by viewModels()
    private val adapter = ProvidersAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityListSearchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_list_search)

        binding.viewModel = viewModel
        binding.recyclerView.adapter = adapter

        viewModel.providers.observe(this, Observer {
            adapter.providers = it
            adapter.notifyDataSetChanged()
        })

    }
}
