package com.rcacao.localsearch.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.rcacao.localsearch.data.model.Provider
import com.rcacao.localsearch.domain.model.GetProvidersResult
import com.rcacao.localsearch.domain.usecase.GetProvidersUseCase
import com.rcacao.localsearch.domain.usecase.SearchProvidersUseCase
import com.rcacao.localsearch.view.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProvidersListViewModelTest {

    private lateinit var viewModel: ProvidersListViewModel
    private val getProvidersUseCase: GetProvidersUseCase = mock()
    private val searchProvidersUseCase: SearchProvidersUseCase = mock()

    private var observerVisibility: Observer<Boolean> = mock()
    private var observerProviders: Observer<List<Provider>> = mock()
    private var observerError: Observer<Event<String>> = mock()
    private var observerText: Observer<String> = mock()

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun loadProviders_OnSuccess_returnList() {
        runBlockingTest {
            val list: List<Provider> = listOf(Provider(), Provider())
            whenever(getProvidersUseCase.invoke()).thenReturn(
                GetProvidersResult.ProvidersLoaded(
                    list
                )
            )

            viewModel = ProvidersListViewModel(getProvidersUseCase, searchProvidersUseCase)
            viewModel.providers.observeForever(observerProviders)

            verify(observerProviders).onChanged(list)
        }
    }

    @Test
    fun searchProviders_OnSuccess_returnList() {
        runBlockingTest {
            val list: List<Provider> = listOf(Provider(), Provider())
            whenever(searchProvidersUseCase.invoke(any(), any())).thenReturn(list)

            viewModel = ProvidersListViewModel(getProvidersUseCase, searchProvidersUseCase)
            viewModel.search("teste")
            viewModel.providers.observeForever(observerProviders)

            verify(observerProviders).onChanged(list)
        }
    }

    @Test
    fun loadProviders_OnError_returnMessage() {
        runBlockingTest {
            whenever(getProvidersUseCase.invoke()).thenReturn(GetProvidersResult.Error("error message"))

            viewModel = ProvidersListViewModel(getProvidersUseCase, searchProvidersUseCase)
            viewModel.error.observeForever(observerError)

            verify(observerError, times(1)).onChanged(any())
        }
    }

    @Test
    fun searchProviders_returnText() {
        runBlockingTest {
            whenever(searchProvidersUseCase.invoke(any(), any())).thenReturn(emptyList())

            viewModel = ProvidersListViewModel(getProvidersUseCase, searchProvidersUseCase)
            viewModel.searchText.observeForever(observerText)
            viewModel.search("teste")

            verify(observerText).onChanged("teste")
        }
    }

    @Test
    fun loadProviders_OnSuccess_listIsVisible() {
        runBlockingTest {
            whenever(getProvidersUseCase.invoke()).thenReturn(
                GetProvidersResult.ProvidersLoaded(
                    emptyList()
                )
            )

            viewModel = ProvidersListViewModel(getProvidersUseCase, searchProvidersUseCase)
            viewModel.isLoaded.observeForever(observerVisibility)

            verify(observerVisibility).onChanged(true)
        }
    }

    @Test
    fun loadProviders_OnSuccess_loadingIsInvisible() {
        runBlockingTest {
            whenever(getProvidersUseCase.invoke()).thenReturn(
                GetProvidersResult.ProvidersLoaded(
                    emptyList()
                )
            )

            viewModel = ProvidersListViewModel(getProvidersUseCase, searchProvidersUseCase)
            viewModel.isLoading.observeForever(observerVisibility)

            verify(observerVisibility).onChanged(false)
        }
    }

    @Test
    fun loadProviders_OnSuccess_buttonIsInvisible() {
        runBlockingTest {
            whenever(getProvidersUseCase.invoke()).thenReturn(
                GetProvidersResult.ProvidersLoaded(
                    emptyList()
                )
            )

            viewModel = ProvidersListViewModel(getProvidersUseCase, searchProvidersUseCase)
            viewModel.isError.observeForever(observerVisibility)

            verify(observerVisibility).onChanged(false)
        }
    }

    @Test
    fun loadProviders_OnError_listIsInvisible() {
        runBlockingTest {
            whenever(getProvidersUseCase.invoke()).thenReturn(GetProvidersResult.Error("error"))

            viewModel = ProvidersListViewModel(getProvidersUseCase, searchProvidersUseCase)
            viewModel.isLoaded.observeForever(observerVisibility)

            verify(observerVisibility).onChanged(false)
        }
    }

    @Test
    fun loadProviders_OnError_loadingIsInvisible() {
        runBlockingTest {
            whenever(getProvidersUseCase.invoke()).thenReturn(GetProvidersResult.Error("error"))

            viewModel = ProvidersListViewModel(getProvidersUseCase, searchProvidersUseCase)
            viewModel.isLoading.observeForever(observerVisibility)

            verify(observerVisibility).onChanged(false)
        }
    }

    @Test
    fun loadProviders_OnSuccess_buttonIsVisible() {
        runBlockingTest {
            whenever(getProvidersUseCase.invoke()).thenReturn(GetProvidersResult.Error("error"))

            viewModel = ProvidersListViewModel(getProvidersUseCase, searchProvidersUseCase)
            viewModel.isError.observeForever(observerVisibility)

            verify(observerVisibility).onChanged(true)
        }
    }


}