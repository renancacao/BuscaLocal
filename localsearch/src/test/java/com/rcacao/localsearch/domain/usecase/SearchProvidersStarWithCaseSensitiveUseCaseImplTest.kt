package com.rcacao.localsearch.domain.usecase

import com.google.gson.Gson
import com.rcacao.localsearch.data.model.Provider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchProvidersStarWithCaseSensitiveUseCaseImplTest {

    private lateinit var useCase: SearchProvidersUseCase

    @Test
    fun searchProvidersOnTextEmptyReturnList() {
        runBlockingTest {
            val list: List<Provider> = listOf(Provider("1", "item1"), Provider("2", "item2"))

            useCase = SearchProvidersStarWithCaseSensitiveUseCaseImpl()
            val result: List<Provider> = useCase("", list)

            Assert.assertEquals(list, result)
        }
    }

    @Test
    fun searchProvidersOnNameExistReturnFilteredList() {
        runBlockingTest {
            val list: List<Provider> = mockList()

            useCase = SearchProvidersStarWithCaseSensitiveUseCaseImpl()
            val result: List<Provider> = useCase("Te", list)

            Assert.assertEquals(2, result.size)
            Assert.assertEquals("Test 1", result[0].name)
            Assert.assertEquals("Test 2", result[1].name)
        }
    }

    @Test
    fun searchProvidersOnNameNotExistReturnEmptyList() {
        runBlockingTest {
            val list: List<Provider> = mockList()

            useCase = SearchProvidersStarWithCaseSensitiveUseCaseImpl()
            val result: List<Provider> = useCase("Lu", list)

            Assert.assertTrue(result.isEmpty())
        }
    }

    private fun mockList(): List<Provider> {
        val gson = Gson()
        val provider1: Provider = gson.fromJson("{\"name\":\"Not Test\"}", Provider::class.java)
        val provider2: Provider = gson.fromJson("{\"name\":\"Test 1\"}", Provider::class.java)
        val provider3: Provider = gson.fromJson("{\"name\":\"Test 2\"}", Provider::class.java)

        return listOf(provider1, provider2, provider3)
    }
}