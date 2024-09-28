package com.example.myloveqoutesapp.screens


import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myloveqoutesapp.MainActivity
import com.example.myloveqoutesapp.R
import com.example.myloveqoutesapp.model.Screen
import kotlinx.coroutines.launch


private val imagPopularQoutes = listOf(
    R.drawable.ic5_crush,
    R.drawable.ic11_love_quotes,
    R.drawable.ic7_forever,
    R.drawable.ic14_romantic
)

private val otherCategories = listOf(
    R.drawable.ic6_cute,
    R.drawable.ic12_love_saying,
    R.drawable.ic16_love_messeges,
    R.drawable.ic13_pickup
)

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(navController: NavHostController) {
    val scopes = rememberCoroutineScope()
    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler {
        showExitDialog = true // Show exit confirmation dialog
    }

    if (showExitDialog) {
        val context = LocalContext.current

        // Show a dialog asking if the user wants to exit the app
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text(text = "Exit App?") },
            text = { Text("Do you really want to exit the app?") },
            confirmButton = {
                Button(onClick = {
                    showToast(context)
                }) { // Add logic to exit the app
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showExitDialog = false
                }) {
                    Text("No")
                }
            }
        )
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


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
                            .clickable {
//                                DrawerContent(navController,drawerState,scope)
                                scope.launch {
                                    drawerState.open() // Open the drawer when clicked
                                }

                            }
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

                // Scrollable content for parts 2 to 5
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()) // Make the Column scrollable
                        .padding(top = 56.dp)// Add padding to create margin around the column
                        .constrainAs(scrollableContent) {
                            top.linkTo(part1.bottom) // Align below part1
                            bottom.linkTo(parent.bottom) // Fill remaining space
                        }
                ) {
                    HorizontalPagerHomeScreen()
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 5.dp)
                            .background(Color.Transparent)
                    ) {
                        CenteredImages(navController)
                    }

                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(Color.Transparent)
                    ) {
                        // Create references for the images
                        val (centeredImage, endImage) = createRefs()
                        // Centered Image
                        Image(
                            painter = painterResource(id = R.drawable.popular_20_11zon), // Replace with your drawable
                            contentDescription = null,
                            modifier = Modifier
                                .constrainAs(centeredImage) {
                                    centerTo(parent) // Center the image in the parent
                                }
                                .height(30.dp)
                                .width(90.dp),
                            contentScale = ContentScale.FillBounds
                        )

                        // End Image
                        Image(
                            painter = painterResource(id = R.drawable.ic_mm_see_all), // Replace with your drawable
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Screen.CategoryScreen)
                                }
                                .constrainAs(endImage) {
                                    end.linkTo(parent.end) // Align the image to the end of the parent
                                    centerVerticallyTo(parent) // Center it vertically
                                }
                                .height(30.dp)
                                .width(100.dp),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                            .background(Color.Transparent)
                    ) {
                        HorizontalPopularImageList(imagPopularQoutes,navController)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(Color.Transparent)
                    ) {
                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(Color.Transparent)
                        ) {
                            // Create references for the images
                            val (centeredImage, endImage) = createRefs()

                            // Centered Image
                            Image(
                                painter = painterResource(id = R.drawable.other_18_11zon), // Replace with your drawable
                                contentDescription = null,
                                modifier = Modifier
                                    .constrainAs(centeredImage) {
                                        centerTo(parent) // Center the image in the parent
                                    }
                                    .height(30.dp)
                                    .width(90.dp),
                                contentScale = ContentScale.FillBounds
                            )

                            // End Image
                            Image(
                                painter = painterResource(id = R.drawable.ic_mm_see_all), // Replace with your drawable
                                contentDescription = null,
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(Screen.CategoryScreen)
                                    }
                                    .constrainAs(endImage) {
                                        end.linkTo(parent.end) // Align the image to the end of the parent
                                        centerVerticallyTo(parent) // Center it vertically
                                    }
                                    .height(30.dp)
                                    .width(100.dp),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }

                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                            .background(Color.Transparent)
                    ) {
                        HorizontalPopularImageList(otherCategories,navController)
                    }
                }
            }
        }
}


fun showToast(context: Context) {
    (context as? MainActivity)?.finish() // or call finish()

}
//@Composable
//fun exitApp() {
//    val context = LocalContext.current
//
//    // Show Toast message before exiting
//    Toast.makeText(context, "Exiting the app...", Toast.LENGTH_SHORT).show()}
//}

//@Composable
//fun exitApp(val context:Context) {
////    val context = LocalContext.current
//
//    // Show Toast message before exiting
//    Toast.makeText(context, "Exiting the app...", Toast.LENGTH_SHORT).show()
//}

