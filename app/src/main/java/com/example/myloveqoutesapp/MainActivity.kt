package com.example.myloveqoutesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myloveqoutesapp.model.NavGraph
import com.example.myloveqoutesapp.ui.theme.MyLoveQoutesAppTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyLoveQoutesAppTheme {
                // Your Scaffold
                val navController = rememberNavController()
                Scaffold(
//                    topBar = {
//                        TopAppBar(title = { Text(text = "Love Quotes App") })
//                    },
                    content = { innerPadding ->
                        // Padding for scaffold content
                        NavGraph(navController = navController, paddingValues = innerPadding)
                    }
                )
            }
        }
    }
}

@Composable
fun QuotesScreen(navController: NavHostController) {

}










