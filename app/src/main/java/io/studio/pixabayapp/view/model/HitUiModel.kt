package io.studio.pixabayapp.view.model

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class HitUiModel(
    val id: Int,
    val thumbnail: String,
    val image: String,
    val username: String,
    val likesCount: Int,
    val downloadCount: Int,
    val commentCount: Int,
    val tags: String
) : Serializable

object HitDiffUtil : DiffUtil.ItemCallback<HitUiModel>() {
    override fun areItemsTheSame(oldItem: HitUiModel, newItem: HitUiModel) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: HitUiModel, newItem: HitUiModel) =
        oldItem.id == newItem.id
}