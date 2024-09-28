package com.example.myloveqoutesapp

import android.app.Application
import com.example.myloveqoutesapp.framework_layer.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class WallpaperApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WallpaperApp)
            modules(appModule)
        }
    }
}
