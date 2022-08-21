package com.edurda77.geolocation.db

import com.edurda77.geolocation.entity.MarkModel

class RepositoryDbImpl (private val markDao: MarkDao):RepositoryDb {
    override suspend fun addMarkToDb(markModel: MarkModel) {
        markDao.addMark(markModel)
    }

    override suspend fun updateMarkInDb(id: Int, title: String, annotation: String) {
        markDao.updateMark(id, title, annotation)
    }

    override suspend fun deleteMarkFromDb(id: Int) {
        markDao.deleteMark(id)
    }

    override suspend fun getMarkFromDb(): List<MarkModel> {
        return markDao.getMarks()
    }
}