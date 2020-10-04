package com.rcacao.localsearch.domain.model

import com.rcacao.localsearch.data.model.Provider

sealed class GetProvidersResult {
    data class ProvidersLoaded(val providers: List<Provider>) : GetProvidersResult()
    data class Error(val errorMessage: String) : GetProvidersResult()
}