package com.edurda77.geolocation.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edurda77.geolocation.entity.MarkModel


@Database(
    version = 1,
    entities = [MarkModel::class],
    exportSchema = false
)
abstract class DataBaseMark : RoomDatabase() {
    abstract fun markDao(): MarkDao


}