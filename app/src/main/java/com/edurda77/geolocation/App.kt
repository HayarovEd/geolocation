package com.edurda77.geolocation

import android.app.Application
import com.edurda77.geolocation.di.listViewModelModule
import com.edurda77.geolocation.di.mapViewModelModule
import com.edurda77.geolocation.di.markViewModelModule
import com.edurda77.geolocation.di.roomModule
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    mapViewModelModule,
                    roomModule,
                    listViewModelModule,
                    markViewModelModule
                )
            )
        }

        MapKitFactory.setApiKey("a6d6e6e9-26d6-47e6-9ba9-95d317423e3a")
    }
}