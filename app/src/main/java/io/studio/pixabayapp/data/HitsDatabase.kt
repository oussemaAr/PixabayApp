package io.studio.pixabayapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import io.studio.pixabayapp.data.model.HitEntityModel
import io.studio.pixabayapp.data.source.HitsLocalDataSource

@Database(entities = [HitEntityModel::class], version = 1, exportSchema = false)
abstract class HitsDatabase : RoomDatabase() {
    abstract fun hitsLocalDataSource() : HitsLocalDataSource
}