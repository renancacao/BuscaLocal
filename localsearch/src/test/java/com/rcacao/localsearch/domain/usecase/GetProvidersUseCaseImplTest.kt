package com.rcacao.localsearch.domain.usecase

import com.nhaarman.mockitokotlin2.MockitoKotlinException
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rcacao.localsearch.data.model.Provider
import com.rcacao.localsearch.data.repository.DataResponse
import com.rcacao.localsearch.data.repository.ProvidersRepository
import com.rcacao.localsearch.domain.model.GetProvidersResult
import com.rcacao.localsearch.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class GetProvidersUseCaseImplTest {

    private lateinit var useCase: GetProvidersUseCase
    private val repository: ProvidersRepository = mock()

    @Test
    fun getProvidersOnSuccessReturnProvidersLoaded() {
        runBlockingTest {
            val list: List<Provider> = listOf(Provider("1", "item1"), Provider("2", "item2"))
            whenever(repository.getProviders()).thenReturn(DataResponse.Success(list))
            useCase = GetProvidersUseCaseImpl(repository)

            val result: GetProvidersResult = useCase()

            Assert.assertTrue(result is GetProvidersResult.ProvidersLoaded)
            Assert.assertEquals(list, (result as GetProvidersResult.ProvidersLoaded).providers)
        }
    }

    @Test
    fun getProvidersOnFailureReturnProvidersError() {
        runBlockingTest {
            val ex = MockitoKotlinException("test message", null)
            whenever(repository.getProviders()).thenReturn(DataResponse.Failure(ex))
            useCase = GetProvidersUseCaseImpl(repository)

            val result: GetProvidersResult = useCase()

            Assert.assertTrue(result is GetProvidersResult.Error)
            Assert.assertEquals("test message", (result as GetProvidersResult.Error).errorMessage)
        }
    }

    @Test
    fun getProvidersOnFailureWithoutMessageReturnProvidersErrorGeneric() {
        runBlockingTest {
            val ex = MockitoKotlinException(null, null)
            whenever(repository.getProviders()).thenReturn(DataResponse.Failure(ex))
            useCase = GetProvidersUseCaseImpl(repository)

            val result: GetProvidersResult = useCase()

            Assert.assertTrue(result is GetProvidersResult.Error)
            Assert.assertEquals(
                Constants.GENERIC_ERROR,
                (result as GetProvidersResult.Error).errorMessage
            )
        }
    }
}