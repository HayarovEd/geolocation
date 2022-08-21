package com.edurda77.geolocation.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edurda77.geolocation.entity.MarkModel
import com.edurda77.geolocation.utils.DATABASE_NAME


@Database(
    version = 1,
    entities = [MarkModel::class],
    exportSchema = false
)
abstract class DataBaseMark : RoomDatabase() {
    abstract fun markDao(): MarkDao

    companion object {

        @Volatile
        private var instanse: DataBaseMark? = null

        fun getDatabase(context: Context): DataBaseMark {
            return instanse ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseMark::class.java,
                    DATABASE_NAME
                ).build()
                instanse = instance
                instance
            }
        }
    }
}