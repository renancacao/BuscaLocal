package com.rcacao.localsearch.data.repository

import com.rcacao.localsearch.data.model.Provider

interface ProvidersRepository {
    suspend fun getProviders(): DataResponse<List<Provider>>
}