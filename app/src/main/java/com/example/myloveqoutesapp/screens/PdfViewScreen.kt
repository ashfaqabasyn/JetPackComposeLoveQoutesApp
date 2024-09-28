package com.example.myloveqoutesapp.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myloveqoutesapp.R
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myloveqoutesapp.model.Screen


@Composable
fun PdfViewScreen(navController: NavController, index: Int) {

    val listOfPdf= listOf(
        R.raw.pdf_first,
        R.raw.pdf_second,
        R.raw.pdf_third,
        R.raw.pdf_fourth
    )
    Box(modifier = Modifier.fillMaxSize()
        .background(Color.White)){
        val context = LocalContext.current
        val pdfFile = remember { copyPdfFromRawToTemp(context, listOfPdf[index]) } // Load 'first.pdf' from raw
        val pdfRenderer = remember { PdfRenderer(ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY)) }
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (appBar,lazyColumn) = createRefs()
            ConstraintLayout(modifier = Modifier.fillMaxWidth().background(Color.White).constrainAs(appBar){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
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
            // Show the PDF content in a scrollable LazyColumn
            LazyColumn(modifier = Modifier.fillMaxSize().constrainAs(lazyColumn){
                top.linkTo(appBar.bottom,56.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }.background(Color.White)) {
                items(pdfRenderer.pageCount) { pageIndex ->
                    val pdfBitmap = renderPageFromPdf(pdfRenderer, pageIndex)

                    if (pdfBitmap != null) {
                        // Calculate the aspect ratio
                        val aspectRatio = pdfBitmap.width.toFloat() / pdfBitmap.height.toFloat()
                        val screenWidthPx = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.toDp().toPx() }

//                val screenWidth = LocalConfiguration.current.screenWidthDp.dp.toPx() // Get screen width in pixels
                        val scaledHeight = ((screenWidthPx) / aspectRatio).toInt() // Calculate scaled height

                        Image(
                            bitmap = pdfBitmap.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(scaledHeight.dp) // Set height based on scaled value
                                .padding(vertical = 8.dp) // Add some padding between pages
                        )
                    } else {
                        // Centering the message within the LazyColumn
                        Text(
                            text = "Failed to load PDF",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp) // Add padding around the text
//                        .wrapContentWidth(Alignment.Center) // Center the text
                        )
                    }
                }
            }
        }
    }



}

// Function to render a specific page from the PDF
fun renderPageFromPdf(pdfRenderer: PdfRenderer, pageIndex: Int): Bitmap? {
    return try {
        val page = pdfRenderer.openPage(pageIndex)

        val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

        page.close()

        bitmap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


// Function to copy PDF from raw to a temporary file
fun copyPdfFromRawToTemp(context: Context, pdfResId: Int): File {
    val inputStream: InputStream = context.resources.openRawResource(pdfResId)
    val tempFile = File(context.cacheDir, "tempfile_$pdfResId.pdf")  // Create a valid file name

    inputStream.use {  // Ensure inputStream is properly closed
        FileOutputStream(tempFile).use { outputStream ->
            val buffer = ByteArray(1024)  // Define a buffer size of 1024 bytes (1 KB)
            var bytesRead: Int

            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)  // Write to output stream
            }
        }
    }
    return tempFile
}


