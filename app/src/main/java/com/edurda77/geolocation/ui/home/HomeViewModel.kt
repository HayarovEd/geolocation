package com.edurda77.geolocation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.geolocation.db.RepositoryDbImpl
import com.edurda77.geolocation.entity.MarkModel
import kotlinx.coroutines.launch

class HomeViewModel(private val repository:RepositoryDbImpl) : ViewModel() {

    private val _markData =
        MutableLiveData<StateHomeFragment>(StateHomeFragment.Empty)
    val markData = _markData
    init {
        _markData.value = StateHomeFragment.Loading
        viewModelScope.launch {
            try {
                _markData.value = StateHomeFragment.Success(repository.getMarkFromDb())
            } catch (error: Exception) {
                _markData.value = StateHomeFragment.Failure(error)
            }
        }
    }
     fun addMark(markModel: MarkModel) {
         viewModelScope.launch {
             repository.addMarkToDb(markModel)
         }
     }
}