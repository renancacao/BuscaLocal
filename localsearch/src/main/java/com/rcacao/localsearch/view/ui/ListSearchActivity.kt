package com.rcacao.localsearch.view.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rcacao.localsearch.R
import com.rcacao.localsearch.view.viewmodel.ProvidersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListSearchActivity : AppCompatActivity() {

    private val viewModel: ProvidersListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_search)
    }
}
