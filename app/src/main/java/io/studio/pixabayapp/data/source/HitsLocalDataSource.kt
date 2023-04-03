package io.studio.pixabayapp.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.studio.pixabayapp.data.model.HitEntityModel
import kotlinx.coroutines.flow.Flow

@Dao
interface HitsLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveHits(hits: List<HitEntityModel>)

    @Query("SELECT * FROM hits ORDER by addedAt")
    fun loadHits(): Flow<List<HitEntityModel>>

    @Query("DELETE FROM hits")
    suspend fun clear()
}