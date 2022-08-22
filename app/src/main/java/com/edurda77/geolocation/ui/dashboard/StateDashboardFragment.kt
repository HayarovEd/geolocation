package com.edurda77.geolocation.ui.dashboard

import com.edurda77.geolocation.entity.MarkModel

sealed class StateDashboardFragment {
    object Loading : StateDashboardFragment()
    class Failure(val error: Exception) : StateDashboardFragment()
    class Success(val data:List<MarkModel>) : StateDashboardFragment()
    object Empty : StateDashboardFragment()
}