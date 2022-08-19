package com.edurda77.geolocation

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("a6d6e6e9-26d6-47e6-9ba9-95d317423e3a")
    }
}