package ru.nm17.narodmon.ui.navHost

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import ru.nm17.narodmon.R

sealed class MainScreenSealed(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val iconId: Int
) {
    object Sensors : MainScreenSealed("sensors", R.string.sensors_page_title, R.drawable.ic_home)
    object Webcams : MainScreenSealed("webcams", R.string.webcams, R.drawable.ic_webcam)

    object Messages : MainScreenSealed("messages", R.string.messages, R.drawable.ic_message)

    object Settings : MainScreenSealed("settings", R.string.settings, R.drawable.ic_settings)
}