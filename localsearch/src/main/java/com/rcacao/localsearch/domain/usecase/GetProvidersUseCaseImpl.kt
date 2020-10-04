package com.rcacao.localsearch.domain.usecase

import com.rcacao.localsearch.data.model.Provider
import com.rcacao.localsearch.data.repository.DataResponse
import com.rcacao.localsearch.data.repository.ProvidersRepository
import com.rcacao.localsearch.domain.model.GetProvidersResult
import com.rcacao.localsearch.utils.Constants
import javax.inject.Inject

class GetProvidersUseCaseImpl @Inject constructor(private val repository: ProvidersRepository) :
    GetProvidersUseCase {

    override suspend operator fun invoke(): GetProvidersResult {
        return when (val result: DataResponse<List<Provider>> = repository.getProviders()) {
            is DataResponse.Success -> GetProvidersResult.ProvidersLoaded(result.data)
            is DataResponse.Failure -> GetProvidersResult.Error(
                result.exception.message
                    ?: Constants.GENERIC_ERROR
            )
        }
    }

}
