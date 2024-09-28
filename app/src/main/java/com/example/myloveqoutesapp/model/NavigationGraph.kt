package com.example.myloveqoutesapp.model

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myloveqoutesapp.screens.AllCategoriesScreen
import com.example.myloveqoutesapp.screens.FavoriteListScreen
//import com.example.myloveqoutesapp.screens.FavoriteScreen
import com.example.myloveqoutesapp.screens.HomeScreen
import com.example.myloveqoutesapp.screens.LoveNovelsScreen
import com.example.myloveqoutesapp.screens.PdfViewScreen
import com.example.myloveqoutesapp.screens.SplashScreen
import com.example.myloveqoutesapp.screens.showSpecificCategory

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen, // Starting screen
        modifier = Modifier.padding(paddingValues)
    ) {
        composable<Screen.HomeScreen>(
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700)) + fadeIn(
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(700)
                ) + fadeOut(animationSpec = tween(700))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = tween(700)
                ) + fadeIn(animationSpec = tween(700))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 1000 },
                    animationSpec = tween(700)
                ) + fadeOut(animationSpec = tween(700))
            }
        ) {
            HomeScreen(navController)
        }

        composable(
            route = "specificCategoryScreen/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType }),
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700)) + fadeIn(
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(700)
                ) + fadeOut(animationSpec = tween(700))
            }
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0
            showSpecificCategory(navController, categoryId)
        }

        composable(
            route = "PdfViewScreen/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType }),
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700)) + fadeIn(
                    animationSpec = tween(700)
                )
            },
//            exitTransition = { _, _ ->
//                slideOutHorizontally(
//                    targetOffsetX = { -1000 },
//                    animationSpec = tween(700)
//                ) + fadeOut(animationSpec = tween(700))
//            }
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            PdfViewScreen(navController, index)
        }

        composable<Screen.SplashScreen>(
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700)) + fadeIn(
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(700)
                ) + fadeOut(animationSpec = tween(700))
            }
        ) {
            SplashScreen(navController)
        }

        composable<Screen.CategoryScreen>(
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700)) + fadeIn(
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(700)
                ) + fadeOut(animationSpec = tween(700))
            }
        ) {
            AllCategoriesScreen(navController)
        }

        composable<Screen.FavoriteListScreen>(
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700)) + fadeIn(
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(700)
                ) + fadeOut(animationSpec = tween(700))
            }
        ) {
            FavoriteListScreen(navController)
        }

        composable<Screen.LoveNovelsScreen>(
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700)) + fadeIn(
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(700)
                ) + fadeOut(animationSpec = tween(700))
            }
        ) {
            LoveNovelsScreen(navController)
        }
    }
}



/*  composable<Screen.HomeScreen> {
      HomeScreen(navController)
  }

  composable(
      route = "specificCategoryScreen/{categoryId}",
      arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
  ) { backStackEntry ->
      // Retrieve the integer argument
      val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0 // Provide a default value if needed
      showSpecificCategory(navController, categoryId)
  }

  composable(
      route = "PdfViewScreen/{index}",
      arguments = listOf(navArgument("index") { type = NavType.IntType })
  ) { backStackEntry ->
      // Retrieve the integer argument
      val categoryId = backStackEntry.arguments?.getInt("index") ?: 0 // Provide a default value if needed
      PdfViewScreen(navController, categoryId)
  }


  composable<Screen.SplashScreen> {
      SplashScreen(navController)
  }

  composable<Screen.CategoryScreen> {
      AllCategoriesScreen(navController)
  }



  composable<Screen.FavoriteListScreen> {
      FavoriteListScreen(navController)
  }

  composable<Screen.LoveNovelsScreen> {
      LoveNovelsScreen(navController)
  }


}
}*/