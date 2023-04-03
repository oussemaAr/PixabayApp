package io.studio.pixabayapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HitsApiResponse(
    @SerialName("hits")
    val hits: List<HitApiModel>,
    @SerialName("total")
    val total: Int,
    @SerialName("totalHits")
    val totalHits: Int
)