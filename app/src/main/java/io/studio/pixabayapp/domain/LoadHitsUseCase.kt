package io.studio.pixabayapp.domain

import io.studio.pixabayapp.data.HitsRepository
import io.studio.pixabayapp.domain.mapper.toDomainList
import io.studio.pixabayapp.domain.model.HitDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadHitsUseCase @Inject constructor(
    private val repository: HitsRepository
) {

    operator fun invoke(): Flow<List<HitDomainModel>> =
        repository.loadHits().map { it.toDomainList() }
}