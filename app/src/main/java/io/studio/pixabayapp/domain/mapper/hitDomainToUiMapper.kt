package io.studio.pixabayapp.domain.mapper

import io.studio.pixabayapp.data.model.HitEntityModel
import io.studio.pixabayapp.domain.model.HitDomainModel

fun HitEntityModel.toDomain() = HitDomainModel(
    id = this.id,
    thumbnail = this.thumbnail,
    image = this.image,
    username = this.username,
    likesCount = this.likesCount,
    downloadCount = this.downloadCount,
    commentCount = this.commentCount,
    tags = this.tags
)

fun List<HitEntityModel>.toDomainList() = this.map {
    it.toDomain()
}