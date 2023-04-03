package io.studio.pixabayapp.data.source

import io.studio.pixabayapp.common.data.NetworkClient
import io.studio.pixabayapp.data.model.HitsApiResponse

class HitsRemoteDataSource(
    private val client: NetworkClient
) {

    suspend fun fetchImages(query: String) = runCatching {
        client.GET<HitsApiResponse>(query = query)
    }
}