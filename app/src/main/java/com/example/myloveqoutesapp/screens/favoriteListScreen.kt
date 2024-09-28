package com.example.myloveqoutesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.myloveqoutesapp.R
import com.example.myloveqoutesapp.WallpaperViewModel
import com.example.myloveqoutesapp.domain.ui_layer.WallpaperModel
import com.example.myloveqoutesapp.model.UiState
import org.koin.androidx.compose.getViewModel


@Composable
fun FavoriteListScreen(navController: NavController) {
    val wallpaperViewModel: WallpaperViewModel = getViewModel()
    PhotoViewer(wallpaperViewModel = wallpaperViewModel)
}


@Composable
fun PhotoViewer(wallpaperViewModel: WallpaperViewModel) {
    val wallpaperState by wallpaperViewModel.wallpaperState.collectAsState()

    when (wallpaperState) {
        is UiState.Loading -> {
            // Display loading UI
//            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        is UiState.Error -> {
            Text(
                text = (wallpaperState as UiState.Error).message,
                color = Color.Red,
            )
        }

        is UiState.Success -> {
            val wallpapers = (wallpaperState as UiState.Success).data

            // Check if the list of wallpapers is empty
            if (wallpapers.isEmpty()) {
                Text(
                    text = "List is empty",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center) // Center the message
                )
            } else {
                // Display the wallpapers in a LazyColumn
                LazyColumn {
                    items(wallpapers) { wallpaperModel -> // Pass the WallpaperModel instance directly
                        WallpaperItem(wallpaperModel = wallpaperModel, wallpaperViewModel = wallpaperViewModel)
                    }
                }
            }
        }
    }
}



@Composable
private fun WallpaperItem(
    wallpaperModel: WallpaperModel,
    wallpaperViewModel: WallpaperViewModel
) {
    Box(
        modifier = Modifier.fillMaxWidth().height(350.dp) // Ensure Box takes full width
            .background(Color.Red)

    ) {
        // Image with rounded corners
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clip(RoundedCornerShape(16.dp)), // Apply rounded corners
            painter = painterResource(wallpaperModel.wallpaperName),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
        )

        // ConstraintLayout at the bottom of the Box
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // Align at the bottom of the Box
                .height(35.dp) // Set height of ConstraintLayout
        ) {
            val (row) = createRefs()

            // Row inside the ConstraintLayout
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth().constrainAs(row) {
                    bottom.linkTo(parent.bottom) // Pin to the bottom of the ConstraintLayout

                }

            ) {
                Image(
                    painter = painterResource(id = R.drawable.btn_like_fill), // Replace with your drawable
                    contentDescription = "Image 1",
                    modifier = Modifier.size(24.dp) // Set size as needed
                        .clickable(){
                            wallpaperViewModel.insertOrUpdateWallpaper(wallpaperModel)
                        }
                )

                // Second Image
                Image(
                    painter = painterResource(id = R.drawable.btn_share), // Replace with your drawable
                    contentDescription = "Image 2",
                    modifier = Modifier.size(24.dp) // Set size as needed
                )

                // Third Image
                Image(
                    painter = painterResource(id = R.drawable.btn_save), // Replace with your drawable
                    contentDescription = "Image 3",
                    modifier = Modifier.size(24.dp) // Set size as needed
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = false)
fun FavoriteListScreenPreview() {
//    WallpaperItem(null,null)
}











