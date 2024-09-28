package com.example.myloveqoutesapp.framework_layer

import com.example.myloveqoutesapp.WallpaperViewModel
import com.example.myloveqoutesapp.data_layer.WallpaperDatabase
import com.example.myloveqoutesapp.data_layer.WallpaperRepositoryImpl
import com.example.myloveqoutesapp.domain.ui_layer.WallpaperRepository
import com.example.myloveqoutesapp.model.WallpaperViewModelFactory
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val appModule = module {
    // Database
    single { WallpaperDatabase.getDatabase(get()) }
    // DAOs
    single { get<WallpaperDatabase>().wallpaperDao() }
    // Repositories
    single<WallpaperRepository> { WallpaperRepositoryImpl(get()) }
    single { WallpaperDatabase.getDatabase(get()) } // Database
    single { get<WallpaperDatabase>().wallpaperDao() } // DAO
    single<WallpaperRepository> { WallpaperRepositoryImpl(get()) } // Repository
    viewModel{WallpaperViewModel(get())}
    single<WallpaperViewModelFactory> { WallpaperViewModelFactory(get())}

}
