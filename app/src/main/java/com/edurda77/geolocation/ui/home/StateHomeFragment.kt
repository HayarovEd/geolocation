package com.edurda77.geolocation.ui.home

sealed class StateHomeFragment {
    object Loading : StateHomeFragment()
    class Failure(val msg:Throwable) : StateHomeFragment()
    class SortByName(val data:List<String>) : StateHomeFragment()
}
