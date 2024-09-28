//package com.example.myloveqoutesapp.screens
//
//import android.widget.Toast
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import com.example.myloveqoutesapp.WallpaperViewModel
//import com.example.myloveqoutesapp.domain.ui_layer.WallpaperModel
//
//@Composable
//fun FavoriteScreen(navController: NavController) {
//    val wallpaperViewModel: WallpaperViewModel = viewModel() // Correct usage
//
//    val context = LocalContext.current
//
//    // State variables for user input
//    var wallpaperId by remember { mutableIntStateOf(0) } // Assuming ID is of type Int
//    var wallpaperName by remember { mutableStateOf("") }
//    var isFavorite by remember { mutableStateOf(false) } // Checkbox state for favorite
//    var showToast by remember { mutableStateOf(false) } // Track whether to show toast
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // TextField for wallpaper name
//        TextField(
//            value = wallpaperName,
//            onValueChange = { wallpaperName = it },
//            label = { Text("Wallpaper Name") }
//        )
//
//        // Checkbox for favorite
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Checkbox(checked = isFavorite, onCheckedChange = { isFavorite = it })
//            Text(text = "Is Favorite")
//        }
//
//        // Button to insert wallpaper
//        Button(onClick = {
//            val newWallpaper = WallpaperModel(wallpaperName = 12, isFavorite = isFavorite)
//            wallpaperViewModel.insertWallpaper(newWallpaper)
//            wallpaperName = ""
//            wallpaperId = 0
//            isFavorite = false // Reset the checkbox
//            showToast = true // Set flag to show toast
//        }) {
//            Text("Insert Wallpaper")
//        }
//
//        // TextField for wallpaper ID to update or delete
//        TextField(
//            value = wallpaperId.toString(),
//            onValueChange = { wallpaperId = it.toIntOrNull() ?: 0 },
//            label = { Text("Wallpaper ID to Update/Delete") }
//        )
//
//        // Button to update wallpaper
//        Button(onClick = {
//            val updatedWallpaper = WallpaperModel(wallpaperName = 990, isFavorite = isFavorite)
//            wallpaperViewModel.updateWallpaper(updatedWallpaper)
//            wallpaperName = ""
//            wallpaperId = 0
//            isFavorite = false // Reset the checkbox
//        }) {
//            Text("Update Wallpaper")
//        }
//
//        // Button to delete wallpaper
//        Button(onClick = {
//            wallpaperViewModel.deleteWallpaper(WallpaperModel(wallpaperName = 1234, isFavorite = isFavorite))
//            wallpaperId = 0 // Reset ID
//        }) {
//            Text("Delete Wallpaper")
//        }
//
//        // Optional: Display current wallpapers
//        WallpaperList(viewModel = wallpaperViewModel)
//    }
//
//    // Show toast after insertion
//    if (showToast) {
//        LaunchedEffect(showToast) {
//            Toast.makeText(context, "Inserted Successfully", Toast.LENGTH_SHORT).show()
//            showToast = false // Reset flag
//        }
//    }
//}
//
//@Composable
//fun WallpaperList(viewModel: WallpaperViewModel) {
//    // Collect wallpapers state
//    val wallpapers by viewModel.wallpapers.collectAsState()
//
//    LazyColumn {
//        items(wallpapers) { wallpaper ->
//            Text(text = "${wallpaper.wallpaperName} (Favorite: ${wallpaper.isFavorite})")
//        }
//    }
//}
