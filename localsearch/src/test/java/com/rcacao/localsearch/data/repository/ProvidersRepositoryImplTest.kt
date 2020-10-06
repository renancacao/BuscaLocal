package com.rcacao.localsearch.data.repository

import com.nhaarman.mockitokotlin2.MockitoKotlinException
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rcacao.localsearch.data.datasource.DataSource
import com.rcacao.localsearch.data.model.Provider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class ProvidersRepositoryImplTest {

    private lateinit var repository: ProvidersRepository
    private val dataSource: DataSource = mock()

    @Test
    fun getProvidersOnExceptionReturnFailure() {
        runBlockingTest {
            val ex = MockitoKotlinException("test message", null)
            whenever(dataSource.getProviders()).thenThrow(ex)
            repository = ProvidersRepositoryImpl(dataSource)

            val result: DataResponse<List<Provider>> = repository.getProviders()

            assertTrue(result is DataResponse.Failure)
            assertEquals(ex, (result as DataResponse.Failure).exception)
        }
    }

    @Test
    fun getProvidersOnSuccessReturnSuccess() {
        runBlockingTest {
            val list: List<Provider> = listOf(Provider(), Provider())
            whenever(dataSource.getProviders()).thenReturn(list)
            repository = ProvidersRepositoryImpl(dataSource)

            val result: DataResponse<List<Provider>> = repository.getProviders()

            assertTrue(result is DataResponse.Success)
            assertEquals(list, (result as DataResponse.Success).data)
        }
    }

}