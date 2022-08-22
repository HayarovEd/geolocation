package com.edurda77.geolocation.ui.mark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.geolocation.db.RepositoryDbImpl
import kotlinx.coroutines.launch

class MarkViewModel(private val repository: RepositoryDbImpl) : ViewModel() {
    fun updateMark(id: Int, title:String, annotation: String) {
        viewModelScope.launch {
            repository.updateMarkInDb(id, title, annotation)
        }
    }
}