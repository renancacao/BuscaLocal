package com.rcacao.localsearch.domain.usecase

import com.rcacao.localsearch.data.model.Provider

interface SearchProvidersUseCase {
    suspend operator fun invoke(text: String, list: List<Provider>): List<Provider>
}