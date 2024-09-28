package com.example.myloveqoutesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myloveqoutesapp.R
import com.example.myloveqoutesapp.model.Screen

@Composable
fun LoveNovelsScreen(navController: NavController) {
    val novelListsPic= listOf(painterResource(id = R.drawable.ic_novel1),painterResource(id = R.drawable.ic_novel2),painterResource(id = R.drawable.ic_novel3),painterResource(id = R.drawable.ic_novel4))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        // Background Image
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_mm_bg),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
        )
        ConstraintLayout() {
            val (appbar,column) = createRefs()

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
            ) {
                val (part1, scrollableContent) = createRefs()

                // First part (top part)
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(Color.Transparent) // Set transparent background
                        .constrainAs(part1) {
                            top.linkTo(parent.top)
                        }
                ) {
                    // Create references for the 4 images
                    val (startImage, centerImage, endImage1, endImage2) = createRefs()

                    // Start image (left-aligned)
                    Image(
                        painter = painterResource(id = R.drawable.ic_menu_16), // Replace with your drawable
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp) // Adjust size as needed
                            .constrainAs(startImage) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    )

                    // Center image
                    Text(
                        text = "Love Quotes", // Replace with your desired text
                        style = TextStyle(fontSize = 12.sp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .constrainAs(centerImage) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                            .padding(8.dp), // Optional padding around the text
                        fontSize = 16.sp, // Adjust text size
                        color = Color.Red // Adjust text color
                    )

                    // First image on the right (aligned to end)
                    Image(
                        painter = painterResource(id = R.drawable.ic_favorite), // Replace with your drawable
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp) // Adjust size as needed
                            .clickable {
                                navController.navigate(Screen.FavoriteListScreen)
                            }
                            .constrainAs(endImage1) {
                                end.linkTo(endImage2.start) // Align to the left of the second end image
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    )

                    // Second image on the right (aligned to parent end)
                    Image(
                        painter = painterResource(id = R.drawable.ic_bell), // Replace with your drawable
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp) // Adjust size as needed
                            .constrainAs(endImage2) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    )
                }
                LazyColumn(modifier = Modifier.fillMaxSize()
                    .constrainAs(scrollableContent){
                    top.linkTo(appbar.bottom,56.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }){
                    itemsIndexed(novelListsPic){ index,pic->
                        Image(painter = pic,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().clickable{
                                when(index){
                                    0->{
                                        navController.navigate("PdfViewScreen/$index") // Pass the index here
                                    }
                                    1->{
                                        navController.navigate("PdfViewScreen/$index") // Pass the index here
                                    }
                                    2->{
                                        navController.navigate("PdfViewScreen/$index") // Pass the index here
                                    }
                                    3->{
                                        navController.navigate("PdfViewScreen/$index") // Pass the index here
                                    }
                                    else->{
                                        navController.navigate("PdfViewScreen/$index") // Pass the index here
                                    }
                                }
                            }.height(150.dp),
                            contentScale = ContentScale.FillBounds
                        )                    }
                }
        }
        }

    }

}





//@Preview(showBackground = true)
//@Composable
//fun LoveNovelsScreenPreview() {
//    val navController = rememberNavController()
//    LoveNovelsScreen(navController = navController)
//}