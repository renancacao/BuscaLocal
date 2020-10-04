package com.rcacao.localsearch.di

import com.rcacao.localsearch.data.datasource.DataSource
import com.rcacao.localsearch.data.datasource.LocalDataSourceImpl
import com.rcacao.localsearch.data.repository.ProvidersRepository
import com.rcacao.localsearch.data.repository.ProvidersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindProvidersRepository(repository: ProvidersRepositoryImpl): ProvidersRepository

    @Singleton
    @Binds
    abstract fun bindLocalDataSource(dataSource: LocalDataSourceImpl): DataSource

}