@Composable
fun HorizontalPopularImageList(imageList: List<Int>, navController: NavHostController) { // Accept a list of image resource IDs
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.Transparent)
    ) {
        val (row) = createRefs()

        // Horizontal scrolling row of images
        LazyRow(
            modifier = Modifier.constrainAs(row) {
                centerTo(parent) // Center the row within the ConstraintLayout
            }
        ) {

            itemsIndexed(imageList) { index, imageRes ->
                Image(
                    painter = painterResource(id = imageRes), // Load the image
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            if(imageList==imagPopularQoutes){
                                when(index){
                                0 -> navController.navigate("specificCategoryScreen/3")
                                1 -> navController.navigate("specificCategoryScreen/10")
                                2 -> navController.navigate("specificCategoryScreen/5")
                                3 -> navController.navigate("specificCategoryScreen/13")
                                else->{

                                }
                            }
                            }else{
                                when(index){
                                    0 -> navController.navigate("specificCategoryScreen/4")
                                    1 -> navController.navigate("specificCategoryScreen/11")
                                    2 -> navController.navigate("specificCategoryScreen/9")
                                    3 -> navController.navigate("specificCategoryScreen/12")
                                    else->{
                                    }
                                }
                            }
                        }
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds

                )
            }
        }
    }

}

@Composable
fun CenteredImages(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth() // ConstraintLayout fills the width of the parent
    ) {
        val (image1, image2, image3, image4) = createRefs()
        // Create horizontal chains for the top and bottom row
        createHorizontalChain(image1, image2, chainStyle = ChainStyle.Packed)
        createHorizontalChain(image3, image4, chainStyle = ChainStyle.Packed)
        // Image 1 (Top Left)
        Image(
            painter = painterResource(id = R.drawable.ic_category), // Replace with your drawable
            contentDescription = null,
            contentScale = ContentScale.FillBounds, // Scale the image to fit inside the box
            modifier = Modifier
                .padding(top = 4.dp, bottom = 2.dp, end = 2.dp)
                .width(150.dp) // Set the desired width here
                .clickable {
                    navController.navigate(Screen.CategoryScreen)
                }
                .constrainAs(image1) {
                    top.linkTo(parent.top)
                    bottom.linkTo(image3.top)
                }
        )

        // Image 2 (Top Right)
        Image(
            painter = painterResource(id = R.drawable.ic_love_qoutes), // Replace with your drawable
            contentDescription = null,
            contentScale = ContentScale.FillBounds, // Scale the image to fit inside the box
            modifier = Modifier
                .padding(top = 4.dp, bottom = 2.dp, start = 2.dp)
                .width(150.dp)
                .clickable {
                    navController.navigate("specificCategoryScreen/10")                }
                .constrainAs(image2) {
                    top.linkTo(parent.top)
                    bottom.linkTo(image4.top)
                }
        )

        // Image 3 (Bottom Left)
        Image(
            painter = painterResource(id = R.drawable.ic_love_novels), // Replace with your drawable
            contentDescription = null,
            contentScale = ContentScale.FillBounds, // Scale the image to fit inside the box
            modifier = Modifier
                .padding(top = 2.dp, bottom = 4.dp, end = 2.dp)
                .width(150.dp)
                .clickable {
                    navController.navigate(Screen.LoveNovelsScreen)
                }
                .constrainAs(image3) {
                    top.linkTo(image1.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )

        // Image 4 (Bottom Right)
        Image(
            painter = painterResource(id = R.drawable.ic_lovepoems), // Replace with your drawable
            contentDescription = null,
            contentScale = ContentScale.FillBounds, // Scale the image to fit inside the box
            modifier = Modifier
                .padding(top = 2.dp, bottom = 4.dp, start = 2.dp)
                .width(150.dp)
                .clickable {
                    navController.navigate("specificCategoryScreen/16")
                }
                .constrainAs(image4) {
                    top.linkTo(image2.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerHomeScreen() {
    val data = listOf(
        painterResource(R.drawable.ic_mm1),
        painterResource(R.drawable.ic_mm2),
        painterResource(R.drawable.ic_mm3)
    )

    val pagerState = rememberPagerState(initialPage = 0) { data.size }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        // HorizontalPager in the center
        HorizontalPager(
            state = pagerState
        ) { page ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painter = data[page],  // Use the current page image
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(150.dp)
                        .padding(bottom = 18.dp),
                    contentScale = ContentScale.FillBounds  // Scale the image to fit inside the box
                )
            }
        }

        // The indicators at the bottom center of the first box
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.BottomCenter)  // Align the Box at the bottom center
                .padding(top = 120.dp),  // Optional padding to adjust distance from bottom
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(data.size) { index ->
                    CustomIndicators1(selected = pagerState.currentPage == index)
                }
            }
        }
    }
}

@Composable
fun CustomIndicators1(selected: Boolean) {
    // Choose a color based on selection state
    val fillColor = if (selected) Color.Red else Color.Gray

    Box(
        modifier = Modifier
            .padding(2.dp)
            .width(30.dp)
            .size(12.dp) // Adjust size as needed
            .clip(RoundedCornerShape(10.dp)) // Set rounded corners
            .background(fillColor) // Fill with color
    )
}



@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    // Create a dummy NavController for the preview
    val navController = rememberNavController()

    HomeScreen(navController = navController)
}