package com.example.myloveqoutesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.myloveqoutesapp.domain.ui_layer.WallpaperModel
import com.example.myloveqoutesapp.domain.ui_layer.WallpaperRepository
import com.example.myloveqoutesapp.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


 class WallpaperViewModel(private val repository: WallpaperRepository) : ViewModel() {

    // StateFlow to represent the UI state
    private val _wallpaperState = MutableStateFlow<UiState<List<WallpaperModel>>>(UiState.Loading)
     val wallpaperState: StateFlow<UiState<List<WallpaperModel>>> = _wallpaperState

    init {
        fetchWallpapers()
    }

    // Function to fetch wallpapers and update the UI state
    fun fetchWallpapers() {
        viewModelScope.launch {
            try {
                // Collect the Flow from the repository
                repository.getAllWallpapers().collect { wallpaperList ->
                    _wallpaperState.value = UiState.Success(wallpaperList) // Update the state with the list
                }
            } catch (e: Exception) {
                _wallpaperState.value = UiState.Error("Failed to load wallpapers: ${e.message}") // Handle error state
            }
        }
    }

    // Function to insert or update a wallpaper
     fun insertOrUpdateWallpaper(wallpaper: WallpaperModel) {
        viewModelScope.launch {
            if (repository.getWallpaperById(wallpaper.wallpaperName)?.isFavorite == true) {
                repository.deleteWallpaper(wallpaper)
            } else {
                repository.insertWallpaper(wallpaper)
            }
        }
    }

    // Function to check if a wallpaper is marked as favorite
     fun isWallpaperFavorite(wallpaperId: Int): LiveData<Boolean> {
        return liveData {
            val wallpaper = repository.getWallpaperById(wallpaperId)
            emit(wallpaper?.isFavorite ?: false)
        }
    }
}



