package com.edurda77.geolocation.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edurda77.geolocation.utils.*
import java.io.Serializable

@Entity(tableName = TABLE)
data class MarkModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(ID)
    val id : Int,
    @ColumnInfo(LONGITUDE)
    val longitudeMark: Double,
    @ColumnInfo(LATITUDE)
    val latitudeMark: Double,
    @ColumnInfo(TITLE)
    val titleMark: String? = null,
    @ColumnInfo(ANNOTATION)
    val annotationMark: String? = null,
) : Serializable
