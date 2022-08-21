package com.edurda77.geolocation.di

import androidx.room.Room
import com.edurda77.geolocation.db.DataBaseMark
import com.edurda77.geolocation.db.MarkDao
import com.edurda77.geolocation.db.RepositoryDbImpl
import com.edurda77.geolocation.ui.dashboard.DashboardViewModel
import com.edurda77.geolocation.ui.home.HomeViewModel
import com.edurda77.geolocation.utils.DATABASE_NAME
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            get(), DataBaseMark::class.java,
            DATABASE_NAME
        ).build()
    }
    single { get<DataBaseMark>().markDao()}
    fun provideUserRepositoryDb (markDao: MarkDao): RepositoryDbImpl {
        return RepositoryDbImpl(markDao)
    }
    single { provideUserRepositoryDb(get()) }
}
val mapViewModelModule = module {
    viewModel { HomeViewModel(get()) }
}

val listViewModelModule = module {
    //viewModel { DashboardViewModel(get()) }
}