package com.rcacao.localsearch.view.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.rcacao.localsearch.R
import com.rcacao.localsearch.databinding.ActivityListSearchBinding
import com.rcacao.localsearch.view.viewmodel.ProvidersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListSearchActivity : AppCompatActivity(), TextWatcher {

    private val viewModel: ProvidersListViewModel by viewModels()
    private val adapter = ProvidersAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityListSearchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_list_search)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = adapter
        binding.textSearch.addTextChangedListener(this)

        observerErrorEvent()
        observerProvidersList()

    }

    private fun observerProvidersList() {
        viewModel.providers.observe(this, Observer {
            adapter.setProviders(it)
        })
    }

    private fun observerErrorEvent() {
        viewModel.error.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { showError(it) }
        })
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun afterTextChanged(p0: Editable?) {
        //not used
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //not used
    }

    override fun onTextChanged(searchText: CharSequence?, p1: Int, p2: Int, p3: Int) {
        viewModel.search(searchText.toString())
    }
}
