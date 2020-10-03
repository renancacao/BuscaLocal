package com.rcacao.localsearch.data.datasource

import com.rcacao.localsearch.data.model.Provider

interface DataSource {
    fun getProviders(): List<Provider>
}