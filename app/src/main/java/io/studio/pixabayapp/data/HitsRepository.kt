package io.studio.pixabayapp.data

import io.studio.pixabayapp.data.mapper.toLocatlModelList
import io.studio.pixabayapp.data.model.HitEntityModel
import io.studio.pixabayapp.data.source.HitsLocalDataSource
import io.studio.pixabayapp.data.source.HitsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HitsRepository @Inject constructor(
    private val remoteDataSource: HitsRemoteDataSource,
    private val localDataSource: HitsLocalDataSource,
) {

    private var currentQuery = "fruits"
    suspend fun fetchHits(query: String) {
        val response = remoteDataSource.fetchImages(query = query)
        if (query != currentQuery) {
            localDataSource.clear()
            currentQuery = query
        }
        if (response.isSuccess) {
            localDataSource.saveHits(response.getOrNull()?.hits?.toLocatlModelList().orEmpty())
        }
    }

    fun loadHits(): Flow<List<HitEntityModel>> = localDataSource.loadHits()
}