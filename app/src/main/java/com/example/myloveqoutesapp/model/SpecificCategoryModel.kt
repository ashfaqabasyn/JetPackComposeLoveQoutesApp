package com.example.myloveqoutesapp.model

import com.example.myloveqoutesapp.domain.ui_layer.WallpaperModel
import kotlinx.serialization.Serializable

@Serializable
//@Parcelize
data class SpecificCategoryModel(val categoryName: String, val wallpaperName: List<WallpaperModel>)



