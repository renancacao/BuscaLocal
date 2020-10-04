package com.rcacao.localsearch.domain.usecase

import com.rcacao.localsearch.domain.model.GetProvidersResult

interface GetProvidersUseCase {
    suspend operator fun invoke(): GetProvidersResult
}