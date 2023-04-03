package io.studio.pixabayapp.domain.model

data class HitDomainModel(
    val id: Int,
    val thumbnail: String,
    val image: String,
    val username: String,
    val likesCount: Int,
    val downloadCount: Int,
    val commentCount: Int,
    val tags: String
)