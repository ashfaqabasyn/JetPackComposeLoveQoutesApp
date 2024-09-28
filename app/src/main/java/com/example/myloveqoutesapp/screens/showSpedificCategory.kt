package com.example.myloveqoutesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myloveqoutesapp.domain.ui_layer.WallpaperModel
import com.example.myloveqoutesapp.R
import com.example.myloveqoutesapp.WallpaperViewModel
import com.example.myloveqoutesapp.model.SpecificCategoryModel
import org.koin.androidx.compose.getViewModel

@Composable
fun PhotoViewer(wallpaperModel: WallpaperModel) {
    val wallpaperViewModel: WallpaperViewModel = getViewModel()


    // Box to contain the image and controls
    Box(
        modifier = Modifier
            .height(230.dp)
            .background(Color.Transparent)
            .padding(start = 4.dp, end = 3.dp, bottom = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = wallpaperModel.wallpaperName),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            WallpaperItem(wallpaperModel = wallpaperModel, wallpaperViewModel = wallpaperViewModel)
            Image(
                painter = painterResource(id = R.drawable.btn_save),
                contentDescription = null,
                modifier = Modifier.clickable {
                    // Add save functionality here
                }
            )
            Image(
                painter = painterResource(id = R.drawable.btn_share),
                contentDescription = null,
                modifier = Modifier.clickable {
                    // Add share functionality here
                }
            )
        }
    }
}


//@Composable
//fun WallpaperItem(
//    wallpaperModel: WallpaperModel,
//    wallpaperViewModel: WallpaperViewModel
//) {
//    // Observe the LiveData for favorite status
//    val isFavorite by wallpaperViewModel.isWallpaperFavorite(wallpaperModel.wallpaperName).observeAsState(initial = false)
//
//    // Determine the button image based on the favorite status
//    val btnFavoriteUnFavorite = if (isFavorite) {
//        painterResource(id = R.drawable.btn_like_fill) // Filled heart if favorite
//    } else {
//        painterResource(id = R.drawable.btn_like) // Empty heart if not favorite
//    }
//
//    // Image with clickable modifier
//    Image(
//        painter = btnFavoriteUnFavorite,
//        contentDescription = null,
//        modifier = Modifier.clickable {
//            // Toggle favorite status when clicked
//            val newFavoriteStatus = !isFavorite // Toggle the favorite status
//
//            // Ensure that you are inserting or updating the correct wallpaper
//            wallpaperViewModel.insertOrUpdateWallpaper(
//                WallpaperModel(wallpaperModel.wallpaperName, newFavoriteStatus)
//            )
//        }
//    )
//}
@Composable
private fun WallpaperItem(
    wallpaperModel: WallpaperModel,
    wallpaperViewModel: WallpaperViewModel
) {
    // Trigger favorite status check when the composable is first composed
    LaunchedEffect(wallpaperModel.wallpaperName) {
        wallpaperViewModel.isWallpaperFavorite(wallpaperModel.wallpaperName)
    }

    // Collect the favorite status as state
    val isFavorite by wallpaperViewModel.isWallpaperFavorite(wallpaperModel.wallpaperName).observeAsState(initial = false)

    // Determine the button image based on the favorite status
    val btnFavoriteUnFavorite = if (isFavorite) {
        painterResource(id = R.drawable.btn_like_fill) // Filled heart if favorite
    } else {
        painterResource(id = R.drawable.btn_like) // Empty heart if not favorite
    }

    // Image with clickable modifier
    Image(
        painter = btnFavoriteUnFavorite,
        contentDescription = null,
        modifier = Modifier.clickable {
            // Toggle favorite status when clicked
            val newFavoriteStatus = !isFavorite

            // Insert or update wallpaper and toggle favorite status
            wallpaperViewModel.insertOrUpdateWallpaper(
                WallpaperModel(wallpaperModel.wallpaperName, newFavoriteStatus)
            )
        }
    )
}


