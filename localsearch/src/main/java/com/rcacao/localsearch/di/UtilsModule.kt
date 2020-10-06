package com.rcacao.localsearch.di

import com.rcacao.localsearch.utils.JsonHelper
import com.rcacao.localsearch.utils.JsonHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class UtilsModule {

    @Singleton
    @Binds
    abstract fun bindJsonHelper(helper: JsonHelperImpl): JsonHelper

}
