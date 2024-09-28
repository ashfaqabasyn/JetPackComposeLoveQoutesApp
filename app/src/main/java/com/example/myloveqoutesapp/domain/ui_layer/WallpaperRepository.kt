package com.example.myloveqoutesapp.domain.ui_layer

import kotlinx.coroutines.flow.Flow

interface WallpaperRepository {
    fun getAllWallpapers(): Flow<List<WallpaperModel>>
    suspend fun getWallpaperById(wallpaperName: Int): WallpaperModel?
    suspend fun insertWallpaper(wallpaper: WallpaperModel)
    suspend fun deleteWallpaper(wallpaper: WallpaperModel)
    suspend fun updateWallpaper(wallpaper: WallpaperModel)
}
