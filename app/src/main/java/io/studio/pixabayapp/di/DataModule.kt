package io.studio.pixabayapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.studio.pixabayapp.common.data.NetworkClient
import io.studio.pixabayapp.data.HitsDatabase
import io.studio.pixabayapp.data.HitsRepository
import io.studio.pixabayapp.data.source.HitsLocalDataSource
import io.studio.pixabayapp.data.source.HitsRemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesHitsRepository(
        remoteDataSource: HitsRemoteDataSource,
        localDataSource: HitsLocalDataSource
    ) = HitsRepository(remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun providesHitsRemoteDataSource() = HitsRemoteDataSource(NetworkClient)

    @Provides
    @Singleton
    fun providesHitsLocalDataSource(database: HitsDatabase) = database.hitsLocalDataSource()

    @Provides
    @Singleton
    fun providesHitsDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        name = "hit_databse",
        klass = HitsDatabase::class.java
    ).fallbackToDestructiveMigration().build()
}