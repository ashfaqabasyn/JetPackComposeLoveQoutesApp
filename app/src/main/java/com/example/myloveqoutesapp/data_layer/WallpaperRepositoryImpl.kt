package com.example.myloveqoutesapp.data_layer

import com.example.myloveqoutesapp.domain.ui_layer.WallpaperModel
import com.example.myloveqoutesapp.domain.ui_layer.WallpaperRepository
import kotlinx.coroutines.flow.Flow

class WallpaperRepositoryImpl(private val dao: WallpaperDao) : WallpaperRepository {
    override fun getAllWallpapers(): Flow<List<WallpaperModel>> = dao.getAllWallpapers()

    override suspend fun getWallpaperById(wallpaperName: Int): WallpaperModel? = dao.getWallpaperById(wallpaperName)

    override suspend fun insertWallpaper(wallpaper: WallpaperModel) {
        dao.insertWallpaper(wallpaper)
    }

    override suspend fun deleteWallpaper(wallpaper: WallpaperModel) {
        dao.deleteWallpaper(wallpaper)
    }

    override suspend fun updateWallpaper(wallpaper: WallpaperModel) {
        dao.updateWallpaper(wallpaper)
    }
}
