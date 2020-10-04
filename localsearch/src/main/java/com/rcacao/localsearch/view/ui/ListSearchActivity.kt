package com.rcacao.localsearch.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rcacao.localsearch.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_search)
    }
}
