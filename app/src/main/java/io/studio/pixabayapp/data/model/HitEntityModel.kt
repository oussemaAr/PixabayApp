package io.studio.pixabayapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hits")
data class HitEntityModel(
    @PrimaryKey
    val id: Int,
    val thumbnail: String,
    val image: String,
    val username: String,
    val likesCount: Int,
    val downloadCount: Int,
    val commentCount: Int,
    val tags: String,
    val addedAt: Long = System.currentTimeMillis()
)