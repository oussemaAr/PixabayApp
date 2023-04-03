package io.studio.pixabayapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.studio.pixabayapp.data.HitsRepository
import io.studio.pixabayapp.domain.FetchHitsUseCase
import io.studio.pixabayapp.domain.LoadHitsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun providesFetchHitsUseCase(hitsRepository: HitsRepository) = FetchHitsUseCase(hitsRepository)

    @Provides
    @Singleton
    fun providesLoadHitsUseCase(hitsRepository: HitsRepository) = LoadHitsUseCase(hitsRepository)
}