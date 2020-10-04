package com.rcacao.localsearch.view.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rcacao.localsearch.data.model.Provider
import com.rcacao.localsearch.domain.model.GetProvidersResult
import com.rcacao.localsearch.domain.usecase.GetProvidersUseCase
import com.rcacao.localsearch.view.model.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProvidersListViewModel @ViewModelInject @Inject constructor(private val getProviderUseCase: GetProvidersUseCase) :
    ViewModel() {

    private val mutableProviders: MutableLiveData<List<Provider>> = MutableLiveData()
    private val mutableSearchText: MutableLiveData<String> = MutableLiveData()
    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val mutableIsLoaded: MutableLiveData<Boolean> = MutableLiveData()
    private val mutableIsError: MutableLiveData<Boolean> = MutableLiveData()
    private val mutableError: MutableLiveData<Event<String>> = MutableLiveData()

    val providers: LiveData<List<Provider>> get() = mutableProviders
    val searchText: LiveData<String> get() = mutableSearchText
    val isLoading: LiveData<Boolean> get() = mutableIsLoading
    val isLoaded: LiveData<Boolean> get() = mutableIsLoaded
    val isError: LiveData<Boolean> get() = mutableIsError
    val error: LiveData<Event<String>> get() = mutableError

    init {
        loadProviders()
    }

    fun loadProviders() {
        isLoading()
        viewModelScope.launch {
            when (val result: GetProvidersResult = getProviderUseCase()) {
                is GetProvidersResult.ProvidersLoaded -> isLoaded(result.providers)
                is GetProvidersResult.Error -> isError(result.errorMessage)
            }
        }
    }

    private fun isLoading() {
        mutableIsLoading.value = true
        mutableIsError.value = false
    }

    private fun isLoaded(providers: List<Provider>) {
        mutableIsError.value = false
        mutableIsLoading.value = false
        mutableIsLoaded.value = true
        mutableSearchText.value = ""
        mutableProviders.value = providers
    }

    private fun isError(errorMessage: String) {
        mutableIsError.value = true
        mutableIsLoading.value = false
        mutableIsLoaded.value = false
        mutableError.value = Event(errorMessage)
    }
}