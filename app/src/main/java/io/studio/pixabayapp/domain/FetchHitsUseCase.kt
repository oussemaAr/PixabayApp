package io.studio.pixabayapp.domain

import io.studio.pixabayapp.data.HitsRepository
import javax.inject.Inject

class FetchHitsUseCase @Inject constructor(
    private val repository: HitsRepository
) {

    suspend operator fun invoke(query: String) {
        repository.fetchHits(query)
    }
}