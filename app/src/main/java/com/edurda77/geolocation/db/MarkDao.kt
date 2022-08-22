package com.edurda77.geolocation.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edurda77.geolocation.entity.MarkModel
import com.edurda77.geolocation.utils.ANNOTATION
import com.edurda77.geolocation.utils.ID
import com.edurda77.geolocation.utils.TABLE
import com.edurda77.geolocation.utils.TITLE

@Dao
interface MarkDao {
    @Insert(entity = MarkModel::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMark(markModel: MarkModel)
    @Query("DELETE FROM $TABLE WHERE $ID=:id")
    suspend fun deleteMark(id: Int)
    @Query("UPDATE $TABLE SET $TITLE=:title, $ANNOTATION=:annotation  WHERE $ID=:id")
    suspend fun updateMark(id: Int, title:String, annotation: String)
    @Query("SELECT * FROM $TABLE")
    suspend fun getMarks(): List<MarkModel>
}