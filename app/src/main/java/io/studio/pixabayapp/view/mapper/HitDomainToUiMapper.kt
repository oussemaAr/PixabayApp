package io.studio.pixabayapp.view.mapper

import io.studio.pixabayapp.domain.model.HitDomainModel
import io.studio.pixabayapp.view.model.HitUiModel

fun HitDomainModel.toUi() = HitUiModel(
    id = this.id,
    thumbnail = this.thumbnail,
    image = this.image,
    username = this.username,
    likesCount = this.likesCount,
    downloadCount = this.downloadCount,
    commentCount = this.commentCount,
    tags = this.tags
)

fun List<HitDomainModel>.toUiList() = this.map {
    it.toUi()
}