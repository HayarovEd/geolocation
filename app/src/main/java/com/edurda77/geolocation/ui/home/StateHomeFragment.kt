package com.edurda77.geolocation.ui.home

import com.edurda77.geolocation.entity.MarkModel

sealed class StateHomeFragment {
    object Loading : StateHomeFragment()
    class Failure(val error: Exception) : StateHomeFragment()
    class Success(val data:List<MarkModel>) : StateHomeFragment()
    object Empty : StateHomeFragment()
}