@Composable
fun showSpecificCategory(navHostController: NavHostController,categoryId: Int) {

    val listOfTitles = listOf(
        "Valentine Quotes",
        "Breakup Quotes",
        "Broken Heart",
        "Crush Quotes",
        "Cute Love Quotes",
        "Forever Love",
        "Funny Messages",
        "Kiss Messages",
        "Long Distance",
        "Love Messages",
        "Love Quotes",
        "Love Sayings",
        "Pick Up Lines",
        "Romantic Quotes",
        "Sad Quotes",
        "Sorry Messages",
        "Love Poems"
    )
    val valentineList1 = listOf(
        WallpaperModel(R.drawable.ic1_v, false),
        WallpaperModel(R.drawable.ic2_v, false),
        WallpaperModel(R.drawable.ic3_v, false),
        WallpaperModel(R.drawable.ic4_v, false),
        WallpaperModel(R.drawable.ic5_v, false),
        WallpaperModel(R.drawable.ic6_v, false),
        WallpaperModel(R.drawable.ic7_v, false),
        WallpaperModel(R.drawable.ic8_v, false),
        WallpaperModel(R.drawable.ic9_v, false),
        WallpaperModel(R.drawable.ic10_v, false)
    )
    val breakupList2 = listOf(
        WallpaperModel(R.drawable.ic1_bu, false),
        WallpaperModel(R.drawable.ic2_bu, false),
        WallpaperModel(R.drawable.ic3_bu, false),
        WallpaperModel(R.drawable.ic4_bu, false),
        WallpaperModel(R.drawable.ic5_bu, false),
        WallpaperModel(R.drawable.ic6_bu, false),
        WallpaperModel(R.drawable.ic7_bu, false),
        WallpaperModel(R.drawable.ic8_bu, false),
        WallpaperModel(R.drawable.ic9_bu, false)
    )

    val brokenHeartList3 = listOf(
        WallpaperModel(R.drawable.ic1_bh, false),
        WallpaperModel(R.drawable.ic2_bh, false),
        WallpaperModel(R.drawable.ic3_bh, false),
        WallpaperModel(R.drawable.ic4_bh, false),
        WallpaperModel(R.drawable.ic5_bh, false),
        WallpaperModel(R.drawable.ic6_bh, false),
        WallpaperModel(R.drawable.ic7_bh, false),
        WallpaperModel(R.drawable.ic8_bh, false),
        WallpaperModel(R.drawable.ic9_bh, false),
        WallpaperModel(R.drawable.ic10_bh, false)
    )
    val crushQuoteList4 = listOf(
        WallpaperModel(R.drawable.ic1_cq, false),
        WallpaperModel(R.drawable.ic2_cq, false),
        WallpaperModel(R.drawable.ic3_cq, false),
        WallpaperModel(R.drawable.ic4_cq, false),
        WallpaperModel(R.drawable.ic5_cq, false),
        WallpaperModel(R.drawable.ic6_cq, false),
        WallpaperModel(R.drawable.ic7_cq, false),
        WallpaperModel(R.drawable.ic8_cq, false),
        WallpaperModel(R.drawable.ic9_cq, false),
        WallpaperModel(R.drawable.ic10_cq, false)
    )

    val cuteLoveList5 = listOf(
        WallpaperModel(R.drawable.ic1_cl, false),
        WallpaperModel(R.drawable.ic2_cl, false),
        WallpaperModel(R.drawable.ic3_cl, false),
        WallpaperModel(R.drawable.ic4_cl, false),
        WallpaperModel(R.drawable.ic5_cl, false),
        WallpaperModel(R.drawable.ic6_cl, false),
        WallpaperModel(R.drawable.ic7_cl, false),
        WallpaperModel(R.drawable.ic8_cl, false),
        WallpaperModel(R.drawable.ic9_cl, false),
        WallpaperModel(R.drawable.ic10_cl, false)

    )

    val foreverLoveList6 = listOf(
        WallpaperModel(R.drawable.ic1_fl, false),
        WallpaperModel(R.drawable.ic2_fl, false),
        WallpaperModel(R.drawable.ic3_fl, false),
        WallpaperModel(R.drawable.ic4_fl, false),
        WallpaperModel(R.drawable.ic5_fl, false),
        WallpaperModel(R.drawable.ic6_fl, false),
        WallpaperModel(R.drawable.ic7_fl, false),
        WallpaperModel(R.drawable.ic8_fl, false),
        WallpaperModel(R.drawable.ic9_fl, false),
        WallpaperModel(R.drawable.ic10_fl, false)
    )
    val funnyList7 = listOf(
        WallpaperModel(R.drawable.ic1_f, false),
        WallpaperModel(R.drawable.ic2_f, false),
        WallpaperModel(R.drawable.ic3_f, false),
        WallpaperModel(R.drawable.ic4_f, false),
        WallpaperModel(R.drawable.ic5_f, false),
        WallpaperModel(R.drawable.ic6_f, false),
        WallpaperModel(R.drawable.ic7_f, false),
        WallpaperModel(R.drawable.ic8_f, false),
        WallpaperModel(R.drawable.ic9_f, false),
        WallpaperModel(R.drawable.ic10_f, false)
    )

    val kissList8 = listOf(
        WallpaperModel(R.drawable.ic1_k, false),
        WallpaperModel(R.drawable.ic2_k, false),
        WallpaperModel(R.drawable.ic3_k, false),
        WallpaperModel(R.drawable.ic4_k, false),
        WallpaperModel(R.drawable.ic5_k, false),
        WallpaperModel(R.drawable.ic6_k, false),
        WallpaperModel(R.drawable.ic7_k, false),
        WallpaperModel(R.drawable.ic8_k, false),
        WallpaperModel(R.drawable.ic9_k, false),
        WallpaperModel(R.drawable.ic10_k, false)
    )


    val longDistanceList9 = listOf(
        WallpaperModel(R.drawable.ic1_ld, false),
        WallpaperModel(R.drawable.ic1_ld, false),
        WallpaperModel(R.drawable.ic2_ld, false),
        WallpaperModel(R.drawable.ic3_ld, false),
        WallpaperModel(R.drawable.ic4_ld, false),
        WallpaperModel(R.drawable.ic5_ld, false),
        WallpaperModel(R.drawable.ic6_ld, false),
        WallpaperModel(R.drawable.ic7_ld, false),
        WallpaperModel(R.drawable.ic8_ld, false),
        WallpaperModel(R.drawable.ic9_ld, false),
        WallpaperModel(R.drawable.ic10_ld, false)
    )

    val loveMessegesList10 = listOf(
        WallpaperModel(R.drawable.ic1_ld, false),
        WallpaperModel(R.drawable.ic2_ld, false),
        WallpaperModel(R.drawable.ic3_ld, false),
        WallpaperModel(R.drawable.ic4_ld, false),
        WallpaperModel(R.drawable.ic5_ld, false),
        WallpaperModel(R.drawable.ic6_ld, false),
        WallpaperModel(R.drawable.ic7_ld, false),
        WallpaperModel(R.drawable.ic8_ld, false),
        WallpaperModel(R.drawable.ic9_ld, false),
        WallpaperModel(R.drawable.ic10_ld, false)
    )
    val loveQoutesList11 = listOf(
        WallpaperModel(R.drawable.ic1_lq, false),
        WallpaperModel(R.drawable.ic2_lq, false),
        WallpaperModel(R.drawable.ic3_lq, false),
        WallpaperModel(R.drawable.ic4_lq, false),
        WallpaperModel(R.drawable.ic5_lq, false),
        WallpaperModel(R.drawable.ic6_lq, false),
        WallpaperModel(R.drawable.ic7_lq, false),
        WallpaperModel(R.drawable.ic8_lq, false),
        WallpaperModel(R.drawable.ic9_lq, false),
        WallpaperModel(R.drawable.ic10_lq, false),
    )
    val loveSayingsList12 = listOf(
        WallpaperModel(R.drawable.ic1_ls, false),
        WallpaperModel(R.drawable.ic2_ls, false),
        WallpaperModel(R.drawable.ic3_ls, false),
        WallpaperModel(R.drawable.ic4_ls, false),
        WallpaperModel(R.drawable.ic5_ls, false),
        WallpaperModel(R.drawable.ic6_ls, false),
        WallpaperModel(R.drawable.ic7_ls, false),
        WallpaperModel(R.drawable.ic8_ls, false),
        WallpaperModel(R.drawable.ic9_ls, false),
        WallpaperModel(R.drawable.ic10_ls, false)
    )
    val pickUpLinesList13 = listOf(
        WallpaperModel(R.drawable.ic1_pl, false),
        WallpaperModel(R.drawable.ic2_pl, false),
        WallpaperModel(R.drawable.ic3_pl, false),
        WallpaperModel(R.drawable.ic4_pl, false),
        WallpaperModel(R.drawable.ic5_pl, false),
        WallpaperModel(R.drawable.ic6_pl, false),
        WallpaperModel(R.drawable.ic7_pl, false),
        WallpaperModel(R.drawable.ic8_pl, false),
        WallpaperModel(R.drawable.ic9_pl, false),
        WallpaperModel(R.drawable.ic10_pl, false)
    )

    val romanticList14 = listOf(
        WallpaperModel(R.drawable.ic1_r, false),
        WallpaperModel(R.drawable.ic2_r, false),
        WallpaperModel(R.drawable.ic3_r, false),
        WallpaperModel(R.drawable.ic4_r, false),
        WallpaperModel(R.drawable.ic5_r, false),
        WallpaperModel(R.drawable.ic6_r, false),
        WallpaperModel(R.drawable.ic7_r, false),
        WallpaperModel(R.drawable.ic8_r, false),
        WallpaperModel(R.drawable.ic9_r, false),
        WallpaperModel(R.drawable.ic10_r, false)
    )
    val sadList15 = listOf(
        WallpaperModel(R.drawable.ic1_s, false),
        WallpaperModel(R.drawable.ic2_s, false),
        WallpaperModel(R.drawable.ic3_s, false),
        WallpaperModel(R.drawable.ic4_s, false),
        WallpaperModel(R.drawable.ic5_s, false),
        WallpaperModel(R.drawable.ic6_s, false),
        WallpaperModel(R.drawable.ic7_s, false),
        WallpaperModel(R.drawable.ic8_s, false),
        WallpaperModel(R.drawable.ic9_s, false),
        WallpaperModel(R.drawable.ic10_s, false)
    )

    val sorryList16 = listOf(
        WallpaperModel(R.drawable.ic1_sr, false),
        WallpaperModel(R.drawable.ic2_sr, false),
        WallpaperModel(R.drawable.ic3_sr, false),
        WallpaperModel(R.drawable.ic4_sr, false),
        WallpaperModel(R.drawable.ic5_sr, false),
        WallpaperModel(R.drawable.ic6_sr, false),
        WallpaperModel(R.drawable.ic7_sr, false),
        WallpaperModel(R.drawable.ic8_sr, false),
        WallpaperModel(R.drawable.ic9_sr, false),
        WallpaperModel(R.drawable.ic10_sr, false)
    )
    val lovePoemsList17 = listOf(
        WallpaperModel(R.drawable.ic_poem1, false),
        WallpaperModel(R.drawable.ic_poem2, false),
        WallpaperModel(R.drawable.ic_poem3, false),
        WallpaperModel(R.drawable.ic_poem4, false),
        WallpaperModel(R.drawable.ic_poem5, false),
        WallpaperModel(R.drawable.ic_poem6, false),
        WallpaperModel(R.drawable.ic_poem7, false),
        WallpaperModel(R.drawable.ic_poem8, false),
        WallpaperModel(R.drawable.ic_poem9, false),
        WallpaperModel(R.drawable.ic_poem10, false),
        )


        val listOfSpecificCategory = listOf(
        SpecificCategoryModel(listOfTitles[0], valentineList1),
        SpecificCategoryModel(listOfTitles[1], breakupList2),
        SpecificCategoryModel(listOfTitles[2], brokenHeartList3),
        SpecificCategoryModel(listOfTitles[3], crushQuoteList4),
        SpecificCategoryModel(listOfTitles[4], cuteLoveList5),
        SpecificCategoryModel(listOfTitles[5], foreverLoveList6),
        SpecificCategoryModel(listOfTitles[6], funnyList7),
        SpecificCategoryModel(listOfTitles[7], kissList8),
        SpecificCategoryModel(listOfTitles[8], longDistanceList9),
        SpecificCategoryModel(listOfTitles[9], loveMessegesList10),
        SpecificCategoryModel(listOfTitles[10], loveQoutesList11),
        SpecificCategoryModel(listOfTitles[11], loveSayingsList12),
        SpecificCategoryModel(listOfTitles[12], pickUpLinesList13),
        SpecificCategoryModel(listOfTitles[13], romanticList14),
        SpecificCategoryModel(listOfTitles[14], sadList15),
        SpecificCategoryModel(listOfTitles[15], sorryList16),
        SpecificCategoryModel(listOfTitles[16], lovePoemsList17)
    )


    Box() {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_mm_bg),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
        )
    }
    ConstraintLayout {
        val (appBar, lazyColumn) = createRefs()

        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .constrainAs(appBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            val (drawerMenu, text, end1, end2) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_menu_16), // Replace with your drawable
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.constrainAs(drawerMenu) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
            Image(
                painter = painterResource(id = R.drawable.ic_bell), // Replace with your drawable
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .constrainAs(end1) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.ic_favorite), // Replace with your drawable
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .constrainAs(end2) {
                        end.linkTo(end1.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )


            Text(
                text = listOfTitles[categoryId].toString(),
                color = Color.Red,
                fontSize = 14.sp, // Use sp for font size
                fontWeight = FontWeight.Bold, // Set font weight to Bold
                fontStyle = FontStyle.Italic, // Set font style to Italic
                modifier = Modifier
                    .constrainAs(text) {
                        start.linkTo(drawerMenu.end)  // Start of Text linked to the end of drawerMenu
                        end.linkTo(end2.start)        // End of Text linked to the start of end2
                        top.linkTo(parent.top)        // Align with the top of the parent
                        bottom.linkTo(parent.bottom)  // Align with the bottom of the parent
                    }
            )

        }

        LazyColumn(
            modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(lazyColumn) {
                    top.linkTo(appBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            items(listOfSpecificCategory[categoryId].wallpaperName.size) { index ->
                PhotoViewer(listOfSpecificCategory[categoryId].wallpaperName[index]) // Pass each wallpaper to PhotoViewer
            }

            item {
                Spacer(modifier = Modifier.height(35.dp)) // Adjust height as necessary
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun previewShowSpecificCategory() {
    val lovePoemsList17 = listOf(
        WallpaperModel(R.drawable.ic_poem2, false),
        WallpaperModel(R.drawable.ic_poem1, false),
        WallpaperModel(R.drawable.ic_poem3, false),
        WallpaperModel(R.drawable.ic_poem4, false),
        WallpaperModel(R.drawable.ic_poem5, false),
        WallpaperModel(R.drawable.ic_poem6, false),
        WallpaperModel(R.drawable.ic_poem7, false),
        WallpaperModel(R.drawable.ic_poem8, false),
        WallpaperModel(R.drawable.ic_poem9, false),
        WallpaperModel(R.drawable.ic_poem10, false),
    )
    val navController = rememberNavController()
    showSpecificCategory(navController,0)

//    val navController = rememberNavController()
//    var model = SpecificCategoryModel("Love Quotes123",lovePoemsList17)
//    showSpecificCategory(navHostController = navController,modele)
}

