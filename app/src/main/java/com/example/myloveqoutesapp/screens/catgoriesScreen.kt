package com.example.myloveqoutesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.myloveqoutesapp.domain.ui_layer.WallpaperModel
import com.example.myloveqoutesapp.R

@Composable
fun AllCategoriesScreen(navController: NavController) {
    val listWallpaperModel = listOf(
        WallpaperModel(R.drawable.ic1_valentine, false),
        WallpaperModel(R.drawable.ic3_breakup, false),
        WallpaperModel(R.drawable.ic4_brokenheart, false),
        WallpaperModel(R.drawable.ic5_crush, false),
        WallpaperModel(R.drawable.ic6_cute, false),
        WallpaperModel(R.drawable.ic7_forever, false),
        WallpaperModel(R.drawable.ic8_funny, false),
        WallpaperModel(R.drawable.ic9_kiss, false),
        WallpaperModel(R.drawable.ic10_long, false),
        WallpaperModel(R.drawable.ic16_love_messeges, false),
        WallpaperModel(R.drawable.ic11_love_quotes, false),
        WallpaperModel(R.drawable.ic12_love_saying, false),
        WallpaperModel(R.drawable.ic13_pickup, false),
        WallpaperModel(R.drawable.ic14_romantic, false),
        WallpaperModel(R.drawable.ic15_sad, false),
        WallpaperModel(R.drawable.ic17_sorry, false)
    )




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
    ) {
        // Background Image
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_mm_bg),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
        )

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (appBar, grid) = createRefs()

            // AppBar layout
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color.Transparent)
                    .constrainAs(appBar) {
                        top.linkTo(parent.top)
                    }
            ) {
                val (startImage, endImage1, endImage2) = createRefs()

                // Start Image
                Image(
                    painter = painterResource(id = R.drawable.ic_menu_16),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 2.dp)
                        .constrainAs(startImage) {
                            start.linkTo(parent.start)
                            centerVerticallyTo(parent)
                        }
                )

                // End Images (bell and favorite)
                Image(
                    painter = painterResource(id = R.drawable.ic_bell),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 2.dp)
                        .constrainAs(endImage1) {
                            end.linkTo(parent.end)
                            centerVerticallyTo(parent)
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 2.dp)
                        .constrainAs(endImage2) {
                            end.linkTo(endImage1.start)
                            centerVerticallyTo(parent)
                        }
                )
            }

            // LazyVerticalGrid for categories, constrained below the appBar
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 25.dp) // Adjust this value as needed
                    .background(color = Color.Transparent)
                    .constrainAs(grid) {
                        top.linkTo(appBar.bottom) // This links the grid to the bottom of the appBar
                        bottom.linkTo(parent.bottom)
                    },
                columns = GridCells.Fixed(3), // You can change this to GridCells.Adaptive if needed
                contentPadding = PaddingValues(3.dp),
                content = {
                    itemsIndexed(listWallpaperModel) { index, wallpaper ->
                        WallpaperItem(
                            wallpaper = wallpaper,
                            modifier = Modifier.clickable {
                                navController.navigate("specificCategoryScreen/$index") // Pass the index here
                            }
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun WallpaperItem(wallpaper: WallpaperModel, modifier: Modifier) {
    // This composable is used to display each wallpaper item in the grid
    Box(
        modifier = modifier
            .padding(8.dp)
            .aspectRatio(1f) // Ensure the box is square
            .background(Color.LightGray) // Add a background color if needed
    ) {
        Image(
            painter = painterResource(id = wallpaper.wallpaperName),
            contentDescription = null,
            contentScale = ContentScale.FillBounds, // Crop to fit
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun previewAllCategoriesScreen(){
//    AllCategoriesScreen()
}