package com.edurda77.geolocation.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.geolocation.db.RepositoryDbImpl
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: RepositoryDbImpl) : ViewModel() {

    private val _markData =
        MutableLiveData<StateDashboardFragment>(StateDashboardFragment.Empty)
    val markData = _markData
    fun getData() {
        _markData.value = StateDashboardFragment.Loading
        viewModelScope.launch {
            try {
                _markData.value = StateDashboardFragment.Success(repository.getMarkFromDb())
            } catch (error: Exception) {
                _markData.value = StateDashboardFragment.Failure(error)
            }
        }
    }
    fun deleteMarkFromDb(id: Int) {
        viewModelScope.launch {
            repository.deleteMarkFromDb(id)
        }
    }
}