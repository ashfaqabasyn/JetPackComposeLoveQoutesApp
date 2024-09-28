package com.example.myloveqoutesapp.model

import kotlinx.serialization.Serializable



sealed class Screen() {
    @Serializable
    object SplashScreen
    @Serializable
    object HomeScreen
    @Serializable
    object QuotesScreen
    @Serializable
    object SpecificCategoryScreen
    @Serializable
    object CategoryScreen
    @Serializable
    object FavoriteScreen
    @Serializable
    object FavoriteListScreen
    @Serializable
    object LoveNovelsScreen
    @Serializable
    object PdfViewerScreen
}
