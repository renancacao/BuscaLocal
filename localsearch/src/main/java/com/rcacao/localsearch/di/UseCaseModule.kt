package com.rcacao.localsearch.di

import com.rcacao.localsearch.domain.usecase.GetProvidersUseCase
import com.rcacao.localsearch.domain.usecase.GetProvidersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetProvidersUseCase(useCase: GetProvidersUseCaseImpl): GetProvidersUseCase

}
