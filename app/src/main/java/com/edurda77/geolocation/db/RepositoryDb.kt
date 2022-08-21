package com.edurda77.geolocation.db

import com.edurda77.geolocation.entity.MarkModel

interface RepositoryDb {
    suspend fun addMarkToDb (markModel: MarkModel)
    suspend fun updateMarkInDb (id: Int, title:String, annotation: String)
    suspend fun deleteMarkFromDb (id: Int)
    suspend fun getMarkFromDb () : List<MarkModel>
}