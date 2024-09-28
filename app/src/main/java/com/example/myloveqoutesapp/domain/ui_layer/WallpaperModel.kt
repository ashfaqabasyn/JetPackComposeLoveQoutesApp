package com.example.myloveqoutesapp.domain.ui_layer

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "items")
data class WallpaperModel(
    @PrimaryKey
    val wallpaperName: Int,
    val isFavorite: Boolean = false)


