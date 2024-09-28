package com.example.myloveqoutesapp.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myloveqoutesapp.R
import com.example.myloveqoutesapp.model.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(navController: NavHostController) {
    HorizontalPagerScreen(navController)
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    // Creating a dummy NavController for the preview
    val navController = rememberNavController()

    SplashScreen(navController = navController)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerScreen(navController: NavHostController) {
    val data = listOf(
        painterResource(R.drawable.ic_splash1),
        painterResource(R.drawable.ic_splash2),
        painterResource(R.drawable.ic_splash3)
    )

    val pagerState = rememberPagerState(initialPage = 0) { data.size }
    val scope = rememberCoroutineScope()
    var isClickable by remember { mutableStateOf(true) } // State to manage clickability


    Box(modifier = Modifier.fillMaxSize()) {

        HorizontalPager(
            modifier = Modifier.align(Alignment.Center),  // Use Alignment.Center for centering
            state = pagerState
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painter = data[it],  // Replace with your image resource
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop  // Scale the image to fill the box
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val buttonText = when (pagerState.currentPage) {
                0 -> "Next"
                data.lastIndex -> "Finish"
                else -> "Next"
            }


            Button(
                onClick = {
                    if (isClickable) {
                        isClickable = false // Disable further clicks
                        scope.launch {
                            if (pagerState.currentPage == pagerState.pageCount - 1) {
                                navController.navigate(Screen.HomeScreen) {
                                    popUpTo(Screen.SplashScreen) { inclusive = true }
                                }
                            } else {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }.invokeOnCompletion { // Re-enable the button when the coroutine completes
                            isClickable = true
                        }
                    }
                },
                Modifier.width(100.dp),
                enabled = isClickable // Button is enabled based on isClickable state
            ) {
                Text(text = buttonText)
            }
            Row {
                repeat(data.size){
                    CustomIndicators(selected = pagerState.currentPage == it)
                }
            }
        }
    }
}



@Composable
fun CustomIndicators(selected: Boolean) {
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
fun PreviewCustomIndicators_Selected() {
    CustomIndicators(selected = true)
}