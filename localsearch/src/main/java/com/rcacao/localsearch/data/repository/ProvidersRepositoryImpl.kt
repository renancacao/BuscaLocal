package com.rcacao.localsearch.data.repository

import com.rcacao.localsearch.data.datasource.DataSource
import com.rcacao.localsearch.data.model.Provider
import javax.inject.Inject

class ProvidersRepositoryImpl @Inject constructor(private val dataSource: DataSource) :
    ProvidersRepository {

    override suspend fun getProviders(): DataResponse<List<Provider>> = try {
        DataResponse.Success(dataSource.getProviders())
    } catch (ex: Exception) {
        DataResponse.Failure(ex)
    }

}