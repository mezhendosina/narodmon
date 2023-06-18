package ru.nm17.narodmon.ui.webCamsScreen

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap

data class WebCamUiEntity(
    val id: Int,
    val name: String,
    val distance: Int,
    val location: String,
    val time: String,
    val imageUrl: String
)
