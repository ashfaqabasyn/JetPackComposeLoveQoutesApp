package com.example.myloveqoutesapp.data_layer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myloveqoutesapp.domain.ui_layer.WallpaperModel
import kotlinx.coroutines.flow.Flow



@Dao
interface WallpaperDao {
    @Query("SELECT * FROM items")
    fun getAllWallpapers(): Flow<List<WallpaperModel>>

    @Query("SELECT * FROM items WHERE wallpaperName = :wallpaperName LIMIT 1")
    suspend fun getWallpaperById(wallpaperName: Int): WallpaperModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWallpaper(wallpaper: WallpaperModel)

    @Delete
    suspend fun deleteWallpaper(wallpaper: WallpaperModel)

    @Update
    suspend fun updateWallpaper(wallpaper: WallpaperModel)
}
