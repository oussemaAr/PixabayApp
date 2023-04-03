package io.studio.pixabayapp.data.mapper

import io.studio.pixabayapp.data.model.HitApiModel
import io.studio.pixabayapp.data.model.HitEntityModel

fun HitApiModel.toLocalModel() = HitEntityModel(
    id = this.id,
    thumbnail = this.previewURL,
    image = this.largeImageURL,
    username = this.user,
    likesCount = this.likes,
    downloadCount = this.downloads,
    commentCount = this.comments,
    tags = this.tags
)

fun List<HitApiModel>.toLocatlModelList() = this.map {
    it.toLocalModel()
}