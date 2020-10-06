package com.rcacao.localsearch.domain.usecase

import com.rcacao.localsearch.data.model.Provider
import javax.inject.Inject

class SearchProvidersStarWithCaseSensitiveUseCaseImpl @Inject constructor() :
    SearchProvidersUseCase {

    override suspend operator fun invoke(text: String, list: List<Provider>): List<Provider> =
        if (text.isEmpty()) {
            list
        } else {
            list.filter { it.name.startsWith(text) }
        }

}
